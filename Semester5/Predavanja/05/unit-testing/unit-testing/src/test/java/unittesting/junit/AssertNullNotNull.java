package unittesting.junit;

import org.junit.Test;

import unittesting.junit.ExampleUnit;
import static org.junit.Assert.*;

public class AssertNullNotNull {

	ExampleUnit myUnit = new ExampleUnit();

	@Test
	public void testGetTheObjectNull() {
		assertNull(myUnit.getTheObject());
	}

	@Test
	public void testGetObjectNotNull() {
		assertNotNull(myUnit.getTheObject());
	}

	@Test
	public void testTrivial() {
		assertNotNull(null);
	}

	@Test
	public void testTrivial2() {
		assertNotNull("Neki tekst");
	}

	@Test
	public void testTrivial3() {
		assertNull(null);
	}

	@Test
	public void testTrivial4() {
		assertNull("Neki tekst");
	}

}
