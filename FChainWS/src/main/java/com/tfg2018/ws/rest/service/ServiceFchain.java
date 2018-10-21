package com.tfg2018.ws.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tfg2018.ws.rest.fchain.FchainConst;
import com.tfg2018.ws.rest.fchain.FchainInterface;

public class ServiceFchain {
	@Path("/JavaRevolutions")
	public class ServiceLoginJR {

		@GET
		@Path("/connect")
		@Produces({ MediaType.APPLICATION_JSON })
		public void Connect() {
			FchainConst fChainConstst = new FchainConst();
			FchainInterface fChain = new FchainInterface(FchainConst.MULTICHAIN_SERVER_IP,
					FchainConst.MULTICHAIN_SERVER_PORT, FchainConst.MULTICHAIN_SERVER_LOGIN,
					FchainConst.MULTICHAIN_SERVER_PWD);
		}
	}
}
