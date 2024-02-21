package primer09;

import javax.swing.table.AbstractTableModel;

/**
 * Reprezentuje model podataka u tabeli.
 */
class MyTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -5519372712630599241L;
	private String[] columnNames = { "Broj indeksa", "Ime", "Prezime",
			"Godina upisa", "Bud\u017eet/Samofinansiranje" };
	private Object[][] data = {
			{ "E10000", "Milan", "Vidakovi\u0107", 2000, true },
			{ "E10001", "Branko", "Milosavljevi\u0107", 2002, true },
			{ "E10002", "Petar", "Petrovi\u0107", 1995, false },
			{ "E10003", "Marko", "Markovi\u0107", 1990, true }
	};

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	/**
	 * Ako se ova metoda ne redefinise, koristi se default renderer/editor za
	 * celiju. To znaci da, ako je kolona tipa boolean, onda ce se u tabeli
	 * prikazati true/false, a ovako ce se za takav tip kolone pojaviti
	 * checkbox.
	 */
	public Class<?> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	public boolean isCellEditable(int row, int col) {
		// Prva kolona ne moze da se menja
		if (col < 1) {
			return false;
		} else {
			return true;
		}
	}

	public void setValueAt(Object value, int row, int col) {
		System.out.println("Postavio vrednost " + value + " na " + row + ","
				+ col + " (tipa " + value.getClass() + ")");

		data[row][col] = value;
		// prijavimo da smo promenili vrednost u tabeli
		// fireTableCellUpdated(row, col);
	}
}
