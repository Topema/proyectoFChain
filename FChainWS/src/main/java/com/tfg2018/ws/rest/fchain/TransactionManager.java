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

	public String sendAssetFrom(KeyPairs sender, String objective, Token token) throws Exception {
		String hexBlob = createSignRequest(sender, token, objective);
		StringEntity request = CommandTranslator.commandToJson("sendrawtransaction", hexBlob);
		try {
			return this.fChainQuerier.executeRequest(request).toString();
		} catch (Exception e) {
			throw new Exception("an error ocurred while sending the assets");
		}
	}

	private String createRawTransaction(String sender, Token token, String destination) throws Exception {
		try {
			Map<String, Object> mapParams = prepareMap(destination, token);
			StringEntity request = CommandTranslator.commandToJson("createrawsendfrom", sender, mapParams);
			return this.fChainQuerier.executeRequest(request).toString();
		} catch (Exception e) {
			throw new Exception("error creating the transaction, the asset doesn't belong to the sender address");
		}
	}

	private Map<String, Object> prepareMap(String destination, Token token) {
		Map<String, Object> mapParams = new HashMap<String, Object>();
		Map<String, Double> filledAsset = new HashMap<String, Double>();
		filledAsset.put(token.getName(), 1.0);
		mapParams.put(destination, filledAsset);
		return mapParams;
	}

	private String createSignRequest(KeyPairs sender, Token token, String objective) throws Exception {
		String hexBlob = createRawTransaction(sender.getAddress(), token, objective);
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
