package primer02;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileStreamTest {
	public FileStreamTest() {
		try {
			DataOutputStream out = new DataOutputStream(
										new BufferedOutputStream(
												new FileOutputStream(
														new File("testStream.dat"))));
			out.writeLong(123456789L);
			out.writeBoolean(true);
			out.writeFloat(3.14f);
			// Isprazni bafere.
			out.flush();
			out.close();

			// Sada čitamo iz datoteke.
			DataInputStream in = new DataInputStream(
									new BufferedInputStream(
											new FileInputStream(
													new File("testStream.dat"))));
			long l = in.readLong();
			boolean b = in.readBoolean();
			float f = in.readFloat();
			System.out.println(l);
			System.out.println(b);
			System.out.println(f);
			in.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new FileStreamTest();
	}
}