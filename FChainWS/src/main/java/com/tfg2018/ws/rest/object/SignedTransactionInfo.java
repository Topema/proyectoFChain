package com.tfg2018.ws.rest.object;

import java.util.HashMap;
import java.util.Map;

public class SignedTransactionInfo {
	private String hex;
	private Boolean complete;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getHex() {
		return hex;
	}

	public void setHex(String hex) {
		this.hex = hex;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
}
