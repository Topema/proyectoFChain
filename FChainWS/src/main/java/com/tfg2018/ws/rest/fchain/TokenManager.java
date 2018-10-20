package com.tfg2018.ws.rest.fchain;

import org.apache.http.entity.StringEntity;

import com.tfg2018.ws.rest.object.Token;
import com.tfg2018.ws.rest.utils.CommandTranslator;
import com.tfg2018.ws.rest.utils.GsontoObjectTranslator;

public class TokenManager {
	
	private FChainInterface fChainQuerier = new FChainInterface(FChainConst.MULTICHAIN_SERVER_IP,FChainConst.MULTICHAIN_SERVER_PORT, FChainConst.MULTICHAIN_SERVER_LOGIN, FChainConst.MULTICHAIN_SERVER_PWD);
	
	public String generateToken(Token token, String address) throws Exception{
		StringEntity request = CommandTranslator.commandToJson("issuefrom", FChainConst.ISSUANCE_ADDRESS,address, token.getName(), token.getIssueqty(), token.getUnits(),token.getIssueraw(),token.getDetails());
		try {
			return CommandTranslator.formatJson(this.fChainQuerier.executeRequest(request));	
		}catch(Exception e) {
			throw new Exception("Token generation fail");
		}
	}
	
	public Token getToken(String tokenName) throws Exception{
		StringEntity request = CommandTranslator.commandToJson("listassets",tokenName); 
		try {
			String resultToken = CommandTranslator.formatJson(this.fChainQuerier.executeRequest(request));
			Token token = GsontoObjectTranslator.getToken(resultToken);
			return token;
		}catch(Exception e) {
			throw new Exception("this token does not exist");
		}
	}

}
