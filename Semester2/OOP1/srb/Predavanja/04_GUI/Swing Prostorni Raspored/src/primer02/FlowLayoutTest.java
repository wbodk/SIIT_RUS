package primer02;

import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;

public class FlowLayoutTest extends JFrame {

	private static final long serialVersionUID = 2428943648250036442L;

	public FlowLayoutTest() {
		setTitle("FlowLayout example");

		FlowLayout layout = new FlowLayout();
		/*
		 * Podrazumevano je da FlowLayout centrira komponente po horizontali. To
		 * se moze promeniti metodom setAlignment
		 */
		layout.setAlignment(FlowLayout.LEFT);
//		layout.setHgap(20);
		setLayout(layout);
		getContentPane().add(new JButton("Prvo"));
		getContentPane().add(new JButton("Drugo"));
		getContentPane().add(new JButton("Trece"));

		/*
		 * Moguce je kreirati nevidljive strut komponente da bi dodatno podesili
		 * rastojanje
		 */
		getContentPane().add(Box.createHorizontalStrut(100));
		getContentPane().add(new JButton("Dugacak tekst"));
		getContentPane().add(new JButton("Veoma dugacak tekst"));

	}

	public static void main(String[] args) {
		FlowLayoutTest b = new FlowLayoutTest();
		b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.pack();
		b.setVisible(true);
	}

}
