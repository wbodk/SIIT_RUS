package metode;

import java.util.Scanner;

/*
 *  Primer funkcije koja vraca veci od dva prosledjena broja.
 *  Autor: Milan Vidakovic
 */
class Maks {

	static int max(int x, int y) {
		if (x > y)
			return x;
		else
			return y;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a, b;
		int c;

		System.out.print("Unesite \"prvi\" broj:");
		a = sc.nextInt();
		System.out.print("Unesite drugi broj:");
		b = sc.nextInt();
		sc.close();
			
		c = max(a, b);
		System.out.printf("Veci od %d i %d je: %d", a, b, c);
		// System.out.println("Veci od " + a + " i " + b + " je: " + c);
	}
}