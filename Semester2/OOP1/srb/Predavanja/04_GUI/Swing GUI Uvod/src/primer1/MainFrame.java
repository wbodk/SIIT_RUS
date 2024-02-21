package primer1;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 3473599216364696648L;

	public MainFrame() {
		setSize(300, 200);
		setLocation(300, 300);
		setTitle("My First GUI App");

		setVisible(true);
	}

	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
//		MainFrame mf2 = new MainFrame();
//		mf.setSize(300, 200);
//		mf.setTitle("Moja prva GI aplikacija");
//		mf.setVisible(true);
	}
}