package nizoviistringovi;

import java.util.Scanner;

/*
 *  Primer programa koji proverava da li je zadati
 *  string palindrom.
 *  Autor: Milan Vidakovic
 */

class Palindr {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s;
		int j;
		boolean flag;

		System.out.print("Unesite string: ");
		s = sc.nextLine();
		sc.close();

		flag = true;
		for (j = 0; j < s.length() / 2; j++) {
			if (s.charAt(j) != s.charAt(s.length() - 1 - j)) {
				flag = false;
				break;
			}
		}

		if (flag)
			System.out.printf("%s je palindrom", s);
		else
			System.out.printf("%s nije palindrom", s);
	}
}