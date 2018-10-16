package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tfg2018.ws.rest.fchain.CommandTranslator;
import com.tfg2018.ws.rest.fchain.FChainConst;
import com.tfg2018.ws.rest.fchain.FChainInterface;

public class FChainConnection {
	
	@Test
	public void test() {
		FChainConst fChainConst = new FChainConst();
		FChainInterface fChain = new FChainInterface(FChainConst.MULTICHAIN_SERVER_IP,FChainConst.MULTICHAIN_SERVER_PORT, FChainConst.MULTICHAIN_SERVER_LOGIN, FChainConst.MULTICHAIN_SERVER_PWD);
		CommandTranslator translator = new CommandTranslator();
		try {
			System.out.print(fChain.executeRequest(translator.commandToJson("getinfo")).toString());
			assert(true);
		}catch(Exception e) {
			assert(false);
		}
	}
}
