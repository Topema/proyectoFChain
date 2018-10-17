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
			test.generateMultisigAddress();
			assert(true);
		}catch(Exception e) {
			System.out.print(e);
			assert(false);
		}
	}
	@Test
	public void setNewAddressTest() {
		AddressManager test = new AddressManager();
		try {
			test.generateNewMultisigAddress("0270e6c1ae859c24d04282cbbe4a3f2f4bcdd1a022f71c3d636bb8785e3741090b");
			assert(true);
		}catch(Exception e) {
			System.out.print(e);
			assert(false);
		}
	}
}
