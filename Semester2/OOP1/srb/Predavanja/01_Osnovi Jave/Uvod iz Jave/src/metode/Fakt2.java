package metode;

/*
 *  Primer rekurzivnog resenja funkcije faktorijel.
 *  Autor: Milan Vidakovic
 */
class Fakt2 {

	static int fakt(int n) {
		if (n < 2)
			return 1;

		return n * fakt(n - 1);
	}

	public static void main(String[] args) {
		int i = 5;

		System.out.printf("Faktorijel od %d je: %d\n", i, fakt(i));
	}
}