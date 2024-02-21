package primer07;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 9011025259829190530L;

	public MainFrame() {
		setSize(300, 200);
		getContentPane().add(bOK, BorderLayout.NORTH);

		// Povezivanje sa izvorom dogadaja (dodavanje reakcije izbora dugmeta)
		// anonimna klasa
		
//		ActionListener al = new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent ev) {
//				JOptionPane.showMessageDialog(null,
//						"Aplikacija ce se ugasiti!" + ev.getModifiers(), "Upozorenje",
//						JOptionPane.ERROR_MESSAGE);
//				System.exit(0);
//			}
//		};
//		bOK.addActionListener(al);
		
		
		bOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				JOptionPane.showMessageDialog(null,
						"Aplikacija ce se ugasiti!" + ev.getModifiers(), "Upozorenje",
						JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}
		});
		
	}

	// Elementi na formi su najcesce privatni atributi klase
	private JButton bOK = new JButton("OK");

	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
		mf.setVisible(true);
	}

}
