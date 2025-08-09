package unittesting.testng.depend;

import org.testng.annotations.Test;

public class TestDepend {
	// This test will fail
	@Test
	public void method1() {
		System.out.println("Method 1 is executed");
		throw new RuntimeException();
	}

	@Test
	public void m2() {
		System.out.println("m2");
	}

	@Test(dependsOnMethods = { "method1", "m2" })
	public void method2() {
		System.out.println("Method 2 is executed");
	}
}
