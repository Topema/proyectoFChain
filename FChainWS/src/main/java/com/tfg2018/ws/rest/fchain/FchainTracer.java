package com.tfg2018.ws.rest.fchain;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.entity.StringEntity;

import com.tfg2018.ws.rest.object.AddressBalance;
import com.tfg2018.ws.rest.object.AssetTransaction;
import com.tfg2018.ws.rest.utils.CommandTranslator;
import com.tfg2018.ws.rest.utils.GsontoObjectTranslator;

public class FchainTracer {
	
	private FchainInterface fChainQuerier = new FchainInterface(FchainConst.MULTICHAIN_SERVER_IP,
			FchainConst.MULTICHAIN_SERVER_PORT, FchainConst.MULTICHAIN_SERVER_LOGIN, FchainConst.MULTICHAIN_SERVER_PWD);
	
	public List<AddressBalance> getAddressBalances(String address) throws Exception {
		StringEntity request = CommandTranslator.commandToJson("getaddressbalances", address);
		Object object = this.fChainQuerier.executeRequest(request);
		return GsontoObjectTranslator.getAddressBalances((ArrayList<Object>) object);	
	}
	
	public String getTokenOwner(String tokenName) throws Exception {
		StringEntity request = CommandTranslator.commandToJson("listassettransactions", tokenName);
		Object object= this.fChainQuerier.executeRequest(request);
		String result = GsontoObjectTranslator.getOwner((ArrayList<Object>) object);
		return result;
	}
	
	public String getLastTokenOwner(String tokenName) throws Exception {
		StringEntity request = CommandTranslator.commandToJson("listassettransactions", tokenName);
		Object object= this.fChainQuerier.executeRequest(request);
		String result = GsontoObjectTranslator.getLastOwner((ArrayList<Object>) object);
		return result;
	}
	
	public String getTokenInitialOwner(String tokenName) throws Exception {
		StringEntity request = CommandTranslator.commandToJson("listassettransactions", tokenName);
		Object object= this.fChainQuerier.executeRequest(request);
		String result = GsontoObjectTranslator.getInitialOwner((ArrayList<Object>) object);
		return result;
	}
	
	public String getTokenCreator(String tokenName) throws Exception {
		StringEntity request = CommandTranslator.commandToJson("listassettransactions", tokenName);
		Object object= this.fChainQuerier.executeRequest(request);
		String result = GsontoObjectTranslator.getCreator((ArrayList<Object>) object);
		return result;
	}
	
	public List<AssetTransaction> getTokenStackTrace(String tokenName) throws Exception {
		StringEntity request = CommandTranslator.commandToJson("listassettransactions", tokenName);
		Object object= this.fChainQuerier.executeRequest(request);
		return GsontoObjectTranslator.getStackTrace((ArrayList<Object>) object);
	}
	
}
