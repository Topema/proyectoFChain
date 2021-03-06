package com.tfg2018.ws.rest.fchain;

import org.apache.http.entity.StringEntity;

import com.tfg2018.ws.rest.object.Token;
import com.tfg2018.ws.rest.utils.CommandTranslator;
import com.tfg2018.ws.rest.utils.GsontoObjectTranslator;

public class TokenManager {

	private FchainInterface fChainQuerier = new FchainInterface(FchainConst.MULTICHAIN_SERVER_IP,
			FchainConst.MULTICHAIN_SERVER_PORT, FchainConst.MULTICHAIN_SERVER_LOGIN, FchainConst.MULTICHAIN_SERVER_PWD);

	public void generateToken(Token token, String address) throws Exception {
		StringEntity request = CommandTranslator.commandToJson("issue", address, token.getName(), token.getIssueqty(),
				token.getUnits(), token.getIssueraw(), token.getDetails());
		try {
			CommandTranslator.formatJson(this.fChainQuerier.executeRequest(request));
			subscribeToken(token.getName());
		} catch (Exception e) {
			throw new Exception("Token generation fail");
		}
	}

	public Token getToken(String tokenName) throws Exception {
		StringEntity request = CommandTranslator.commandToJson("listassets", tokenName);
		try {
			String resultToken = CommandTranslator.formatJson(this.fChainQuerier.executeRequest(request));
			Token token = GsontoObjectTranslator.getToken(resultToken);
			return token;
		} catch (Exception e) {
			throw new Exception("this token does not exist");
		}
	}
	
	private void subscribeToken(String tokenName) throws Exception{
		StringEntity request = CommandTranslator.commandToJson("subscribe", tokenName);
		try {
			CommandTranslator.formatJson(this.fChainQuerier.executeRequest(request));
		} catch (Exception e) {
			throw new Exception("Error when subscribing this token");
		}
	}

}
