package nizoviistringovi;

import java.util.Scanner;

/*
 *  Primer programa koji racuna srednju vrednost unetog niza brojeva.
 *  Autor: Milan Vidakovic
 */
class SrVr {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int niz[] = new int[20];
		int n, i;
		float srvr;

		do {
			System.out.print("Unesite broj elemenata niza (maksimalno 20): ");
			n = sc.nextInt();
		} while (n < 0 || n > 20);

		for (i = 0; i < n; i++) {
			System.out.printf("Unesite el. br. %d: ", i);
			niz[i] = sc.nextInt();
		}
		sc.close();
		
		srvr = 0;
		for (i = 0; i < n; i++) {
			srvr += niz[i]; // srvr = srvr + niz[i];
		}

		srvr /= n; // srvr = srvr / n;
		System.out.printf("Srednja vrednost niza je: %f", srvr);

	}
}