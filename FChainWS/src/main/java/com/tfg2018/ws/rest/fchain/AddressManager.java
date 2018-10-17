package com.tfg2018.ws.rest.fchain;

import com.google.gson.Gson;
import com.tfg2018.ws.rest.object.AddressValidator;
import com.tfg2018.ws.rest.object.KeyPairs;
import com.tfg2018.ws.rest.utils.CommandTranslator;
import com.tfg2018.ws.rest.utils.GsontoObjectTranslator;

public class AddressManager {
	
	private String walletAddress;
	private KeyPairs nonWalletKeys;
	private String multisignatureAddress;
	private FChainInterface fChainQuerier = new FChainInterface(FChainConst.MULTICHAIN_SERVER_IP,FChainConst.MULTICHAIN_SERVER_PORT, FChainConst.MULTICHAIN_SERVER_LOGIN, FChainConst.MULTICHAIN_SERVER_PWD);
	
	public void generateMultisigAddress() throws Exception{
		this.walletAddress = getNewAddress();
		this.nonWalletKeys = getNewKeyPair();
		this.multisignatureAddress = addMultisigAddress().toString();
	}

	
	public void generateNewMultisigAddress(String pubKey) throws Exception {
		this.multisignatureAddress = addMultisigAddress(pubKey).toString();
	}
	
	private Object addMultisigAddress() throws Exception{
		try {
			String[] keys = {this.walletAddress,  this.nonWalletKeys.getPubkey()};
			return this.fChainQuerier.executeRequest(CommandTranslator.commandToJson("addmultisigaddress", 1,keys));
		}catch(Exception e){
			throw new Exception("Fallo al generar el par de claves");
		}
	}
	
	
	private Object addMultisigAddress(String pubKey) throws Exception{ 
			validateAddress(pubKey);
			this.walletAddress = getNewAddress();
			System.out.println(this.walletAddress);
		try {
			String[] keys = {this.walletAddress,  pubKey};
			return this.fChainQuerier.executeRequest(CommandTranslator.commandToJson("addmultisigaddress", 1,keys));
		}catch(Exception e){
			throw new Exception("Fallo al generar el par de claves");
		}
	}
	
	private String getNewAddress() throws Exception{
		try {
			return this.fChainQuerier.executeRequest(CommandTranslator.commandToJson("getnewaddress")).toString();
		}catch(Exception e) {
			throw new Exception("Error al generar la wallet");
		}
	}
	
	private KeyPairs getNewKeyPair() throws Exception {
		Object keyPair;
		try {
			keyPair = CommandTranslator.formatJson(this.fChainQuerier.executeRequest(CommandTranslator.commandToJson("createkeypairs")));
			return GsontoObjectTranslator.getKeys(keyPair);
		}catch(Exception e) {
			throw new Exception("Error al generar la dirección multifirma");
		}
	}
	
	private void validateAddress(String key) throws Exception{
		Object validator = CommandTranslator.formatJson(this.fChainQuerier.executeRequest(CommandTranslator.commandToJson("validateaddress", key)));
		AddressValidator addressValidator = GsontoObjectTranslator.isKeyValid(validator);
		if(!addressValidator.getIsvalid()) {
			throw new Exception("this address is not valid");
		}
	}
}
