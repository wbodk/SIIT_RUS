package primer03;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CopyFile {
  final int BUFFER_LENGTH = 1024;
  public CopyFile() {
    try {
      FileInputStream in = new FileInputStream("datoteka.txt");
      FileOutputStream out = new FileOutputStream("datoteka2.txt");
      byte[] buffer = new byte[BUFFER_LENGTH];
      int read;
      while((read = in.read(buffer, 0, BUFFER_LENGTH)) != -1) {
      	// obrada učitanog niza bajtova
    	System.out.println(read);
      	out.write(buffer, 0, read);
      }
      in.close();
      out.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new CopyFile();
  }
}