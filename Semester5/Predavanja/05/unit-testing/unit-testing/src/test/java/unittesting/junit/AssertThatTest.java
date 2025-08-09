package unittesting.junit;

import static org.junit.Assert.*;
// matchers are functions used for values evaluation
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import unittesting.junit.ExampleUnit;

public class AssertThatTest {
	
	@Test
	public void testThat() {
		assertThat("that", is(equalTo("that")));
	}
	
	@Test
	public void testThatSecond() {
		assertThat("123", is("123"));
	}
	
	@Test
	public void testThatThird() {
		assertThat("123", isA(String.class));
	}
	
	@Test
	public void testThatIsA() {
		ExampleUnit eu = new ExampleUnit();
		
		assertThat(eu, isA(ExampleUnit.class));
	}
}
