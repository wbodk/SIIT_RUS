package vp.AdvancedJava.optional;

public class City {
	private int zipCode;
	private String name;
	Country country;
	
	public City(int zipCode, String name, Country country) {
		super();
		this.zipCode = zipCode;
		this.name = name;
		this.country = country;
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
