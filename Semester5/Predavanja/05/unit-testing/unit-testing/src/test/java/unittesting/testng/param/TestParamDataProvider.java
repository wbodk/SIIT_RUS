package unittesting.testng.param;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestParamDataProvider {

	@Test(dataProvider = "provideText")
	public void test(String initialText, String expected) {
		Assert.assertEquals(initialText + " with this!", expected);
	}

	@DataProvider(name = "provideText")
	public Object[][] provideData() {

		return new Object[][] { 
			{ "salt", "salt with this!" }, 
			{ "pork", "pork with this!" }, 
			{ "cabbage", "cabbage with this!" }
		};
	}

}
