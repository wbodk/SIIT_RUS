package ulazizlaz;

class PrintTest {

	public static void main(String[] args) {
		int c = 356;
		System.out.printf("celobrojni: %d\n", c);
		System.out.printf("celobrojni: %6d\n", c);
		System.out.printf("celobrojni: %-6d\n", c);
		System.out.printf("celobrojni: %+6d\n", c);
		System.out.printf("celobrojni: %+6d\n", -c);

		System.out.printf("realni: %f\n", 3.141);
		System.out.printf("realni: %6.2f\n", 3.141);
		System.out.printf("realni: %e\n", 3.141);
		System.out.printf("realni: %6.2e\n", 3.141);
		System.out.printf("realni: %g\n", 3.141);

		int a = 1, b = 2, cc = 3;
		System.out.println("a je: " + a + ", b je: " + b + ", cc je: " + cc);
		System.out.printf("a je: %d, b je: %d, cc je: %d", a, b, cc);
	}
}