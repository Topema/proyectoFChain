package com.tfg2018.ws.rest.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.tfg2018.ws.rest.fchain.FchainConst;
import com.tfg2018.ws.rest.object.AddressBalance;
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
		if (assetTransactions != null) {
			for (Object assetTransaction : assetTransactions) {
				transactions.add(getTransaction(assetTransaction));
			}
		}
		return transactions;
	}
	
	public static List<AddressBalance> getAddressBalances(List<Object> addressBalances) {
		System.out.println(addressBalances);
		List<AddressBalance> balances = new ArrayList<AddressBalance>();
		if(addressBalances != null) {
			for(Object addressBalance :addressBalances) {
				balances.add(getBalance(addressBalance));
			}
		}
		return balances;
	}
	
	public static String getOwner(List<Object> assetTransactions) {
		String result = "";
		if (assetTransactions != null) {
			int i = 1;
			for (Object assetTransaction : assetTransactions) {
				if(i==0) {
					try {
						result = formatTransactions(assetTransaction).getAddresses().keySet().toArray()[1].toString();
					}catch (Exception e){}
				} else {
					result = formatTransactions(assetTransaction).getAddresses().keySet().toArray()[0].toString();
				}
			}
		}
		return result;
	}
	
	public static String getLastOwner(List<Object> assetTransactions) {
		String address = "";
		if (assetTransactions != null) {
			for (Object assetTransaction : assetTransactions) {
				address = formatTransactions(assetTransaction).getAddresses().keySet().toArray()[0].toString();
				if(address.equals(FchainConst.BURN_ADDRESS)) {
					return formatTransactions(assetTransaction).getAddresses().keySet().toArray()[1].toString();
				}
			}
		}
		return address;
	}
	
	public static String getInitialOwner(List<Object> assetTransactions) {
		if (assetTransactions != null) {
			int i = 0;
			for (Object assetTransaction : assetTransactions) {
				if(i == 1) {
					return formatTransactions(assetTransaction).getAddresses().keySet().toArray()[0].toString();
				} else {
					i++;
				}
			}
		}
		return "";
	}
	
	public static String getCreator(List<Object> assetTransactions) {
		String result = "";
		if (assetTransactions != null) {
			for (Object assetTransaction : assetTransactions) {
					return formatTransactions(assetTransaction).getAddresses().keySet().toArray()[0].toString();
			}
		}
		return result;
	}
	
	private static AssetTransaction formatTransactions(Object assetTransaction) {
		String a = assetTransaction.toString();
		Gson gson = new Gson();
		AssetTransaction res = gson.fromJson(a, AssetTransaction.class);
		return res;
	}
	
	private static AssetTransaction getTransaction(Object assetTransaction) {
		String a = assetTransaction.toString();
		Gson gson = new Gson();
		AssetTransaction res = gson.fromJson(a, AssetTransaction.class);
		return res;
	}
	
	private static AddressBalance getBalance(Object addressBalance) {
		String a = addressBalance.toString();
		Gson gson = new Gson();
		AddressBalance res = gson.fromJson(a, AddressBalance.class);
		return res;
	}

}
