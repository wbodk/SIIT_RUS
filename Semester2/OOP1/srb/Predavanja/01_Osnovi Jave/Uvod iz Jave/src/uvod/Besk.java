package uvod;

/*
 *  Primer lose napisane petlje koja zato postaje beskona�na.
 *  Problem je u tome �to floating point aritmetika nije
 *  dovoljno precizna u nekim slu�ajevima.
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