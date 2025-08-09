package unittesting.assertj;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import unittesting.junit.ExampleUnit;

public class AssertThatTest {
	@Test
	public void testThat() {
		assertThat("that").isEqualTo("that");
	}

	@Test
	public void testArrayContent() {
		ExampleUnit eu = new ExampleUnit();

		assertThat(eu.getIntArray()).hasSize(3).contains(1, 2)
				.doesNotContain(4, 5).endsWith(3);
	}
}
