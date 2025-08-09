package unittesting.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import unittesting.junit.ExampleUnit;

public class AssertEquals {

    @Test
    public void testConcatenate() {
        ExampleUnit myUnit = new ExampleUnit();

        String result = myUnit.concatenate("one", "two");

        assertEquals("onetwo", result);
    }
}
