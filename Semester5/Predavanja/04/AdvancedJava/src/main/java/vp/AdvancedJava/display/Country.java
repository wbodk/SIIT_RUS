package vp.AdvancedJava.display;


public class Country implements Displayable {
	private int id;
	private String name;
	private int population;
	
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

	public void displayData(String prefix) {
		System.out.println(prefix + "---- " + id + " " + name + " " +
			population);
	}
}
