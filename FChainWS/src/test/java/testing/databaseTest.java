package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tfg2018.ws.rest.database.DatabaseManager;

public class databaseTest {

	@Test
	public void test() {
		DatabaseManager database =new DatabaseManager();
		try {
			database.MySQLConnection();
			assert(true);
		}catch(Exception e) {
			assert(false);
		}
	}
	
	

}
