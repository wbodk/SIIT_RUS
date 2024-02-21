package metode;

/*
 *  Primer realizacije funkcije sinus preko razvoja u red.
 *  Autor: Milan Vidakovic
 */

class Sinus {
	public static void main(String[] parametri) {
		double s1 = sinus(45);
		double s2 = Math.sin(Math.PI / 4);
		System.out.printf("sinus(90): %f, sin(PI/2): %f\n", s1, s2);
	}

	static double sinus(double ugao) {
		double x;
		double s, sabirak;
		double znak;
		long stepen;

		x = ugao * Math.PI / 180;
		s = 0;
		stepen = 1;
		znak = 1;
		sabirak = x; // znak * Math.pow(x,stepen) / fakt(stepen);
		while (Math.abs(sabirak) > 1E-7) {
			s = s + sabirak;
			znak = znak * -1;
			stepen = stepen + 2;
			sabirak = znak * Math.pow(x, stepen) / fakt(stepen);
		}
		return s;
	}

	static long fakt(long n) {
		long i, x;
		if (n < 2)
			return 1;

		x = 1;
		for (i = 1; i <= n; i++)
			x = x * i;

		return x;
	}

	/*
	 * static long fakt(long n) { if (n < 2) return 1;
	 * 
	 * return n * fakt(n-1); }
	 */
}