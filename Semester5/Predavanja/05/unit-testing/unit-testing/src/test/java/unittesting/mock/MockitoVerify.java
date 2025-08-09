package unittesting.mock;

import org.mockito.Mockito;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

import org.mockito.ArgumentMatchers;

public class MockitoVerify {

	@Test
	public void testVerify()  {
	  // create and configure mock
	  ExampleClass test = Mockito.mock(ExampleClass.class);
	  when(test.return12()).thenReturn(43);
	 	  
	  // test some methods
	  test.exampleFunctionality("master");
	  test.return12();
	  test.return12();
	  
	  //verify method calls
	  
	  // verify have we called exampleFunctionality with a parameter starting with "master"
	  verify(test).exampleFunctionality(ArgumentMatchers.startsWith("master"));
	  
	  // verify have we called method return12() twice
	  verify(test, times(2)).return12();
	  
	  //other verifications
	  verify(test, never()).getSignature();
	  verify(test, atLeastOnce()).exampleFunctionality("master");
	  
//	  verify(mock, atLeast(2)).someMethod(params);  
//	  verify(mock, atMost(5)).someMethod(params);
	}
}
