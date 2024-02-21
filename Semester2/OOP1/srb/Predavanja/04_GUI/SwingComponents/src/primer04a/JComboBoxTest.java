package primer04a;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class JComboBoxTest extends JFrame {
	Object[] items = { new Auto(), new Auto(false), "Treca opcija" };
	JComboBox c = new JComboBox();
	JLabel l = new JLabel("Labela");
	
	public JComboBoxTest() {
		setTitle("JComboBox test");
		setSize(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// napuni combo box stavkama
		/*
		 * Napomena: Za prikazivanje naziva stavke u combo boxu koristi se
		 * toString metod objekta koji se dodaje kao item.
		 */
		for (int i = 0; i < items.length; i++)
			c.addItem(items[i]);

		c.addItemListener(new Reakcija());

		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(c);
		cp.add(l);
	}

	class Reakcija implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			/*
			 * Napomena: getSelectedItem() vraca kompletan objekat koji je
			 * selektovan, pa se isti dalje moze koristiti.
			 */
			System.out.println(c.getSelectedItem());
			if (e.getSource() instanceof JComboBox)
				l.setText(c.getSelectedItem().toString());
		}
	}

	
	
	public static void main(String[] args) {
		new JComboBoxTest().setVisible(true);
	}
}
