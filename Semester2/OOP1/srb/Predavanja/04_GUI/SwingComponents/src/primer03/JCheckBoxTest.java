package primer03;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class JCheckBoxTest extends JFrame {

	private static final long serialVersionUID = -6584321262042905199L;
	
	JCheckBox cb1 = new JCheckBox("CheckBox1");
	ButtonGroup group = new ButtonGroup();
	JRadioButton rb1 = new JRadioButton("RadioButton1", true);
	JRadioButton rb2 = new JRadioButton("RadioButton2", false);
	JRadioButton rb3 = new JRadioButton("RadioButton3", false);
	JLabel l;
	
	public JCheckBoxTest() {
		setSize(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		l = new JLabel("Tekst");

		cp.add(cb1);
		group.add(rb1);
		group.add(rb2);
		group.add(rb3);
		cp.add(rb1);
		cp.add(rb2);
		cp.add(rb3);

		cb1.addItemListener(new Reakcija());
		Reakcija r = new Reakcija();
		rb1.addItemListener(r);
		rb2.addItemListener(r);
		rb3.addItemListener(r);
		cp.add(l);
	}

	class Reakcija implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			l.setText("Odabrao stavku: "
					+ ((AbstractButton) e.getItem()).getText());
			System.out.println("Odabrao stavku: "
					+ ((AbstractButton) e.getItem()).getText());
		}
	}

	
	
	public static void main(String[] args) {
		new JCheckBoxTest().setVisible(true);
	}
}
