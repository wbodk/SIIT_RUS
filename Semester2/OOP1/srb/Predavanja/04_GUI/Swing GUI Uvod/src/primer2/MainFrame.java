package primer2;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -860084444785833089L;

	// Elementi na formi su najcesce privatni atributi klase
	private JButton bOK = new JButton("OK");
	private JButton bCancel = new JButton("Cancel");

	public MainFrame() {
		setSize(300, 200);
		setTitle("My Second GUI App");

		// dodajemo komponente na formu:
//		getContentPane().add(bOK);
//		getContentPane().add(bCancel);
		getContentPane().add(bOK, BorderLayout.NORTH);
		getContentPane().add(bCancel, BorderLayout.SOUTH);
	}

	/** Redefinisano da bi prilikom klika na "X" ugasili aplikaciju */
	@Override
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
		mf.setVisible(true);
	}

}