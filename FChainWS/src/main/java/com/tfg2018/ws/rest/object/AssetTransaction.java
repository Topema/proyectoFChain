package com.tfg2018.ws.rest.object;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssetTransaction {
	private Map<String,Double> addresses;
	private List<Object> items = null;
	private List<Object> data = null;
	private Integer confirmations;
	private String txid;
	private Boolean valid;
	private Integer time;
	private Integer timereceived;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Map getAddresses() {
		return addresses;
	}

	public List<Object> getItems() {
		return items;
	}

	public List<Object> getData() {
		return data;
	}

	public Integer getConfirmations() {
		return confirmations;
	}

	public String getTxid() {
		return txid;
	}

	public Boolean getValid() {
		return valid;
	}

	public Integer getTime() {
		return time;
	}
	public Integer getTimereceived() {
		return timereceived;
	}
	
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}
}
