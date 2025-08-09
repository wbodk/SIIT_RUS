package unittesting.testng.groups;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test(groups = "postgres-test")
public class TestPostgres {

	public void runPSQL() {
		System.out.println("runPSQL()");
		assertTrue(true);
	}

	public void runPSQL1() {
		System.out.println("runPSQL1()");
		assertTrue(true);
	}
	
}
