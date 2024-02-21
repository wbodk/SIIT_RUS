package primer06;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -8436413845566897546L;

	public MainFrame() {
		setSize(300, 200);
		getContentPane().add(bOK, BorderLayout.NORTH);

		// Povezivanje sa izvorom dogadaja (dodavanje reakcije izbora dugmeta)
		MyListener listener = new MyListener();
		bOK.addActionListener(listener);
	}

	private JButton bOK = new JButton("OK");

	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
		mf.setVisible(true);
	}

}
