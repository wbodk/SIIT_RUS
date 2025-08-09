package vp.AdvancedJava.optional;

import java.util.Optional;

public class OptionalExample {

	public static void main(String[] args) {
		Country country = new Country(1, "Serbia", 7057000);
		
		// dobijanje optional objekta od postojeceg objekta
		Optional<Country> c1 = Optional.of(country);  
		
		Optional<Country> c2 = Optional.empty(); // optional ne sadrzi vrednost
		
		
		System.out.println(c1.isPresent()); // da li u Optional objektu ima vrednost
		System.out.println(c2.isPresent()); // da li u Optional objektu ima vrednost
		
		Country c3 = c1.get(); // preuzimanje vrednosti iz Optional objekta
		Country c4 = c2.orElse(new Country()); // vraca c2, a ako je null, onda vraca new Country()
		
		// provera not null kod klasicnog pristupa
		if (c3 != null) {
			System.out.println(c3.getName());
		}
		
		// provera da li je null ako koristimo optional
		// funkcija ifPresent ce izvrsiti kod ako Optional ima vrednost 
			
		
		City city = new City(21000, "Novi Sad", country);
		Address address = new Address("Bulevar oslobodjenja", 23, city);
		
		// ispis naziva drzave u kojoj se ulica nalazi
		
		// klasican pristup
		String name;		 
		if (address != null && address.getCity() != null && address.getCity().getCountry() != null) {
			name = address.getCity().getCountry().getName();
		} else {
			name = "Nepoznato";
		}
		System.out.println(name);
		
		// isto to koriscenjem Optional
		Optional<Address> a = Optional.of(address); // dobijamo objekat Optional
		name = a.map(Address::getCity) // vraca Optional koji sadrzi grad ili null
			.map(City::getCountry) // vraca Optional koji sadrzi drzavu ili null
			.map(Country::getName) // vraca Optional koji sadrzi naziv drzave ili null
			.orElse("Nepoznato"); // vraca naziv drzave ako nije null ili "Nepoznato" ako je naziv null 
		
		System.out.println(name);
	}

}
