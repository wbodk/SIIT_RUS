package algoritmi;

/*
 *  Sortiranje niza metodom bubble sort.
 *  Autor: Milan Vidakovic
 */
class Bubble {

	/**
	 * Stampa niz na ekranu. Ako su parametri i i j jednaki -1, onda se samo
	 * stampa niz. Ako je barem jedan od njih razlicit od -1, element sa tim
	 * indeksom se prikazuje u uglastim zagradama.
	 */
	static void print(int a[], int i, int j) {
		int n = a.length;
		int k;

		for (k = 0; k < n; k++) {
			if (k == i || k == j)
				System.out.printf("[%d] ", a[k]);
			else
				System.out.printf(" %d  ", a[k]);
		}
		System.out.println();
	}

	/** Sortira zadati niz po algoritmu bubble sort. */
	static void sort(int a[]) {
		int n = a.length;
		int i, j, tmp;
		for (i = n - 1; i > 0; i--) {
			for (j = 0; j < i; j++) {
				// poredimo svaka dva uzastopna elementa
				print(a, j, j + 1); // odstampamo niz pre eventualne zamene
				if (a[j] > a[j + 1]) { // ako je prvi veci od drugog
					// zamenimo ih
					tmp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = tmp;
				}
				print(a, j, j + 1); // odstampamo niz posle eventualne zamene
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int niz[] = { 18, 6, 12, 42, 94, 44, 55, 67 };

		System.out.printf("Nesortirano:\n");
		print(niz, -1, -1);
		System.out.println();
		sort(niz);
		System.out.printf("Sortirano:\n");
		print(niz, -1, -1);
	}
}