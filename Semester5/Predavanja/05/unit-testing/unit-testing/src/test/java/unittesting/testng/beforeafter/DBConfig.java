package unittesting.testng.beforeafter;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class DBConfig {

	@BeforeSuite()
	public void beforeSuite() {
		System.out.println("@BeforeSuite is executed");
	}

	@AfterSuite()
	public void afterSuite() {
		System.out.println("@AfterSuite is executed");
	}

	@BeforeTest()
	public void beforeTest() {
		System.out.println("@BeforeTest is executed");
	}

	@AfterTest()
	public void afterTest() {
		System.out.println("@AfterTest is executed");
	}

}
