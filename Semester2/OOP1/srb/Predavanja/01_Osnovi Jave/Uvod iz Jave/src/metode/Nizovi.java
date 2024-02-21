package metode;

class Nizovi {
	static void f(int[] a) {
		a[1] = 3;
	}

	static void f2(int[][] a) {
		a[0][0] = 10;
	}

	public static void main(String args[]) {
		int[] n = { 1, 2, 3 };
		System.out.println(n[1]);
		f(n);
		System.out.println(n[1]);

		int[][] n2 = { { 1, 2, 3 }, { 4, 5, 6 } };
		System.out.println(n2[0][0]);
		f2(n2);
		System.out.println(n2[0][0]);
	}
}