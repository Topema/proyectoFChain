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

	private FchainInterface fChainQuerier = new FchainInterface(FchainConst.MULTICHAIN_SERVER_IP,
			FchainConst.MULTICHAIN_SERVER_PORT, FchainConst.MULTICHAIN_SERVER_LOGIN, FchainConst.MULTICHAIN_SERVER_PWD);

	public String sendConfirmedTransaction(String hexBlob) throws Exception {
		StringEntity request = CommandTranslator.commandToJson("sendrawtransaction", hexBlob);
		try {
			return this.fChainQuerier.executeRequest(request).toString();
		} catch (Exception e) {
			throw new Exception("an error ocurred while sending the assets");
		}
	}

	public String createAndSignRawTransaction(String senderAddress, String senderPrivKey, String destination, String tokenName) throws Exception {
		String hexBlob;
		try {
			Map<String, Object> mapParams = prepareMap(destination, tokenName);
			StringEntity request = CommandTranslator.commandToJson("createrawsendfrom",senderAddress, mapParams);
			hexBlob = this.fChainQuerier.executeRequest(request).toString();
		} catch (Exception e) {
			throw new Exception("error creating the transaction, the asset doesn't belong to the sender address");
		}
		return signRequest(senderPrivKey, hexBlob);
	}

	private Map<String, Object> prepareMap(String destination, String tokenName) {
		Map<String, Object> mapParams = new HashMap<String, Object>();
		Map<String, Double> filledAsset = new HashMap<String, Double>();
		filledAsset.put(tokenName, 1.0);
		mapParams.put(destination, filledAsset);
		return mapParams;
	}

	private String signRequest(String sender, String hexBlob) throws Exception {
		List<String> label = new ArrayList<String>();
		List<String> privKey = new ArrayList<String>();
		privKey.add(sender);

		StringEntity request = CommandTranslator.commandToJson("signrawtransaction", hexBlob, label, privKey);
		try {
			return GsontoObjectTranslator.getSignedTransactionInfo(this.fChainQuerier.executeRequest(request)).getHex();
		} catch (Exception e) {
			throw new Exception("error ocurred while singing raw transaction");
		}
	}

}
