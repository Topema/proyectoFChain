package com.tfg2018.ws.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tfg2018.ws.rest.ConsumedObjects.OutsideMessage;
import com.tfg2018.ws.rest.ConsumedObjects.CreateTokenStructure;
import com.tfg2018.ws.rest.ConsumedObjects.SendTokenCreator;
import com.tfg2018.ws.rest.fchain.FchainConst;
import com.tfg2018.ws.rest.fchain.FchainTracer;
import com.tfg2018.ws.rest.fchain.TokenManager;
import com.tfg2018.ws.rest.fchain.TransactionManager;
import com.tfg2018.ws.rest.fchain.WalletManager;
import com.tfg2018.ws.rest.object.AddressBalance;
import com.tfg2018.ws.rest.object.AssetTransaction;
import com.tfg2018.ws.rest.object.KeyPairs;
import com.tfg2018.ws.rest.object.Token;

@Path("/Fchain")
public class ServiceFchain {

	private WalletManager walletManager = new WalletManager();
	private TokenManager tokenManager = new TokenManager();
	private FchainTracer fchainTracer = new FchainTracer();
	private TransactionManager transactionManager = new TransactionManager();
	private OutsideMessage response = new OutsideMessage();

	@GET
	@Path("/createKeyPair")
	@Produces({ MediaType.APPLICATION_JSON })
	public KeyPairs createKeyPair() throws Exception {
		KeyPairs response = walletManager.getNewKeyPair();
		walletManager.grantPermission(response.getAddress());
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
	@Path("/getToken")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Token getTokenInfo(OutsideMessage token) throws Exception {
		Token response = tokenManager.getToken(token.getMessage());
		return response;
	}

	@POST
	@Path("/generateNewToken")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Token GenerateNewToken(CreateTokenStructure tokenCreator) throws Exception {
		tokenManager.generateToken(tokenCreator.getToken(), tokenCreator.getAddress());
		Token response = tokenManager.getToken(tokenCreator.getTokenName());
		return response;
	}

	@POST
	@Path("/getTokenOwner")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public OutsideMessage getTokenOwner(OutsideMessage token) throws Exception {
		this.response.setMessage(fchainTracer.getTokenOwner(token.getMessage()));
		return response;
	}
	
	@POST
	@Path("/getInitialTokenOwner")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public OutsideMessage getInitialTokenOwner(OutsideMessage token) throws Exception {
		this.response.setMessage(fchainTracer.getTokenInitialOwner(token.getMessage()));
		return response;
	}
	
	@POST
	@Path("/getLastTokenOwner")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public OutsideMessage getLastTokenOwner(OutsideMessage token) throws Exception {
		this.response.setMessage(fchainTracer.getLastTokenOwner(token.getMessage()));
		return response;
	}

	@POST
	@Path("/getTokenCreator")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public OutsideMessage getTokenCreator(OutsideMessage token) throws Exception {
		this.response.setMessage(fchainTracer.getTokenCreator(token.getMessage()));
		return response;
	}

	@POST
	@Path("/getTokenStackTrace")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<AssetTransaction> getTokenStackTrace(OutsideMessage token) throws Exception {
		List<AssetTransaction> response = fchainTracer.getTokenStackTrace(token.getMessage());
		return response;
	}

	@POST
	@Path("/getAddressBalance")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<AddressBalance> getAddressBalance(OutsideMessage address) throws Exception {
		List<AddressBalance> response = fchainTracer.getAddressBalances(address.getMessage());
		return response;
	}

	@POST
	@Path("/createInstantTransaction")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public OutsideMessage sendToken(SendTokenCreator transact) throws Exception {
		String hexBlob = transactionManager.createAndSignRawTransaction(transact.getAddressSender(),
				transact.getPrivKey(), transact.getAddressReceiver(), transact.getTokenName());
		this.response.setMessage(transactionManager.sendConfirmedTransaction(hexBlob));
		return response;
	}

	@POST
	@Path("/burnToken")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public OutsideMessage burnToken(SendTokenCreator transact) throws Exception {
		String hexBlob = transactionManager.createAndSignRawTransaction(transact.getAddressSender(),
				transact.getPrivKey(), FchainConst.BURN_ADDRESS, transact.getTokenName());
		this.response.setMessage(transactionManager.sendConfirmedTransaction(hexBlob));
		return response;
	}
}
