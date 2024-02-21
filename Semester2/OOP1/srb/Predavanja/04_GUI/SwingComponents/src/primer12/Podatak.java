package primer12;

public class Podatak {
	public String naslov;
	public String text;

	public Podatak(String naslov, String text) {
		this.naslov = naslov;
		this.text = text;
	}

	public String toString() {
		return naslov;
	}
}