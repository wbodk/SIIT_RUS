package unittesting.junit;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import unittesting.junit.ExampleUnit;

public class AssertArrays {
	
    @Test
    public void testGetTheStringArray() {
    	
        ExampleUnit myUnit = new ExampleUnit();
        myUnit.setAnArray(new String[] {"one", "two", "three"});

        String[] expectedArray = {"one with some more text.", "two with some more text.",
        							"three with some more text."};

        String[] resultArray =  myUnit.getTheStringArray();

        assertArrayEquals(expectedArray, resultArray);
    }

}
