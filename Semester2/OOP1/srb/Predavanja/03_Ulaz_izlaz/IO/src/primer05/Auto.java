package primer05;

import java.io.Serializable;

public class Auto implements Serializable {
	
	private static final long serialVersionUID = 3287628319422332382L;

	private boolean upaljen;
	
	public int kubikaza;
	
	public String marka;
	
	public int brojBrzina;
	
	public String model;

	public Auto() {
		upaljen = false;
	}

	public void upali() {
		upaljen = true;
	}

	public boolean radi() {
		return upaljen;
	}
}