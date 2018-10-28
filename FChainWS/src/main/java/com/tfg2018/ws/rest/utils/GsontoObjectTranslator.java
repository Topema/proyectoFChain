package com.tfg2018.ws.rest.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.tfg2018.ws.rest.object.AddressValidator;
import com.tfg2018.ws.rest.object.AssetTransaction;
import com.tfg2018.ws.rest.object.KeyPairs;
import com.tfg2018.ws.rest.object.SignedTransactionInfo;
import com.tfg2018.ws.rest.object.Token;

public class GsontoObjectTranslator {

	public static KeyPairs getKeys(Object keyPair) {
		String a = keyPair.toString().substring(1, keyPair.toString().length() - 1);
		Gson gson = new Gson();
		return gson.fromJson(a, KeyPairs.class);
	}

	public static Token getToken(Object token) {
		String a = token.toString().substring(1, token.toString().length() - 1);
		Gson gson = new Gson();
		return gson.fromJson(a, Token.class);
	}

	public static SignedTransactionInfo getSignedTransactionInfo(Object signedTransactionInfo) {
		String a = signedTransactionInfo.toString();
		Gson gson = new Gson();
		return gson.fromJson(a, SignedTransactionInfo.class);
	}

	public static AddressValidator isKeyValid(Object validator) {
		String a = validator.toString();
		Gson gson = new Gson();
		return gson.fromJson(a, AddressValidator.class);
	}
	
	public static List<AssetTransaction> getStackTrace(List<Object> assetTransactions) {
		List<AssetTransaction> transactions = new ArrayList<AssetTransaction>();
		int i =1;
		if (assetTransactions != null) {
			for (Object assetTransaction : assetTransactions) {
				transactions.add(getTransaction(assetTransaction));
			}
		}
		return transactions;
	}
	
	public static String getOwner(List<Object> assetTransactions) {
		List<AssetTransaction> transactions = new ArrayList<AssetTransaction>();
		String result = "";
		int i =1;
		if (assetTransactions != null) {
			for (Object assetTransaction : assetTransactions) {
				if(i==assetTransactions.size())
					result = formatTransactions(assetTransaction).getAddresses().keySet().toArray()[0].toString();
				i++;
			}
		}
		return result;
	}
	
	private static AssetTransaction formatTransactions(Object assetTransaction) {
		String a = assetTransaction.toString();
		Gson gson = new Gson();
		AssetTransaction res = gson.fromJson(a, AssetTransaction.class);
			if(res.getAddresses().size()==1) {
				return null;
			}
		return res;
	}
	
	private static AssetTransaction getTransaction(Object assetTransaction) {
		String a = assetTransaction.toString();
		Gson gson = new Gson();
		AssetTransaction res = gson.fromJson(a, AssetTransaction.class);
		return res;
	}

}
