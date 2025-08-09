package unittesting.testng.time;

import org.testng.annotations.Test;

public class TestTimeout {

	@Test(timeOut = 1000) //milliseconds
	public void testThisShouldPass() throws InterruptedException {
		// block test for some time (less than timeout)
		Thread.sleep(800); 
	}

	@Test(timeOut = 1000)
	public void testThisShouldFail() {
		// infinite loop - this test will fail due to timeout
		while (true);
	}

}
