package primer06;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

// Implementacija osluskivaca
public class MyListener implements ActionListener {

	// ActionPerformed je jedina metoda ActionListener interfejsa
	@Override
	public void actionPerformed(ActionEvent ev) {
		JOptionPane.showMessageDialog(null, "Aplikacija ce se ugasiti !",
				"Upozorenje", JOptionPane.WARNING_MESSAGE);
		System.exit(0); // Ovde se smešta kod koji se izvršava u slucaju izbora
						// dugmeta
	}
}