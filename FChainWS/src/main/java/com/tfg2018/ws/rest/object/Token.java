package com.tfg2018.ws.rest.object;

import java.util.Map;
import java.util.HashMap;

public class Token {

	private String name;
	private String issuetxid;
	private Object assetref;
	private Integer multiple;
	private Double units;
	private Boolean open;
	private Map<String, String> details;
	private Double issueqty;
	private Integer issueraw;
	private Boolean subscribed;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Token(String assetName, Map<String, String> details) {
		this.name = assetName;
		this.issueqty = 1.0;
		this.units = 0.1;
		this.issueraw = 0;
		this.details = details;
	}
	
	public Token(String assetName) {
		this.name = assetName;
	}

	public String getName() {
		return name;
	}

	public String getIssuetxid() {
		return issuetxid;
	}

	public Object getAssetref() {
		return assetref;
	}

	public Integer getMultiple() {
		return multiple;
	}

	public Double getUnits() {
		return units;
	}

	public Boolean getOpen() {
		return open;
	}

	public Map<String, String> getDetails() {
		return details;
	}

	public Double getIssueqty() {
		return issueqty;
	}

	public Integer getIssueraw() {
		return issueraw;
	}

	public Boolean getSubscribed() {
		return subscribed;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}
}
