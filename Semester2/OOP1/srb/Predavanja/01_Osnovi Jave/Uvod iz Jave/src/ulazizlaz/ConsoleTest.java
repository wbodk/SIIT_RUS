package ulazizlaz;

import java.io.Console;

public class ConsoleTest {

	public static void main(String[] args) {
		Console c = System.console();
		c.printf("Unesite korisnicko ime:");
		String ime = c.readLine();
		c.printf("Unesite sifru:");
		String sifra = new String(c.readPassword());

		c.printf("Ime je %s, a sifra je %s", ime, sifra);
	}
}