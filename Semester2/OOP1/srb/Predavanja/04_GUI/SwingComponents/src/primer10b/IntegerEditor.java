package primer10b;

import java.awt.Component;
import java.awt.Toolkit;

import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


/**
 * Obavlja posao editora za kolonu tipa Integer. Prijavljeno u glavnom programu
 * kodom: jTable1.setDefaultEditor(Integer.class, new IntegerEditor(1973,2004));
 * Ovaj editor se automatski poziva prilikom postavljanja vrednosti u tabeli
 * (metoda setValueAt() TableModel-a.
 */
public class IntegerEditor extends DefaultCellEditor {
	private static final long serialVersionUID = -7467942148088306362L;
	private int minimum, maximum;

	public IntegerEditor(int min, int max) {
		// pozvacemo konstruktor nadklase sa JTextField komponentom kao editorom
		// celije
		super(new JTextField());
		minimum = min;
		maximum = max;
	}

	/**
	 * Redefinisemo ovu metodu da bismo pozvali editor celije (u ovom slucaju to
	 * je JTextField). Sistem automatski poziva ovu metodu da bi se pozvao
	 * odgovarajuci editor celije. Nas posao je da upisemo prosledjenu vrednost
	 * u nasu JTextField komponentu.
	 */
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		JTextField tf = (JTextField) super.getTableCellEditorComponent(table,
				value, isSelected, row, column);
		tf.setText(value.toString());
		return tf;

	}

	/**
	 * Redefinisemo ovu metodu da bismo zavrsili editovanje celije. Ova metoda
	 * se automatski poziva kada editor celije zavrsi editovanje (nakon sto je
	 * zavrsetak potvrdjen metodom stopCellEditing()). Posao ove metode je da
	 * prihvati vrednost iz editora i da je prosledi u tabelu.
	 */
	public Object getCellEditorValue() {
		JTextField tf = (JTextField) getComponent();
		String s = tf.getText();
		if (ispravan(s)) {
			return Integer.parseInt(s);
		} else {
			// ovo ne bi trebalo nikada da se desi zato sto smo se obezbedili od
			// ovoga
			// metodom stopCellEditing()
			System.err.println("getCellEditorValue: neispravna vrednost: " + s);
			return null;
		}
	}

	/**
	 * Redefinisemo ovu metodu da bismo sprecili prihvat neispravne vrednosti.
	 * Ova metoda se automatski poziva kada se korisnik proba da zavrsi
	 * editovanje. Ako je vrednost neispravna, ispisacemo poruku o gresci i
	 * vratiti se nazad u editor.
	 */
	public boolean stopCellEditing() {
		JTextField tf = (JTextField) getComponent();
		if (!ispravan(tf.getText())) {
			greska(tf);
			return false; // ne dozvoljavamo prihvat vrednosti i idemo nazad u
							// editor
		}
		return super.stopCellEditing();
	}

	private boolean ispravan(String s) {
		try {
			int i = Integer.parseInt(s);
			if (i < minimum || i > maximum)
				throw new Exception("Nedozvoljena vrednost celije.");
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	private void greska(JTextField tf) {
		Toolkit.getDefaultToolkit().beep();
		tf.selectAll();
		JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(tf),
				"Vrednost ovog polja mo\u017ee da bude samo celobrojna vrednost izme\u0111u  "
						+ minimum + " i " + maximum, "Greska",
				JOptionPane.ERROR_MESSAGE);
	}
	

}
