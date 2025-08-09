package unittesting.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TestGroup {

	@BeforeGroups(groups = {"database"})
	public void setupDB() {
		System.out.println("setUpDB()");
	}

	@AfterGroups(groups = {"database"})
	public void cleanDB() {
		System.out.println("cleanUpDB()");
	}

	@Test(groups = {"postgres-test"})
	public void runPostgres() {
		System.out.println("runPostgres()");
	}

	@Test(groups = "postgres-test")
	public void runPostgres1() {
		System.out.println("runPostgres1()");
	}

	@Test(groups = "database")
	public void testConnectOracle() {
		System.out.println("testConnectOracle()");
	}

	@Test(groups = "database")
	public void testConnectMySQL() {
		System.out.println("testConnectMySQL");
	}
}
