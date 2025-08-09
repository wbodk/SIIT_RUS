package unittesting.tollbooth;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import unittesting.tollbooth.TollBooth;
import unittesting.tollbooth.model.Car;
import unittesting.tollbooth.model.Truck;
import unittesting.tollbooth.model.Vehicle;

public class TollBoothTest {
	@Test
	public void testStop() {
		TollBooth tb = new TollBooth();

		int actual = tb
				.stop(new Vehicle[] { new Car(), new Car(), new Truck() });

		assertEquals(3, actual);
	}

	@Test
	public void testPayToll() {
		TollBooth tb = new TollBooth();

		assertEquals(
				1480, //expected
				tb.payToll(new Vehicle[] { new Car(), new Car(), new Truck() }), //actual
				1E-6); //epsilon (we consider two float values equal if they differ less than epsilon value)
	}
}
