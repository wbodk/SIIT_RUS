package primer15;

import javax.swing.JOptionPane;

class ConfirmDialogExample {
	public static void main(String[] args) {
		int choice = JOptionPane.showConfirmDialog(null,
				"Erase your hard disk?");
		if (choice == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "Disk erased!");
		} else {
			JOptionPane.showMessageDialog(null, "Cancelled.");
		}
	}
}
