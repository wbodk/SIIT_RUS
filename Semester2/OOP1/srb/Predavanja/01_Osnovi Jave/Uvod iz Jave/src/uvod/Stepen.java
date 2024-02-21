package uvod;

import java.util.Scanner;

/*
 *  Primer programa koji racuna proizvoljni stepen
 *  proizvoljnog broja uzastopnim mnozenjem.
 *  Autor: Milan Vidakovic
 */
class Stepen {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int a, n, i, stepen;
    i = 1;
    stepen = 1;
  
    System.out.print("Racunam a na n. Unesite vrednost za a: ");
    a = sc.nextInt();
    System.out.print("Unesite vrednost za n: ");
    n = sc.nextInt();
    sc.close();

    while (i++ <= n)
      stepen *= a; // stepen = stepen * a;
      
    /*
    while (i <= n) {
      stepen = stepen * a;  
      i++;
    }
    */
    System.out.printf("Vrednost %d na %d je: %d", a, n, stepen);
  }
}