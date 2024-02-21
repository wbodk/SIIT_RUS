package nizoviistringovi;

import java.util.Scanner;

/*
 *  Primer programa koji racuna sumu unetog niza brojeva.
 *  Autor: Milan Vidakovic
 */
class Suma {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n, i, suma;
		do {
			System.out.print("Unesite broj elemenata niza (maksimalno 20): ");
			n = sc.nextInt();
		} while (n < 0 || n > 20);
		int niz[] = new int[n];

		for (i = 0; i < n; i++) {
			System.out.printf("Unesite el. br. %d: ", (i+1));
			niz[i] = sc.nextInt();
		}
		sc.close();
		
		suma = 0;
		for (i = 0; i < n; i++) {
			suma += niz[i]; // suma = suma + niz[i];
		}

		System.out.printf("Suma niza je: %d", suma);

	}
}