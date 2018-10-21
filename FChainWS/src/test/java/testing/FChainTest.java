package testing;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.Test;

import com.tfg2018.ws.rest.fchain.WalletManager;
import com.tfg2018.ws.rest.object.KeyPairs;
import com.tfg2018.ws.rest.object.Token;
import com.tfg2018.ws.rest.fchain.FChainConst;
import com.tfg2018.ws.rest.fchain.FChainInterface;
import com.tfg2018.ws.rest.fchain.TokenManager;
import com.tfg2018.ws.rest.fchain.TransactionManager;
import com.tfg2018.ws.rest.utils.CommandTranslator;

public class FChainTest {

	@Test
	public void setAddressTest() {
		WalletManager test = new WalletManager();
		try {
			test.getNewAddress();
			assert(true);
		}catch(Exception e) {
			System.out.print(e);
			assert(false);
		}
	}
	/*@Test
	public void setNewAddressTest() {
		AddressManager test = new AddressManager();
		try {
			//test.generateNewMultisigAddress("0270e6c1ae859c24d04282cbbe4a3f2f4bcdd1a022f71c3d636bb8785e3741090b");
			assert(true);
		}catch(Exception e) {
			System.out.print(e);
			assert(false);
		}
	}*/
	
	@Test
	public void issueToken() {
		WalletManager walletManager = new WalletManager();
		TokenManager tokenManager = new TokenManager();
		Token token = newToken();
		String tokenName = token.getName();
		try {
			String address = walletManager.getNewAddress();
			tokenManager.generateToken(token,address);
			token = tokenManager.getToken(tokenName);
			assertEquals(tokenName,token.getName());
			assert(true);
		}catch(Exception e){
			System.out.print(e);
			assert(false);
		}
	}
	
	@Test
	public void rawtransaction() {
		TokenManager tokenManager = new TokenManager();
		WalletManager walletManager = new WalletManager();
		TransactionManager transactionManager = new TransactionManager();
		try {
			KeyPairs keyPairSender = walletManager.getNewKeyPair();
			KeyPairs keyPairReceiver = walletManager.getNewKeyPair();
			Token token = newToken();
			tokenManager.generateToken(token,keyPairSender.getAddress());
			String hexBlob = transactionManager.createAndSignRawTransaction(keyPairSender, keyPairReceiver.getAddress(), token);
			transactionManager.sendConfirmedTransaction(hexBlob);
			assert(true);
		}catch(Exception e){
			System.out.println(e);
			assert(false);
		}
	}
	
	private String randomString() {
		
		int length = 32 ;
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		             + "abcdefghijklmnopqrstuvwxyz"
		             + "0123456789";
		String str = new Random().ints(length, 0, chars.length())
		                         .mapToObj(i -> "" + chars.charAt(i))
		                         .collect(Collectors.joining());
		return str;
	}
	
	private Token newToken() {
		Map <String,String> params = new HashMap<String,String>();
		params.put("param1", "param1");
		params.put("param2", "param2");
		String tokenName = randomString();
		return new Token(tokenName,params);
	}
}
