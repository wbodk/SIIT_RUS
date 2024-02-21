package nizoviistringovi;

import java.util.Scanner;

/*
 *  Primer programa koji pronalazi najveci element u nizu
 *  brojeva.
 *  Autor: Milan Vidakovic
 */
class Maks {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int niz[] = new int[20];
		int n, i, maks;

		do {
			System.out.print("Unesite broj elemenata niza (maksimalno 20): ");
			n = sc.nextInt();
		} while (n < 0 || n > 20);

		for (i = 0; i < n; i++) {
			System.out.printf("Unesite el. br. %d: ", i);
			niz[i] = sc.nextInt();
		}
		sc.close();

		maks = niz[0];
		for (i = 1; i < n; i++) {
			if (niz[i] > maks)
				maks = niz[i];
		}

		System.out.printf("Najveci element niza je: %d", maks);
	}
}