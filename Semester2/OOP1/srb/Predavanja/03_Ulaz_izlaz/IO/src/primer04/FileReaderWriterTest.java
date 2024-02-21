package primer04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class FileReaderWriterTest {
  public FileReaderWriterTest() {
    try {
    	String encoding = "utf-8";
    	
      // FileWriter ne odgovara uvek, jer podrazumeva default character encoding
//      PrintWriter out = new PrintWriter(
//    		  					new FileWriter("testReaderWriter.txt"));

  	PrintWriter out = new PrintWriter(
  							new OutputStreamWriter(
  								new FileOutputStream("testReaderWriter.txt"), encoding));
      
      
      String s = "Unicode string; Уницоде стринг";
      out.println(s);
      out.close();
      

      // Sada citamo iz datoteke.

      // FileReader ne odgovara uvek, jer podrazumeva default character encoding
//      BufferedReader in = new BufferedReader(
//    		  					new FileReader("testReaderWriter.txt"));

  	BufferedReader in = new BufferedReader(
  							new InputStreamReader(
  								new FileInputStream("testReaderWriter.txt"), encoding));

      //String s2 = in.readLine();
      //System.out.println(s2);
      
    	String s2;
    	while((s2 = in.readLine()) != null) {
    	  System.out.println(s2);
    	}
      
      in.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new FileReaderWriterTest();
  }
 }