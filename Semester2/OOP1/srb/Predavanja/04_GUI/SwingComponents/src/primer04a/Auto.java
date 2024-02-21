package primer04a;

class Auto {

	private boolean radi;

	Auto() {
		System.out.println("Konstruktor koji automatski pali auto.");
		radi = true;
	}

	Auto(boolean b) {
		System.out
				.println("Konstruktor koji pali/ne_pali auto u zavisnosti od parametra.");
		radi = b;
	}

	void upali() {
		radi = true;
	}

	void ugasi() {
		radi = false;
	}

	boolean daLiRadi() {
		return radi;
	}

	protected void finalize() {
		System.out.println("Obrisan objekat");
	}

	public String toString() {
		if (radi)
			return ("Auto koji radi.");
		else
			return ("Auto koji NE radi.");
	}
}
