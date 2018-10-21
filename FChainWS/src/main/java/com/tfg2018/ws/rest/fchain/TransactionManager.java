package com.tfg2018.ws.rest.fchain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.entity.StringEntity;

import com.tfg2018.ws.rest.object.KeyPairs;
import com.tfg2018.ws.rest.object.Token;
import com.tfg2018.ws.rest.utils.CommandTranslator;
import com.tfg2018.ws.rest.utils.GsontoObjectTranslator;

public class TransactionManager {

	private FChainInterface fChainQuerier = new FChainInterface(FChainConst.MULTICHAIN_SERVER_IP,
			FChainConst.MULTICHAIN_SERVER_PORT, FChainConst.MULTICHAIN_SERVER_LOGIN, FChainConst.MULTICHAIN_SERVER_PWD);

	public String sendAssetFrom(String hexBlob) throws Exception {
		StringEntity request = CommandTranslator.commandToJson("sendrawtransaction", hexBlob);
		try {
			return this.fChainQuerier.executeRequest(request).toString();
		} catch (Exception e) {
			throw new Exception("an error ocurred while sending the assets");
		}
	}

	public String createAndSignRawTransaction(KeyPairs sender, String destination, Token token) throws Exception {
		String hexBlob;
		try {
			Map<String, Object> mapParams = prepareMap(destination, token);
			StringEntity request = CommandTranslator.commandToJson("createrawsendfrom", sender.getAddress(), mapParams);
			hexBlob = this.fChainQuerier.executeRequest(request).toString();
		} catch (Exception e) {
			throw new Exception("error creating the transaction, the asset doesn't belong to the sender address");
		}
		return signRequest(sender, hexBlob);
	}

	private Map<String, Object> prepareMap(String destination, Token token) {
		Map<String, Object> mapParams = new HashMap<String, Object>();
		Map<String, Double> filledAsset = new HashMap<String, Double>();
		filledAsset.put(token.getName(), 1.0);
		mapParams.put(destination, filledAsset);
		return mapParams;
	}

	private String signRequest(KeyPairs sender, String hexBlob) throws Exception {
		List<String> label = new ArrayList<String>();
		List<String> privKey = new ArrayList<String>();
		privKey.add(sender.getPrivkey());

		StringEntity request = CommandTranslator.commandToJson("signrawtransaction", hexBlob, label, privKey);
		try {
			return GsontoObjectTranslator.getSignedTransactionInfo(this.fChainQuerier.executeRequest(request)).getHex();
		} catch (Exception e) {
			throw new Exception("error ocurred while singing raw transaction");
		}
	}

}
