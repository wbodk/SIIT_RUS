package metode;

/*
 *  Primer iterativnog resenja funkcije faktorijel.
 *  Autor: Milan Vidakovic
 */
class Fakt1 {

	static int fakt(int n) {
		int i, f = 1;
		for (i = 1; i <= n; i++) {
			f *= i;
		}
		return f;
	}

	public static void main(String[] args) {
		int i = 5;

		System.out.printf("Faktorijel od %d je: %d\n", i, fakt(i));
	}
}