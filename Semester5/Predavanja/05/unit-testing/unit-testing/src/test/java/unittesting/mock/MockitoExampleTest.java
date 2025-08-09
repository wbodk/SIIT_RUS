package unittesting.mock;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Iterator;

import org.mockito.Mockito;
import org.testng.annotations.Test;

public class MockitoExampleTest {

	@Test
	public void testMultipleVals() {
		// create mock object
		ExampleClass test = Mockito.mock(ExampleClass.class);

		// define what happens when exampleFunctionality() method is invoked
		when(test.exampleFunctionality("world")).thenReturn("Hello mock!");

		// use mock in the test
		assertEquals(test.exampleFunctionality("world"), "Hello mock!");
	}

	@Test
	public void testMoreThanOneReturnValue() {
		// mocking existing Java class Iterator
		Iterator i = Mockito.mock(Iterator.class);

		when(i.next()).thenReturn("Hello").thenReturn(" ").
			thenReturn("testers!");
		String r1 = (String) i.next();
		String r2 = (String) i.next();
		String r3 = (String) i.next();
		String result = r1 + r2 + r3;

		assertEquals("Hello bug-seeders!", result);
	}

	@Test
	public void testReturnValueDependentOnMethodParameter() {
		// mocking existing Java class Comparable
		Comparable c = Mockito.mock(Comparable.class);
		
		when(c.compareTo("some text")).thenReturn(1);
		when(c.compareTo("some other text")).thenReturn(2);

		assertEquals(2, c.compareTo("some other text"));
	}
	
	@Test
	public void testReturnValueInDependentOnMethodParameter()  {
	  Comparable c = mock(Comparable.class);
	  
	  // return 0 for any int parameter
	  when(c.compareTo(anyInt())).thenReturn(0);
	  
	  assertEquals(0, c.compareTo(9));
	}
	
	@Test(expectedExceptions = IOException.class) //we expect IOException during test execution
	public void testForIOException() throws IOException {
	  // create and configure mock object
	  ExampleClass mock = mock(ExampleClass.class);
	  //it throws IOException when the doSomethingRisky() method is called
	  doThrow(new IOException()).when(mock).doSomethingRisky();
	  
	  // call mocked method. It will throw IOException 
	  mock.doSomethingRisky();
	} 


}
