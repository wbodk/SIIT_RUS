package unittesting.testng.beforeafter;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGConfiguration {
	
	@BeforeGroups("greetings")
	public void beforeGroups() {
		System.out.println("@BeforeGroups is executed");
	}

	@AfterGroups("greetings")
	public void afterGroups() {
		System.out.println("@AfterGroups is executed");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("@BeforeClass is executed");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("@AfterClass is executed");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("@BeforeMethod is executed");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("@AfterMethod is executed");
	}

	@Test(groups = "greetings")
	public void runTest1() {
		System.out.println("@Test - runTest1");
	}

	@Test
	public void runTest2() {
		System.out.println("@Test - runTest2");
	}
}
