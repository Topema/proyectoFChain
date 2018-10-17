package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tfg2018.ws.rest.fchain.AddressManager;
import com.tfg2018.ws.rest.fchain.FChainConst;
import com.tfg2018.ws.rest.fchain.FChainInterface;
import com.tfg2018.ws.rest.utils.CommandTranslator;

public class FChainTest {

	@Test
	public void setAddressTest() {
		AddressManager test = new AddressManager();
		try {
			test.generatemultisigAddress();
			assert(true);
		}catch(Exception e) {
			System.out.print(e);
			assert(false);
		}
	}
}
