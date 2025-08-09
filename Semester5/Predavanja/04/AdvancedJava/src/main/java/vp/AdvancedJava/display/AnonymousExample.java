package vp.AdvancedJava.display;

import java.util.ArrayList;
import java.util.Collection;

public class AnonymousExample {
	public static void main(String args[]) {
		Collection<Displayable> items = new ArrayList<>();
		
		
		Displayable country = new Country(1, "Serbia", 7057000); // klasicna klasa koja implementira interfejs
		
		// kreiranje anonimne klase koja implementira interfejs Displayable
		// ova klasa se ne moze vise koristiti za kreiranje novih instanci
		Displayable d1 = new Displayable() {  
			String text = "Novi Sad";
			public void displayData(String prefix) {
				System.out.println(prefix + " " + text);
			}
		};
		
		// isto kao kreiranje anonimne klase, samo koriscenjem lambda izraza
		// kod sa desne strane je lambda izraz. To je implementacija interfejsa Displayable. 
		// Sa leve strane znaka -> je parametar (tip se moze izostaviti. Zagrade takodje ako je jedan parametar)
		// Sa desne strane znaka -> je telo implementirane metode interfejsa
		// ovaj lambda izraz je analogan kodu:
		// new Displayable() {  
		// 		public void displayData(String prefix) {
		//			System.out.println(prefix + " Beograd");
		//		}
		// };	
		Displayable d2 = prefix -> { System.out.println(prefix + " Beograd"); };
		
		items.add(country);
		items.add(d1);
		items.add(d2);
		
		displayCollection(items);
	}
	
	private static void displayCollection(Collection<Displayable> items) {
		for (Displayable d: items) {
			d.displayData("Stavka kolekcije: ");
		}
	}
}
