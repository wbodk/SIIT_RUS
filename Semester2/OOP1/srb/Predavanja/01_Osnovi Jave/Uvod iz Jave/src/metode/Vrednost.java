package metode;

/*
 *  Primer prenosa parametra po vrednosti.
 *  Autor: Milan Vidakovic
 */

class Vrednost {

	static void f(int i) {
		i = 3;
	}

	public static void main(String[] args) {
		int i = 5;
		f(i);
		System.out.printf("%d", i);
	}
}
