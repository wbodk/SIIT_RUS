package primer06;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipTest {

	private static final int BUFFER_LENGTH = 1024;
	private static final int MAX_FILE_LENGTH = 65536;
	
	public ZipTest() {
		byte[] buffer = new byte[BUFFER_LENGTH];
		try {
			// Kreiramo arhivu "test.zip"
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					"test.zip"));
			// Pripremimo se za čitanje datoteke
			// koju cemo zapakovati.
			BufferedInputStream fin = new BufferedInputStream(
					new FileInputStream("src/primer06/ZipTest.java"));
			// Ubacivanje datoteke u arhivu počinje
			// metodom putNextEntry().
			out.putNextEntry(new ZipEntry("src/primer06/ZipTest.java"));
			// Posle toga moramo da datoteku iščitamo
			// i smestimo u arhivu.
			int read;
			while ((read = fin.read(buffer, 0, BUFFER_LENGTH)) != -1) {
				out.write(buffer, 0, read);
			}
			out.close();
			fin.close();

			// Sada ćemo da otvorimo arhivu i
			// pročitamo iz nje datoteku "ZipTest.java"
			ZipInputStream in = new ZipInputStream(new FileInputStream(
					"test.zip"));
			ZipEntry zipEntry;
			// Prolazimo kroz sve datoteke u arhivi.
			while ((zipEntry = in.getNextEntry()) != null) {
				System.out.println("Extracting file: " + zipEntry.getName());
				System.out.println("isDirectory: " + zipEntry.isDirectory());
				int total = 0;
				byte[] accumulator = new byte[MAX_FILE_LENGTH];
				while ((read = in.read(buffer, 0, BUFFER_LENGTH)) != -1) {
					for (int i = 0; i < read; i++)
						accumulator[total + i] = buffer[i];
					total += read;
				}
				// U nizu bajtova "accumulator" nalazi
				// se raspakovana tekuća datoteka.
				String fileText = new String(accumulator, 0, total, "utf-8");
				System.out.println(fileText);
			}
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ZipTest();
	}

}
