package unittesting.junit;
import java.util.concurrent.ThreadLocalRandom;

public class ExampleUnit {
	
	String[] anArray;

	public ExampleUnit(String[] anArray) {
		super();
		this.anArray = anArray;
	}
	
	public int[] getIntArray() {
		return new int[] {1, 2, 3};
	}

	public ExampleUnit() {
		super();
	}

	public String[] getAnArray() {
		return anArray;
	}

	public void setAnArray(String[] anArray) {
		this.anArray = anArray;
	}
	
	public String[] getTheStringArray() {
		String[] retArray = new String[anArray.length];
		
		for (int i = 0; i < anArray.length; i++) {
			retArray[i] = anArray[i] + " with some more text.";
		}
		
		return retArray;
	}
	
	public String concatenate(String s1, String s2) {
		return s1 + s2;
	}
	
	public Boolean getTheBoolean() {
		
		// sa 50% sanse vraca true ili false 
		if (ThreadLocalRandom.current().nextInt(0, 10) > 5) {
			return true;
		}
		
		return false;
	}
	
	public Object getTheObject() {
		
		// sa 50% sanse vrati null i neki objekat
		if (ThreadLocalRandom.current().nextInt(0, 10) > 5) {
			return new Object();
		}
		
		return null;
	}
}
