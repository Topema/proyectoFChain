package com.tfg2018.ws.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tfg2018.ws.rest.fchain.FChainQueryMaker;

public class ServiceFChain {
	@Path("/JavaRevolutions")
	public class ServiceLoginJR {

		@GET
		@Path("/connect")
		@Produces({MediaType.APPLICATION_JSON})
		public void Connect() {
			FChainConst fChainConstst = new FChainConst();
			FChainQueryMaker fChain = new FChainQueryMaker(FChainConst.MULTICHAIN_SERVER_IP,FChainConst.MULTICHAIN_SERVER_PORT, FChainConst.MULTICHAIN_SERVER_LOGIN, FChainConst.MULTICHAIN_SERVER_PWD);
		}
	}
}
