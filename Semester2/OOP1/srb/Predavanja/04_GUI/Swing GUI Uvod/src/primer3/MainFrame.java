package primer3;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1114936864794315712L;

	public MainFrame() {
		setSize(300, 300);
		setLocation(300, 300);
		setTitle("My First GUI App");
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