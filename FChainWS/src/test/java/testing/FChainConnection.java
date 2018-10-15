package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tfg2018.ws.rest.fchain.FChainQueryMaker;
import com.tfg2018.ws.rest.service.FChainConst;

public class FChainConnection {

	@Test
	public void test() {
		FChainConst fChainConstst = new FChainConst();
		FChainQueryMaker fChain = new FChainQueryMaker(FChainConst.MULTICHAIN_SERVER_IP,FChainConst.MULTICHAIN_SERVER_PORT, FChainConst.MULTICHAIN_SERVER_LOGIN, FChainConst.MULTICHAIN_SERVER_PWD);
	}

}
