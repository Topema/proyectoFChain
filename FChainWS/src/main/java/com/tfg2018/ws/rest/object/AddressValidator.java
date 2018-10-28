package com.tfg2018.ws.rest.object;

import java.util.HashMap;
import java.util.Map;

public class AddressValidator {

	private Boolean isvalid;
	private String address;
	private Boolean ismine;
	private Boolean iswatchonly;
	private Boolean isscript;
	private String account;
	private Boolean _synchronized;
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

	public Boolean getIswatchonly() {
	return iswatchonly;
	}

	public void setIswatchonly(Boolean iswatchonly) {
	this.iswatchonly = iswatchonly;
	}

	public Boolean getIsscript() {
	return isscript;
	}

	public void setIsscript(Boolean isscript) {
	this.isscript = isscript;
	}

	public String getAccount() {
	return account;
	}

	public void setAccount(String account) {
	this.account = account;
	}

	public Boolean getSynchronized() {
	return _synchronized;
	}

	public void setSynchronized(Boolean _synchronized) {
	this._synchronized = _synchronized;
	}

	public Map<String, Object> getAdditionalProperties() {
	return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
	}
}
