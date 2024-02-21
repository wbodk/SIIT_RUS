package uvod;

import java.util.Scanner;

/*
 *  Primer programa koji racuna vrednost broja PI:
 *  PI/4 = 1 - 1/3 + 1/5 - 1/7 + ... 
 *  Autor: Milan Vidakovic
 */
class Pi {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int znak;
    float clan, suma, eps, i;
  
    System.out.printf("Racunam PI sa zadatom tacnoscu. Zadajte tacnost: ");
    eps = sc.nextFloat();
    sc.close();
  
    znak = -1; clan = 1;
    suma = 1; i = 1;
  
    do
    {
      clan = znak / (2 * i + 1);
      suma += clan; // suma = suma + clan;
      znak *= -1;
      i++;
    } while (Math.abs(clan) >= eps);
  
    System.out.printf("Vrednost PI sa tacnoscu %f je: %f\n", eps, 4*suma);
    System.out.printf("Vrednost PI u Javi je: %f", Math.PI);
  }
}