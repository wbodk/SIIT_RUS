package vp.AdvancedJava.stream;

import java.util.ArrayList;
import java.util.List;


public class Country {
	private int id;
	private String name;
	private int population;
	private List<City> cities = new ArrayList<>();
	
	public Country() {
	}
	
	public Country(int id, String name, int population) {
		super();
		this.id = id;
		this.name = name;
		this.population = population;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", population="
				+ population + "]";
	}
	
	public void displayCountry() {
		System.out.println(id + " " + name + " " + population);
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
}
