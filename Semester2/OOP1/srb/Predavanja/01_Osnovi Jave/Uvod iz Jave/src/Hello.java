import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

class Hello {
  static void f(int[] a) {
    a[1] = 3;    
  }
  
  static void f2(int[][] a) {
    a[0][0] = 10;
  }

  public static void main(String args[]) throws IOException {
//    int[] n = {1, 2, 3};
//    System.out.println(n[1]);
//    f(n);
//    System.out.println(n[1]);
//    
//    int[][] n2 = {{1, 2, 3}, {4, 5, 6}};
//    System.out.println(n2[0][0]);
//    f2(n2);
//    System.out.println(n2[0][0]);
//    
//    System.out.println("n[0] je: " + n[0] + ", a n2[0][0] je: " + n2[0][0]);
//    System.out.printf("n[0] je: %d, a n2[0][0] je: %d\n", n[0], n2[0][0]);
//    
//    int i = 65;
//    
//    System.out.printf("%d\n", i);
//    System.out.printf("%c\n", i);
//    
//    BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
//    String s = in.readLine(); 
//    System.out.println(s);
//    double dd = Double.parseDouble(s);
//    System.out.println(dd);
//    
//    Scanner sc = new Scanner(System.in); 
//    s = sc.nextLine(); 
//    i = sc.nextInt();
//    double d = sc.nextDouble();
//    
//    System.out.printf("%s, %d, %.8f", s, i, d);
    
    int i = 12456789;
    System.out.printf("%08x\n", i);
    
    System.out.printf("%02x\n", (i & 0x000000ff));
    System.out.printf("%02x\n", (i & 0x0000ff00) >> 8);
    System.out.printf("%02x\n", (i & 0x00ff0000) >> 16);
    System.out.printf("%02x\n", (i & 0xff000000) >> 24);
  }
}