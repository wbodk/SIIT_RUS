package primer00;

import javax.swing.JButton;
import javax.swing.JFrame;

public class NoLayout extends JFrame {
	private static final long serialVersionUID = 7191936660027249340L;

	public NoLayout() {
		setSize(640, 480);
		// Postaviti Layout na null
		getContentPane().setLayout(null); 
		setResizable(false);
		JButton ok = new JButton("Ok"); 
		getContentPane().add(ok); 
		// Postaviti komponente direktno na koordinate
		ok.setBounds(123, 137, 50, 30);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new NoLayout();
	}

}
