package primer06;

import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipFileTest {

	private static final int BUFFER_LENGTH = 1024;
	private static final int MAX_FILE_LENGTH = 65536;

	public ZipFileTest() {
		byte[] buffer = new byte[BUFFER_LENGTH];
		try {
			ZipFile zf = new ZipFile("test.zip");
			ZipEntry e = zf.getEntry("src/primer06/ZipTest.java");
			InputStream in = zf.getInputStream(e);

			int total = 0;
			int read;
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

			in.close();
			zf.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ZipFileTest();
	}

}
