package primer01;

import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JTextFieldTest extends JFrame {

	private static final long serialVersionUID = -8117700488371263336L;
	
	JTextField tf = new JTextField(30);
	JLabel l = new JLabel("Tekst");

	public JTextFieldTest() {
		setSize(400, 200);
		setTitle("Component test");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(l);
		getContentPane().add(tf);

		tf.addKeyListener(new Reakcija());
	}

	/** Rukovalac dogadjajima definisan kao inner klasa */
	class Reakcija extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_F1) {
				if (e.getModifiersEx() == KeyEvent.CTRL_DOWN_MASK)
					l.setText("Pritisnuo taster Ctrl+F1");
			} else if (e.getKeyCode() == KeyEvent.VK_A) {
				l.setText("Pritisnuo taster A");
			} else {
				l.setText("Tekst");
			}
		}
	}

	
	
	public static void main(String[] args) {
		new JTextFieldTest().setVisible(true);
	}
}
