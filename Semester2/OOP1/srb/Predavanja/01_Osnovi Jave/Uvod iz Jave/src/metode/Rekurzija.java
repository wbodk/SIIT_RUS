package metode;

public class Rekurzija {

	static int f(int i) {
		return f(i);
	}
	public static void main(String[] args) {
		//f(5);
		int i = 4, j = 5, k = 65;
		String s = "tekst";
		//System.out.println("Vrednost promenljive i je: " + i + ", a vrednost promenljive j je: " + j);
		System.out.printf("Vrednost promenljive i je: %+d, a vrednost promenljive j je: %+d, dok je k %c\n<%s>", i, j, k, s);
		double d = 3.1415345234523452345234532;
		System.out.printf("d je: %.18f", d);
	}

}
