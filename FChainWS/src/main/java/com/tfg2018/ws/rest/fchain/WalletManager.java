package com.tfg2018.ws.rest.fchain;

import org.apache.http.entity.StringEntity;

import com.tfg2018.ws.rest.object.AddressValidator;
import com.tfg2018.ws.rest.object.KeyPairs;
import com.tfg2018.ws.rest.utils.CommandTranslator;
import com.tfg2018.ws.rest.utils.GsontoObjectTranslator;

public class WalletManager {

	private FchainInterface fChainQuerier = new FchainInterface(FchainConst.MULTICHAIN_SERVER_IP,
			FchainConst.MULTICHAIN_SERVER_PORT, FchainConst.MULTICHAIN_SERVER_LOGIN, FchainConst.MULTICHAIN_SERVER_PWD);

	public void grantPermission(String address) throws Exception {
		try {
			this.fChainQuerier.executeRequest(CommandTranslator.commandToJson("grantfrom", FchainConst.ADMIN_ADDRESS , address, "issue,send,receive"));
		} catch (Exception e) {
			throw new Exception("Fallo al otorgar permisos");
		}
	}

	public String getNewAddress() throws Exception {
		try {
			StringEntity request = CommandTranslator.commandToJson("getnewaddress");
			return this.fChainQuerier.executeRequest(request).toString();
		} catch (Exception e) {
			throw new Exception("Wallet generation error");
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
			throw new Exception("Key Pair generation error");
		}
	}

	public String validateAddress(String key) throws Exception {
		StringEntity request = CommandTranslator.commandToJson("validateaddress", key);
		Object validator = CommandTranslator.formatJson(this.fChainQuerier.executeRequest(request));
		AddressValidator addressValidator = GsontoObjectTranslator.isKeyValid(validator);
		if (!addressValidator.getIsvalid()) {
			throw new Exception("this address is not valid");
		}
		return addressValidator.getAddress();
	}

	private void importAddress(String address) throws Exception {
		try {
			StringEntity request = CommandTranslator.commandToJson("importaddress", address);
			this.fChainQuerier.executeRequest(request);
		} catch (Exception e) {
			throw new Exception("Address importation error");
		}
	}
}
