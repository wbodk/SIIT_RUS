package primer09;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 9011025259829190530L;

	public MainFrame() {
		setSize(300, 200);
		getContentPane().add(bOK, BorderLayout.NORTH);
		getContentPane().add(bCancel, BorderLayout.SOUTH);

		// Povezivanje sa izvorom dogadaja (dodavanje reakcije izbora dugmeta)
		// anonimna klasa
		bOK.addActionListener(this);
		bCancel.addActionListener(this);
	}

	// Elementi na formi su najcesce privatni atributi klase
	private JButton bOK = new JButton("OK");
	private JButton bCancel = new JButton("Cancel");

	@Override
	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource().equals(bOK)) {
			JOptionPane.showMessageDialog(null, "Aplikacija ce se ugasiti !",
					"Upozorenje", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
		mf.setVisible(true);
	}

}
