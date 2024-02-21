package primer05a;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JListTest extends JFrame {

	Object[] items = { new Auto(), new Auto(false), "Treca opcija", "Treca opcija", "Treca opcija", "Treca opcija", "Treca opcija", "Treca opcija", "Treca opcija", "Treca opcija", "Treca opcija", "Treca opcija", "Treca opcija", "Treca opcija" };
	JList lb = new JList(items);
	JLabel l = new JLabel("Labela");
	
	private static final long serialVersionUID = -8343918671420192701L;
	
	public JListTest() {
		setTitle("JList test");
		setSize(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		c.setListData(items);

		lb.addListSelectionListener(new Reakcija());

		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		JScrollPane sp = new JScrollPane(lb);
		cp.add(sp);
//		cp.add(lb);
		cp.add(l);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class Reakcija implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {//This line prevents double events
				l.setText(lb.getSelectedValue().toString());
				System.out.println("Odabrana stavka: " + lb.getSelectedValue());
			}
		}
	}

	public static void main(String[] args) {
		new JListTest().setVisible(true);
	}
	
}
