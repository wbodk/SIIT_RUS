package primer02;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class JTextAreaTest extends JFrame {

	private static final long serialVersionUID = -3096801368626142680L;

	public JTextAreaTest() {
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());

		ta1 = new JTextArea("Tekst1", 5, 30);
		ta1.addCaretListener(new Reakcija());
		cp.add(ta1);

		ta2 = new JTextArea("Tekst2", 5, 30);
		cp.add(ta2);
	}

	class Reakcija implements CaretListener {
		public void caretUpdate(CaretEvent e) {
			ta2.setText("" + e.getDot() + ", " + e.getMark());
		}
	}

	JTextArea ta1;
	JTextArea ta2;

	public static void main(String[] args) {
		new JTextAreaTest().setVisible(true);
	}
}
