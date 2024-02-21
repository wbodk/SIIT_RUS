package bazapodataka;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BazaStudenata {

	Map<String, Student> mapa = new HashMap<String, Student>();
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
	
	Scanner sc = new Scanner(System.in);
	
	public BazaStudenata(String file) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));

			String line;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				System.out.println(line);
				Student std = parseStudent(line);
				mapa.put(std.getJmbg(), std);
			}
			
			in.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private Student parseStudent(String s) throws NumberFormatException, ParseException {
		String[] tokens = s.split(";");
		Student ret = new Student(
				// JMBG, ime, prezime
				tokens[0].trim(), tokens[1].trim(), tokens[2].trim(),
				// godina upisa, budzet/samofinansiranje
				Integer.parseInt(tokens[3].trim()), tokens[4].trim().equalsIgnoreCase("b"),
				// datum rodjenja
				sdf.parse(tokens[5].trim())
				);
		return ret;
	}

	private void printAllStudents() {
		for (Student s : this.mapa.values()) {
			System.out.println(s);
		}
		
	}

	private void pronadjiStudentaPoJMBGu() {
		System.out.print("Unesite JMBG:");
		String jmbg = sc.nextLine();
		Student std = this.mapa.get(jmbg);
		if (std == null) {
			System.out.println("Nema studenta sa JMBG-om: " + jmbg);
		} else {
			System.out.println(std);
		}
		
	}

	private void pronadjiStudentaPoImenu() {
		System.out.print("Unesite ime:");
		String ime = sc.nextLine();
		for (Student std : this.mapa.values()) {
			if (std.getIme().toUpperCase().contains(ime.toUpperCase())) {
				System.out.println(std);
			}
		}
		System.out.println("KRAJ pretrage");
	}

	public static void main(String[] args) {
		BazaStudenata bs = new BazaStudenata("studenti.txt");
		bs.printAllStudents();
//		bs.pronadjiStudentaPoJMBGu();
		bs.pronadjiStudentaPoImenu();
	}
}
