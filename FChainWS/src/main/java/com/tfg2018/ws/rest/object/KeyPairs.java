package com.tfg2018.ws.rest.object;

import java.util.HashMap;
import java.util.Map;

public class KeyPairs {
	private String address;
	private String pubkey;
	private String privkey;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPubkey() {
		return pubkey;
	}

	public void setPubkey(String pubkey) {
		this.pubkey = pubkey;
	}

	public String getPrivkey() {
		return privkey;
	}

	public void setPrivkey(String privkey) {
		this.privkey = privkey;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
