package unittesting.testng.ignore;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIgnore {

	//default enabled=true
	@Test
	public void test1() {
		Assert.assertEquals("here it is", "here it is");
	}

	@Test(enabled = true)
	public void test2() {
		Assert.assertEquals("here it is", "here it is");
	}

	// this test will be ignored
	@Test(enabled = false)
	public void test3() {
		Assert.assertEquals("true", "here it is");
	}

}