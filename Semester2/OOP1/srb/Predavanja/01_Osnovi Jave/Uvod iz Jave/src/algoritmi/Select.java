package algoritmi;

/*
 *  Sortiranje niza metodom selection sort.
 *  Autor: Milan Vidakovic
 */
class Select {

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

	/** Sortira zadati niz po algoritmu selection sort. */
	static void sort(int a[]) {
		int n = a.length;
		int i, j, tmp;
		for (i = 0; i < n - 1; i++) {
			/*
			 * tekuci el. iz petlje po 'i' poredimo sa svim ostalim elementima
			 * (petlja po 'j')
			 */
			for (j = i + 1; j < n; j++) {
				print(a, i, j); // odstampamo niz pre eventualne zamene
				if (a[i] > a[j]) {
					tmp = a[i];
					a[i] = a[j];
					a[j] = tmp;
				}
				print(a, i, j); // odstampamo niz posle eventualne zamene
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int niz[] = { 44, 55, 12, 42, 94, 18, 6, 67 };

		System.out.printf("Nesortirano:\n");
		print(niz, -1, -1);
		System.out.println();
		sort(niz);
		System.out.printf("Sortirano:\n");
		print(niz, -1, -1);
	}
}