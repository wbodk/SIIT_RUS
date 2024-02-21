package uvod;

import java.util.Scanner;

/*
 *  Primer programa koji racuna ocenu za zadati broj poena.
 *   Poeni	Ocena
 *   95 ... 100	10
 *   85 ... 94	9
 *   75 ... 84	8
 *   65 ... 74	7
 *   55 ... 64	6
 *   0 ... 54	5
 *  Autor: Milan Vidakovic
 */

class Ocena {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int poeni, ocena;

    System.out.print("Unesite broj bodova:");
    poeni = sc.nextInt();
    sc.close();
    if (poeni > 95)
      ocena = 10;
    else if (poeni > 84)
      ocena = 9;
    else if (poeni > 74)
      ocena = 8;
    else if (poeni > 64)
      ocena = 7;
    else if (poeni > 54)
      ocena = 6;
    else
      ocena = 5;
  
    System.out.printf("Ocena za %d bodova je %d", poeni, ocena);
  }
}