package primer08ini;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class INIFile {

	/**
	 * Konstruiše INIFile objekat koji čita datu INI datoteku.
	 * 
	 * @param filename
	 *            Naziv INI datoteke.
	 */
	public INIFile(String filename) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(filename));
			readINI(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Iščitava datu INI datoteku i puni asocijativnu mapu spiskom pronađenih sekcija.
	 * @param in Reader koji je dobijen otvaranjem date datoteke.
	 */
	private void readINI(BufferedReader in) {
		String line, key, value;
		String currentCategory = "default";
		HashMap<String, String> currentMap = new HashMap<String, String>();
		categories.put(currentCategory, currentMap);
		try {
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.startsWith(";"))
					continue;
				if (line.charAt(0) == '[') {
					currentCategory = line.substring(1, line.length() - 1);
					currentMap = new HashMap<String, String>();
					categories.put(currentCategory, currentMap);
				} else {
					int index = line.indexOf("=");
					if (index != -1) {
						key = line.substring(0, index).trim();
						value = line.substring(index + 1).trim();
						currentMap.put(key, value);
					}
//					String[] keyValue = line.split("=");
//					if (keyValue.length > 0) {
//						key = keyValue[0].trim();
//						value = keyValue[1].trim();
//						currentMap.put(key, value);
//					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Vraća vrednost datog parametra u obliku stringa.
	 * 
	 * @param category
	 *            Kategorija (sekcija) u kojoj se nalazi parametar
	 * @param key
	 *            Naziv parametra
	 * @return String koji sadrži vrednost parametra
	 */
	public String getString(String category, String key, String defaultValue) {
		HashMap<String, String> hm = categories.get(category);
		if (hm == null)
			return defaultValue;
		else {
			String value = hm.get(key);
			if (value != null)
				return value;
			else
				return defaultValue;
		}
	}

	/**
	 * Vraća vrednost datog parametra u obliku integera.
	 * 
	 * @param category
	 *            Kategorija (sekcija) u kojoj se nalazi parametar
	 * @param key
	 *            Naziv parametra
	 * @return Integer vrednost parametra
	 */
	public int getInt(String category, String key, int defaultValue) {
		HashMap<String, String> hm = categories.get(category);
		if (hm == null)
			return defaultValue;
		else {
			String value = hm.get(key);
			if (value == null) {
				return defaultValue;
			}
			try {
				return (Integer.parseInt(value));
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
				return defaultValue;
			}

		}
	}
	
	/**
	 * Vraća vrednost datog parametra u obliku double broja.
	 * 
	 * @param category
	 *            Kategorija (sekcija) u kojoj se nalazi parametar
	 * @param key
	 *            Naziv parametra
	 * @return Integer vrednost parametra
	 */
	public double getDouble(String category, String key, double defaultValue) {
		HashMap<String, String> hm = categories.get(category);
		if (hm == null)
			return defaultValue;
		else {
			String value = hm.get(key);
			if (value == null) {
				return defaultValue;
			}
			try {
				return (Double.parseDouble(value));
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
				return defaultValue;
			}

		}
	}

	/**
	 * Hash mapa koja sadrži kategorije (sekcije). Hash ključ je naziv
	 * kategorije (string), a vrednost je hash mapa koja sadrži parove
	 * (parametar, vrednost).
	 */
	private HashMap<String, HashMap<String, String>> categories = new HashMap<String, HashMap<String, String>>();
}
