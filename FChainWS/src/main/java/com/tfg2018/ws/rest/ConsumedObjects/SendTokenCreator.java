package com.tfg2018.ws.rest.ConsumedObjects;

public class SendTokenCreator {

	private String addressSender;
	private String privKey;
	private String addressReceiver;
	private String tokenName;
	
	public String getAddressSender() {
		return addressSender;
	}
	public void setAddressSender(String addressSender) {
		this.addressSender = addressSender;
	}
	public String getPrivKey() {
		return privKey;
	}
	public void setPrivKey(String privKey) {
		this.privKey = privKey;
	}
	public String getTokenName() {
		return tokenName;
	}
	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}
	public String getAddressReceiver() {
		return addressReceiver;
	}
	public void setAddressReceiver(String addressReceiver) {
		this.addressReceiver = addressReceiver;
	}
}
