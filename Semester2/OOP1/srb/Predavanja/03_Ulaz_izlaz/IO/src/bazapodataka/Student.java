package bazapodataka;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
	private String jmbg;
	
	private String ime;
	private String prezime;
	
	private int godinaUpisa;
	
	private boolean budzet;
	
	private Date datumRodjenja;
	
	public Student() {
		
	}

	public Student(String jmbg, String ime, String prezime, int godinaUpisa, boolean budzet, Date datumRodjenja) {
		this();
		this.jmbg = jmbg;
		this.ime = ime;
		this.prezime = prezime;
		this.godinaUpisa = godinaUpisa;
		this.budzet = budzet;
		this.datumRodjenja = datumRodjenja;
	}




	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public int getGodinaUpisa() {
		return godinaUpisa;
	}

	public void setGodinaUpisa(int godinaUpisa) {
		this.godinaUpisa = godinaUpisa;
	}

	public boolean isBudzet() {
		return budzet;
	}

	public void setBudzet(boolean budzet) {
		this.budzet = budzet;
	}
	
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jmbg == null) ? 0 : jmbg.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (jmbg == null) {
			if (other.jmbg != null)
				return false;
		} else if (!jmbg.equals(other.jmbg))
			return false;
		return true;
	}

	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");

	@Override
	public String toString() {
		return "Student [jmbg=" + jmbg + ", ime=" + ime + ", prezime=" + prezime + ", godinaUpisa=" + godinaUpisa
				+ ", budzet=" + budzet + ", datumRodjenja=" + sdf.format(datumRodjenja) +"]";
	}
}
