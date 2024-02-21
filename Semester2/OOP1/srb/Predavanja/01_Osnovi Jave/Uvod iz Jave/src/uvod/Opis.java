package uvod;

import java.util.Scanner;

/*
 *  Primer programa koji za unetu srednjoškolsku ocenu ispisuje
 *  tekstualni opis.
 *  Autor: Milan Vidakovic
 */
class Opis {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int ocena;
    System.out.print("Unesite ocenu:");
    ocena = sc.nextInt();
    sc.close();
    switch(ocena)
    {
      case 5: System.out.print("odlican\n"); break;
      case 4: System.out.print("vrlo dobar\n"); break;
      case 3: System.out.print("dobar\n"); break;
      case 2: System.out.print("dovoljan\n"); break;
      case 1: System.out.print("nedovoljan\n"); break;
      default: System.out.print("nekorektna ocena");
    }
  }
}