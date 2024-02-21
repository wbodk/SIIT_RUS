package algoritmi;

/*
 *  Binarna pretraga niza.
 *  Ova metoda radi jedino ako je niz uredjen.
 *  Autor: Milan Vidakovic
 */
class SearchB {

	/*
	 * Stampa niz na ekran.
	 */
	static void print(int a[]) {
		int n = a.length;
		int i;

		for (i = 0; i < n; i++) {
			System.out.printf(" %3d    ", i);
		}
		System.out.println();
		for (i = 0; i < n; i++) {
			System.out.printf(" %3d    ", a[i]);
		}
		System.out.println();
	}

	/*
	 * Pretrazuje niz a za zadatim elementom el.
	 */
	static int search(int a[], int el) {
		int n = a.length;
		int d = 0; // donja granica
		int g = n - 1; // gornja granica
		while (d <= g) {
			int s = (d + g) / 2; // sracunamo sredinu niza.
			System.out.printf("donja: %d, gornja: %d, sredina: %d\n", d, g, s);
			if (el > a[s])
				d = s + 1; // trazimo u gornjoj polovini
			else if (el < a[s])
				g = s - 1; // trazimo u donjoj polovini
			else
				return s; // nasli - vracamo poziciju
		}
		return -1; // nismo nasli
	}

	public static void main(String[] args) {
		int niz[] = { 2, 4, 9, 10, 13, 20, 68, 80, 300, 400 };
		int i;

		System.out.println("Niz:");
		print(niz);
		System.out.println();
		i = search(niz, 9);
		System.out.printf("\nRedni broj elementa 9 u nizu je: %d\n\n", i);
		i = search(niz, 25);
		System.out.printf("\nRedni broj elementa 25 u nizu je: %d\n\n", i);
	}
}