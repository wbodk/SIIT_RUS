package vp.AdvancedJava.stream;

public class City {
	private int zipCode;
	private String name;
	
	public City(int zipCode, String name) {
		super();
		this.zipCode = zipCode;
		this.name = name;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
