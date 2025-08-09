package unittesting.junit;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import unittesting.junit.ExampleUnit;

public class AssertSameNotSame {

	ExampleUnit myUnit = new ExampleUnit();

	@Test
	public void testGetTheSameObjectSame() {

		ExampleUnit myUnit2 = new ExampleUnit();

		assertNotSame(myUnit, myUnit2);

		myUnit2 = myUnit;
		assertSame(myUnit, myUnit2);
	}

	@Test
	public void testSameString() {
		assertSame("testing", "testing"); // Java compiler links equal string literals
									// to the same object. So, these two strings
									// are a single object

		assertNotSame("testing", new String("testing")); // these two strings are
													// different objects
	}
}
