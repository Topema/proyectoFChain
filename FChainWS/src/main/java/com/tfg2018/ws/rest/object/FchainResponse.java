package com.tfg2018.ws.rest.object;

import com.google.gson.internal.LinkedTreeMap;

public class FchainResponse {

	Object result = null;
	LinkedTreeMap<String, Object> error;
	String id = null;

	public Object getResult() {
		return result;
	}

	public LinkedTreeMap<String, Object> getError() {
		return error;
	}

	public String getId() {
		return id;
	}
}
