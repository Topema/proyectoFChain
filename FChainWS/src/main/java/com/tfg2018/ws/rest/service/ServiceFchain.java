package com.tfg2018.ws.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tfg2018.ws.rest.fchain.TokenManager;
import com.tfg2018.ws.rest.fchain.WalletManager;
import com.tfg2018.ws.rest.object.KeyPairs;
import com.tfg2018.ws.rest.object.Token;
import com.tfg2018.ws.rest.object.TokenCreator;

@Path("/Fchain")
public class ServiceFchain {

	private WalletManager walletManager = new WalletManager();
	TokenManager tokenManager = new TokenManager();
	
		@GET
		@Path("/createKeyPair")
		@Produces({ MediaType.APPLICATION_JSON })
		public KeyPairs createKeyPair() throws Exception {
			
			KeyPairs response = walletManager.getNewKeyPair();
			return response;
		}
		
		@POST
		@Path("/validateAddress")
		@Consumes({ MediaType.APPLICATION_JSON })
		@Produces({ MediaType.APPLICATION_JSON })
		public KeyPairs validateAddress(KeyPairs privKey) throws Exception {
			String address = walletManager.validateAddress(privKey.getPrivkey());
			privKey.setAddress(address);
			return privKey;
		}
		
		@POST
		@Path("/generateNewToken")
		@Consumes({ MediaType.APPLICATION_JSON })
		@Produces({ MediaType.APPLICATION_JSON })
		public Token GenerateNewToken(TokenCreator tokenCreator) throws Exception {
			Token token = new Token(null, null);
			String address = "";
			tokenManager.generateToken(token,address);
			Token response = tokenManager.getToken(token.getName());
			return response;
		}
}
