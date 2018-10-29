package testing;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.junit.Test;

import com.tfg2018.ws.rest.fchain.WalletManager;
import com.tfg2018.ws.rest.object.KeyPairs;
import com.tfg2018.ws.rest.object.Token;
import com.tfg2018.ws.rest.ConsumedObjects.OutsideMessage;
import com.tfg2018.ws.rest.fchain.FchainConst;
import com.tfg2018.ws.rest.fchain.FchainInterface;
import com.tfg2018.ws.rest.fchain.FchainTracer;
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
			String hexBlob = transactionManager.createAndSignRawTransaction(keyPairSender.getAddress(),keyPairSender.getPrivkey(), keyPairReceiver.getAddress(), token.getName());
			transactionManager.sendConfirmedTransaction(hexBlob);
			assert(true);
		}catch(Exception e){
			System.out.println(e);
			assert(false);
		}
	}
	
	@Test
	public void validateAddress() {
		WalletManager walletManager = new WalletManager();
		KeyPairs keyPair;
		try {
			keyPair = walletManager.getNewKeyPair();
			walletManager.validateAddress(keyPair.getPrivkey());
			assert(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			assert(false);
		}

	}
	
	@Test
	public void checkTokenOwner() {
		FchainTracer fchainTracer = new FchainTracer();
		try {
			fchainTracer.getTokenOwner("Lo_que_tengo_entre_las_piernas");
			assert(true);
		} catch (Exception e) {
			System.out.println(e);
			assert(false);

		}
	}
	/*
	@Test
	public void tokentracing() {
		TokenManager tokenManager = new TokenManager();
		WalletManager walletManager = new WalletManager();
		FchainTracer fchainTracer = new FchainTracer();
		try {
			Token token = newToken();
			KeyPairs keyPairSender3 = walletManager.getNewKeyPair();
			transactToken(token, keyPairSender3);
			System.out.println(keyPairSender3.getAddress());
			TimeUnit.SECONDS.sleep(10);
			System.out.println(fchainTracer.getTokenOwner(token.getName()));
			if(keyPairSender3.getAddress().equals(fchainTracer.getTokenOwner(token.getName()))) {
				assert(true);
			}else {
				assert(false);
			}
		}catch(Exception e){
			System.out.println(e);
			assert(false);
		}
	}
	*/
	
	@Test
	public void getAddressBalance() {
		FchainTracer a = new FchainTracer();
		try {
			if(!a.getAddressBalances("1DNEsT74krP7FKEDBENH56a4GSYSAVLaMuXZ5a").isEmpty()) {
				assert(true);
			}else {
				assert(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assert(false);
			e.printStackTrace();
		}
	}
	
	private void transactToken(Token token, KeyPairs keyPairSender3) throws Exception{
		TokenManager tokenManager = new TokenManager();
		WalletManager walletManager = new WalletManager();
		TransactionManager transactionManager = new TransactionManager();
		KeyPairs keyPairSender = walletManager.getNewKeyPair();
		KeyPairs keyPairSender2 = walletManager.getNewKeyPair();
		tokenManager.generateToken(token,keyPairSender.getAddress());
		String hexBlob1 = transactionManager.createAndSignRawTransaction(keyPairSender.getAddress(), keyPairSender.getPrivkey(), keyPairSender2.getAddress(), token.getName());
		transactionManager.sendConfirmedTransaction(hexBlob1);
		String hexBlob2 = transactionManager.createAndSignRawTransaction(keyPairSender2.getAddress(), keyPairSender2.getPrivkey(), keyPairSender3.getAddress(), token.getName());
		transactionManager.sendConfirmedTransaction(hexBlob2);
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
