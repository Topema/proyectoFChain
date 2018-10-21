package com.tfg2018.ws.rest.fchain;

import org.apache.http.entity.StringEntity;

import com.google.gson.Gson;
import com.tfg2018.ws.rest.object.AddressValidator;
import com.tfg2018.ws.rest.object.KeyPairs;
import com.tfg2018.ws.rest.utils.CommandTranslator;
import com.tfg2018.ws.rest.utils.GsontoObjectTranslator;

public class WalletManager {

	private String walletAddress;
	private KeyPairs nonWalletKeys;
	private String multisignatureAddress;
	private FchainInterface fChainQuerier = new FchainInterface(FchainConst.MULTICHAIN_SERVER_IP,
			FchainConst.MULTICHAIN_SERVER_PORT, FchainConst.MULTICHAIN_SERVER_LOGIN, FchainConst.MULTICHAIN_SERVER_PWD);

	public void generateMultisigAddress() throws Exception {
		this.walletAddress = getNewAddress();
		this.nonWalletKeys = getNewKeyPair();
		this.multisignatureAddress = addMultisigAddress().toString();
	}

	public KeyPairs getNonWalletKeys() {
		return this.nonWalletKeys;
	}

	public void generateNewMultisigAddress(String pubKey, String privKey) throws Exception {
		this.multisignatureAddress = addMultisigAddress(pubKey, privKey).toString();
	}

	private Object addMultisigAddress() throws Exception {
		try {
			String[] keys = { this.walletAddress, this.nonWalletKeys.getPubkey() };
			return this.fChainQuerier.executeRequest(CommandTranslator.commandToJson("addmultisigaddress", 1, keys));
		} catch (Exception e) {
			throw new Exception("Fallo al generar el par de claves");
		}
	}

	private Object addMultisigAddress(String pubKey, String privKey) throws Exception {
		validateAddress(privKey);
		this.walletAddress = getNewAddress();
		try {
			String[] keys = { this.walletAddress, pubKey };
			return this.fChainQuerier.executeRequest(CommandTranslator.commandToJson("addmultisigaddress", 1, keys));
		} catch (Exception e) {
			throw new Exception("Fallo al generar el par de claves");
		}
	}

	public String getNewAddress() throws Exception {
		try {
			return this.fChainQuerier.executeRequest(CommandTranslator.commandToJson("getnewaddress")).toString();
		} catch (Exception e) {
			throw new Exception("Error al generar la wallet");
		}
	}

	public KeyPairs getNewKeyPair() throws Exception {
		Object keyPair;
		try {
			keyPair = CommandTranslator
					.formatJson(this.fChainQuerier.executeRequest(CommandTranslator.commandToJson("createkeypairs")));
			KeyPairs response = GsontoObjectTranslator.getKeys(keyPair);
			importAddress(response.getAddress());
			return response;
		} catch (Exception e) {
			throw new Exception("Error al generar el par de claves");
		}
	}

	private void validateAddress(String key) throws Exception {
		StringEntity request = CommandTranslator.commandToJson("validateaddress", key);
		Object validator = CommandTranslator.formatJson(this.fChainQuerier.executeRequest(request));
		AddressValidator addressValidator = GsontoObjectTranslator.isKeyValid(validator);
		if (!addressValidator.getIsvalid()) {
			throw new Exception("this address is not valid");
		}
	}

	private void importAddress(String address) throws Exception {
		try {
			StringEntity request = CommandTranslator.commandToJson("importaddress", address, "", false);
			System.out.println("address : " + address);
			this.fChainQuerier.executeRequest(request);
		} catch (Exception e) {
			throw new Exception("Address importation error");
		}
	}
}
