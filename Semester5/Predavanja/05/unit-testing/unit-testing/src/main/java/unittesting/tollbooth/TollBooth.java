package unittesting.tollbooth;

import unittesting.tollbooth.model.Vehicle;

public class TollBooth {
	
	public double payToll(Vehicle v) {
		return v.payToll();
	}
	
	public double payToll(Vehicle[] vehicles) {
		double retVal = 0;
		for (Vehicle v : vehicles) {
			retVal -= payToll(v);
		}
		return retVal;
	}
	
	/**
	 * 
	 * @param vehicles
	 * @return Number of vehicles waiting
	 */
	public int stop(Vehicle[] vehicles) {
		return vehicles.length;
	}
}
