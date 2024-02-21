package primer5;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 105604656942743521L;

	public MainFrame() {
		setSize(300, 200);
		setTitle("My First GUI App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
//		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		mf.setVisible(true);
		
//		MainFrame mf2 = new MainFrame();
//		mf2.setVisible(true);
		
	}
}