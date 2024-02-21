package nizoviistringovi;

import java.util.Scanner;

/*
 *  Primer programa koji za uneti string odredjuje broj
 *  pojavljivanja zadatog znaka.
 *  Autor: Milan Vidakovic
 */
class BrPoj {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s;
		char c;
		int i, j;

		System.out.print("Unesite string: ");
		s = sc.nextLine();

		System.out.print("Unesite slovo za pretragu: ");
		c = sc.nextLine().charAt(0);
		sc.close();

		j = 0;
		for (i = 0; i < s.length(); i++) {
			if (s.charAt(i) == c)
				j++;
		}

		System.out.printf("Broj pojavljivanja '%c' u '%s' je %d.", c, s, j);
	}
}