package vp.AdvancedJava.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {
	public static void main(String args[]) {
		List<Country> countries = new ArrayList<Country>();
		Country c1 = new Country(1, "Serbia", 7057000);
		Country c2 = new Country(2, "France", 66810000);
		Country c3 = new Country(3, "Italy", 60800000);
		
		countries.add(c1);
		countries.add(c2);
		countries.add(c3);
		
		System.out.println("Ispis svih drzava");
		countries
			.stream()
			.forEach(c -> c.displayCountry()); // na svaki element primenjuje funkciju
		
		
		System.out.println("\nIspis svih drzava koje imaju broj stanovnika veci od 10 miliona");
		countries
			.stream()
			.filter(c -> c.getPopulation() > 10000000) // vraca stream drzava koje ispunjavaju kriterijum
			.forEach(c -> c.displayCountry());
		
		System.out.println("\nIspis naziva drzava koje imaju broj stanovnika veci od 10 miliona");
		countries
			.stream()
			.filter(c -> c.getPopulation() > 10000000) 
			.map(c -> c.getName()) // vraca stream naziva drzava
			.forEach(name -> System.out.println(name));
		
		// preuzimanje liste imena
		List<String> names = 
			countries
				.stream()
				.filter(c -> c.getPopulation() > 10000000) 
				.map(c -> c.getName()) // vraca stream naziva drzava
				.collect(Collectors.toList());
		
		System.out.println("\nZbir stanovnika u svim drzavama");
		int sumPopulation = 
			countries
				.stream()
				.mapToInt(c -> c.getPopulation()) // vraca stream populacija
				.sum();
		System.out.println("Ukupno stanovnika: " + sumPopulation);
		
		System.out.println("\nIspis gradova u drzavama");
		// dodavanje gradova u drzave
		c1.getCities().add(new City(21000, "Novi Sad"));
		c1.getCities().add(new City(11000, "Beograd"));
		c2.getCities().add(new City(75008, "Paris"));
		c2.getCities().add(new City(13014, "Marseille"));
		c3.getCities().add(new City(30149, "Rome"));
		c3.getCities().add(new City(40121, "Bologna"));
		//ispis gradova za svaku drzavu
		countries
			.stream()
			.flatMap(country -> country.getCities().stream()) // za svaku drzavu vraca stream gradova
			.forEach(city -> System.out.println(city.getZipCode() + " " + city.getName()));
		
		System.out.println("\nIspis koriscenjem method references");
		countries
			.stream()
			.filter(c -> c.getPopulation() > 10000000)
			.forEach(Country::displayCountry);
		
		System.out.println("\nIspis naziva koriscenjem method references");
		countries
			.stream()
			.filter(c -> c.getPopulation() > 10000000)
			.map(Country::getName)
			.forEach(System.out::println);
		
		System.out.println("\nIspis fudbalskih timova");
		countries
			.stream() 
			.map(Country::getName) // vraca stream naziva drzava
			.map(FootballTeam::new) // vraca stream objekata klase FootballTeam kreiranih konstruktorom kojem se prosledi naziv drzave
			.forEach(t -> System.out.println(t.getName())); 
	}
}
