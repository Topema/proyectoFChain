package com.tfg2018.ws.rest.object;

import java.util.HashMap;
import java.util.Map;

public class AddressValidator {

	private Boolean isvalid;
	private String address;
	private Boolean ismine;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Boolean getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getIsmine() {
		return ismine;
	}

	public void setIsmine(Boolean ismine) {
		this.ismine = ismine;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
