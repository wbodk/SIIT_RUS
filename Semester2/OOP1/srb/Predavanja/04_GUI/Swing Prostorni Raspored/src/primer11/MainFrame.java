package primer11;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 4153347503939809550L;

	public MainFrame() {
		setSize(300, 200);

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				stampaj("Bez veze");
				int answer = JOptionPane
						.showConfirmDialog(MainFrame.this,
								"Da li zelite da prekinete sa radom?",
								"Kraj rada", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
				if (answer == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		}); // addWindowListener
	}

	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
		mf.setVisible(true);
	}

		
	public void stampaj(String s) {
		System.out.println(s);
	}
}
