package unittesting.testng.param;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestParamXML {

	@Test
	// these parameters are loaded from XML file 
	@Parameters({ "dbconfig", "poolsize" })
	public void createConnection(String dbconfig, int poolsize) {

		System.out.println("dbconfig : " + dbconfig);
		System.out.println("poolsize : " + poolsize);
		
		//do something with params. E.g. open database connection

	}

}
