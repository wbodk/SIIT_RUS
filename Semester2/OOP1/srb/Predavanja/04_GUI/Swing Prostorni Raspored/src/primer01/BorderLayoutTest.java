package primer01;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BorderLayoutTest extends JFrame {

	private static final long serialVersionUID = -5364461553119050682L;

	public BorderLayoutTest() {
		setTitle("BorderLayout example");

		BorderLayout bl = new BorderLayout();
		// rastojanje izmedju komponenti
		bl.setHgap(10);
		bl.setVgap(10);

		setLayout(bl);

		JButton s = new JButton("Sever");

		/*
		 * BorderLayout postuje visinu severne i juzne komponente i sirinu
		 * istocne i zapadne. Centralnu komponentu razvlaci u svim pravcima i ne
		 * postuje njene min, max i pref. velicine.
		 */

		s.setPreferredSize(new Dimension(100, 100));

		getContentPane().add(s, BorderLayout.NORTH);
		getContentPane().add(new JButton("Jug1"), BorderLayout.SOUTH);
		getContentPane().add(new JButton("Jug2"), BorderLayout.SOUTH);
		// getContentPane().add(new JButton("Istok"), BorderLayout.EAST);
		getContentPane().add(new JButton("Zapad"), BorderLayout.WEST);
		getContentPane().add(new JButton("Centar"), BorderLayout.CENTER);
		
	}

	public static void main(String[] args) {
		BorderLayoutTest b = new BorderLayoutTest();
		b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.pack();
		b.setVisible(true);
	}

}
