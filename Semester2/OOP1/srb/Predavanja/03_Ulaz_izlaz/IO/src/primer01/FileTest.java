package primer01;

import java.io.File;

public class FileTest {
	public FileTest() {
		try {
			System.out.println("Spisak drajvova:");
			File[] roots = File.listRoots();
			for (File r: roots)
				System.out.println(r.getCanonicalPath());
			System.out.println("KRAJ SPISKA");
			
			// Pozicioniramo se na tekući direktorijum.
			File file = new File(".");
			System.out.println(file.getCanonicalPath());
			System.out.println("File.isFile(): " + file.isFile());
			System.out.println("File.isDirectory(): " + file.isDirectory());
			
			// Izlistamo sadržaj tekućeg direktorijuma.
			File[] list = file.listFiles();
			for (File f : list) {
				if (f.isDirectory()) // ako je poddirektorijum
					System.out.println("<" + f.getName() + ">");
			}
			for (File f : list) {
				if (f.isFile()) // ako je datoteka
					System.out.println(f.getName() + " (" + f.length() + " bytes)");
			}
			
			// Izlistamo sadržaj C:\Windows foldera
			file = new File("../../../../Windows");
			System.out.println(file.getCanonicalPath());
			File[] vindovs = file.listFiles();
			for (File f : vindovs) {
				System.out.println(f.getCanonicalPath());
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new FileTest();
	}
}