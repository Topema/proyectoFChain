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
	
	public void generatemultisigAddress() throws Exception{
		this.walletAddress = getNewAddress();
		this.nonWalletKeys = getNewKeyPair();
		this.multisignatureAddress = addMultisigAddress().toString();
	}

	
	public void generateNewMultisigAddress(String privKey) throws Exception {
		this.multisignatureAddress = addMultisigAddress().toString();
	}
	
	private Object addMultisigAddress(String... privKey) throws Exception{
		String secondKey;
		if(privKey.length>0) {
			secondKey = validateAddress(privKey[0]);
			this.walletAddress = getNewAddress();
		}else {
			secondKey = this.nonWalletKeys.getPrivkey();
		}
		try {
			String[] keys = {this.walletAddress,  secondKey};
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
	
	private String validateAddress(String key) throws Exception{
		Object validator = CommandTranslator.formatJson(this.fChainQuerier.executeRequest(CommandTranslator.commandToJson("validateaddress", key)));
		AddressValidator addressValidator = GsontoObjectTranslator.isKeyValid(validator);
		if(addressValidator.getIsvalid()) {
			return addressValidator.getAddress();
		}else {
			throw new Exception("this address is not valid");
		}
	}
}
