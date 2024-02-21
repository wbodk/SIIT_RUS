package primer05;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectStreamTest {
  public ObjectStreamTest() {
    try {
    	
      Auto auto = new Auto();
      auto.upali();
      auto.kubikaza = 1699;
      auto.marka = "Golf";
      auto.brojBrzina = 5;
//      auto.model = "Dvojka";
//      ObjectOutputStream out = new ObjectOutputStream(
//    		  						new FileOutputStream("objectStream.dat"));
//      out.writeObject(auto);
//      out.close();

      // Sada čitamo iz datoteke.
      ObjectInputStream in = new ObjectInputStream(
    		  						new FileInputStream("objectStream.dat"));
      Object obj = in.readObject(); 
      Auto auto1 = (Auto)obj;
      System.out.println(auto1.radi());
      System.out.println(auto1.kubikaza);
      System.out.println(auto1.marka);
      System.out.println(auto1.brojBrzina);
      System.out.println(auto1.model);
      in.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  
  public static void main(String[] args) {
    new ObjectStreamTest();
  }
}