package com.tfg2018.ws.rest.utils;

import com.google.gson.Gson;
import com.tfg2018.ws.rest.object.AddressValidator;
import com.tfg2018.ws.rest.object.KeyPairs;
import com.tfg2018.ws.rest.object.Token;

public class GsontoObjectTranslator {
	
	public static KeyPairs getKeys(Object keyPair) {
		String a = keyPair.toString().substring(1,keyPair.toString().length()-1);
		Gson gson = new Gson();
		return gson.fromJson(a, KeyPairs.class);	
	}
	
	public static Token getToken(Object token) {
		String a = token.toString().substring(1,token.toString().length()-1);
		Gson gson = new Gson();
		return gson.fromJson(a, Token.class);	
	}
	
	public static AddressValidator isKeyValid(Object validator) {
		String a = validator.toString().substring(1,validator.toString().length()-1);
		Gson gson = new Gson();
		return gson.fromJson(a, AddressValidator.class);	
	}

}
