package algoritmi;

import java.util.Scanner;

/*
 *  Program koji izracunava udeljenost dve zadate tačke
 *  u dvodimenzionalnom koordinatnom sistemu.
 *  Autor: Milan Vidakovic
 */
class Udaljenost {

	static float sracunajUdaljenost(float x1, float y1, float x2, float y2) {
		return (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Unesite x kordinatu prve tacke:");
		float x1 = sc.nextFloat();
		System.out.print("Unesite y kordinatu prve tacke:");
		float y1 = sc.nextFloat();
		System.out.print("Unesite x kordinatu druge tacke:");
		float x2 = sc.nextFloat();
		System.out.print("Unesite y kordinatu druge tacke:");
		float y2 = sc.nextFloat();
		sc.close();

		float udaljenost = sracunajUdaljenost(x1, y1, x2, y2);

		System.out.printf("Udaljenost tacke (%f, %f) od tacke (%f, %f) je: %f",
				x1, y1, x2, y2, udaljenost);
	}
}
