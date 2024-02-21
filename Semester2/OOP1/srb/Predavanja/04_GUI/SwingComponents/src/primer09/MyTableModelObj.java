package primer09;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * Reprezentuje model podataka u tabeli.
 */
class MyTableModelObj extends AbstractTableModel {

	private static final long serialVersionUID = -5519372712630599241L;
	
	private String[] columnNames = { "Broj indeksa", "Ime", "Prezime", "Godina upisa", "Bud\u017eet/Samofinansiranje" };
	
	private List<Student> data = new ArrayList<Student>();
	
	Student dummy = new Student("D01", "dummy", "dummich", 1, true);

	public MyTableModelObj() {
		data.add(new Student("E10000", "Milan", "Vidakovi\u0107", 		2000, true ));
		data.add(new Student("E10001", "Branko", "Milosavljevi\u0107",  2002, true));
		data.add(new Student("E10002", "Petar", "Petrovi\u0107", 		1995, false ));
		data.add(new Student("E10003", "Marko", "Markovi\u0107", 		1990, true ));

	}
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getRowCount() {
		return data.size();
	}
	@Override
	public Object getValueAt(int row, int col) {
		Student student = data.get(row);
		return student.toCell(col);
	}

	@Override
	/**
	 * Ako se ova metoda ne redefinise, koristi se default renderer/editor za
	 * celiju. To znaci da, ako je kolona tipa boolean, onda ce se u tabeli
	 * prikazati true/false, a ovako ce se za takav tip kolone pojaviti
	 * checkbox.
	 */
	public Class<?> getColumnClass(int c) {
		return dummy.toCell(c).getClass();
	}
	@Override
	public boolean isCellEditable(int row, int col) {
		// Prva kolona ne moze da se menja
		if (col < 1) {
			return false;
		} else {
			return true;
		}
	}
	@Override
	public void setValueAt(Object value, int row, int col) {
		System.out.println("Postavio vrednost " + value + " na " + row + ","
				+ col + " (tipa " + value.getClass() + ")");
		Student student = data.get(row);
		student.setCell(col, value);
		data.set(row, student);
		// prijavimo da smo promenili vrednost u tabeli
		// fireTableCellUpdated(row, col);
	}
	
	public void obrisiStudenta(int row) {
		data.remove(row);
		fireTableDataChanged();
	}
}
