package algoritmi;

/*
 *  Linearna pretraga niza.
 *  Ova metoda se koristi kada je niz neuredjen.
 *  Autor: Milan Vidakovic
 */
class SearchL {

	/*
	 * Stampa niz na ekran.
	 */
	static void print(int a[]) {
		int n = a.length;
		int i;

		for (i = 0; i < n; i++) {
			System.out.printf(" %02d  ", a[i]);
		}
		System.out.println();
	}

	/*
	 * Linearna pretraga niza. Idemo od prvog do poslednjeg i ako nadjemo,
	 * vratimo redni broj. Ako ne nadjemo, vratimo -1.
	 */
	static int search(int a[], int el) {
		int n = a.length;
		int i;

		for (i = 0; i < n; i++) {
			if (a[i] == el)
				return i;
		}
		return -1;
	}

	public static void main(String[] args) {
		int niz[] = { 7, 4, 9, 10, 1, 2, 16, 8, 3, 4 };
		int i;

		System.out.println("Niz:");
		print(niz);
		System.out.println();
		i = search(niz, 9);
		System.out.printf("Redni broj elementa 9 u nizu je: %d\n", i);
		i = search(niz, 25);
		System.out.printf("Redni broj elementa 25 u nizu je: %d\n", i);
	}
}