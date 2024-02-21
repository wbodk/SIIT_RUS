package primer09;

class Student {
	String index;
	String ime;
	String prezime;
	int godUpisa;
	boolean budzet;

	Student() {
	}

	Student(String index, String ime, String prezime, int godUpisa,
			boolean budzet) {
		this.index = index;
		this.ime = ime;
		this.prezime = prezime;
		this.godUpisa = godUpisa;
		this.budzet = budzet;
	}

	public Object toCell(int col) {
		switch(col) {
		case 0: return index;
		case 1: return ime;
		case 2: return prezime;
		case 3: return godUpisa;
		case 4: return budzet;
		default: return null;
		}
	}

	public void setCell(int col, Object value) {
		switch(col) {
		case 0: index = (String)value;
		break;
		case 1: ime = (String)value;
		break;
		case 2: prezime = (String)value;
		break;
		case 3: godUpisa = (Integer)value;
		break;
		case 4: budzet = (Boolean)value;
		}
	}
}