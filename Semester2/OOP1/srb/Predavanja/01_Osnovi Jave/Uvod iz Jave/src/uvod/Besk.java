package uvod;

/*
 *  Primer lose napisane petlje koja zato postaje beskonaèna.
 *  Problem je u tome što floating point aritmetika nije
 *  dovoljno precizna u nekim sluèajevima.
 *  Ovo je taj slucaj.
 *  Autor: Milan Vidakovic
 */
class Besk {
  public static void main(String[] args) {
  double x;
  for (x = 0; x != 10; x+=0.1)
    System.out.printf("%f\n" , x);
  }
}