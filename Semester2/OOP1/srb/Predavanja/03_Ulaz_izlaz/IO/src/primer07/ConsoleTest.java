package primer07;

import java.io.Console;

public class ConsoleTest {

  public static void main(String[] args) {
    Console c = System.console();
    c.printf("Unesite korisničko ime:");
    String ime = c.readLine();
    c.printf("Unesite sifru:");
    String sifra = new String(c.readPassword());
    
    c.printf("Ime je: [%s], a šifra je: [%s]\n", ime, sifra);
  }
}