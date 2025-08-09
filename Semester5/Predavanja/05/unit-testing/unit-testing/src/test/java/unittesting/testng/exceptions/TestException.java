package unittesting.testng.exceptions;

import java.util.ArrayList;

import org.testng.annotations.Test;

public class TestException {

	@Test(expectedExceptions = IndexOutOfBoundsException.class)
	public void arrayListAccessWithException() {
		ArrayList<String> alist = new ArrayList<String>();
		alist.add("first");
		alist.add("second");
		alist.add("third");
		
		//trying to get unexisting fourth element
		alist.get(3);
	}
	
}
