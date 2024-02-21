package uvod;

import java.util.Scanner;

/*
 *  Primer programa koji konvertuje stepene Celzijusa u Farenhajte:
 *  tF = tC * 1,80 + 32. 
 *  Autor: Milan Vidakovic
 */
class Celz {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    float temp_C, temp_F;
    System.out.print("Unesite temperaturu u Celizujusovim stepenima:");
    temp_C = sc.nextFloat();
    sc.close();
    temp_F = temp_C * 1.8f + 32f;
    System.out.printf("Temperatura po Farenhajtu je: %f\n", temp_F);
  }
}