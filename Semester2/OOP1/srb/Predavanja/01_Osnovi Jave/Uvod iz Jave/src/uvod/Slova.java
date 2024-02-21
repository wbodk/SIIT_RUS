package uvod;

import java.util.Scanner;

/*
 *  Primer programa koji odredjuje da li je 
 *  uneto slovo samoglasnik, suglasnik, cifra ili ostalo
 *  Autor: Milan Vidakovic
 */
 
class Slova {
  public static void main(String[] args) {
    System.out.print("Unesite jedan karakter:");
    Scanner sc = new Scanner(System.in);
    String s = sc.nextLine();
    sc.close();
    char c = s.charAt(0);
    
    if (c == 'a' || c == 'e'|| c == 'i'|| c == 'o'|| c == 'u') {
      System.out.println("Samoglasnik");
    } else if (Character.isDigit(c)) {
      System.out.println("Broj");
    } else if (Character.isLetter(c)) {
      System.out.println("Suglasnik");
    } else
      System.out.println("Ostalo");
  
    System.out.println("KRAJ");
  }
}