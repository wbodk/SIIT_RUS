package primer6;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

class MainFrame extends JFrame {
	private static final long serialVersionUID = 8087603199136216708L;

	public MainFrame() {
		// Preuzimamo dimenzije ekrana
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		System.out.println(screenWidth + " x " + screenHeight);

		// Podesavamo dimenzije prozora na polovinu dimenzija ekrana
		setSize(screenWidth / 2, screenHeight / 2);

		// Dodeljujemo ikonu
		Image img = kit.getImage("image1.gif");
		setIconImage(img);
		// Podesavamo naslov
		setTitle("My Second GUI App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
//		mf.setVisible(true);
	}
}
