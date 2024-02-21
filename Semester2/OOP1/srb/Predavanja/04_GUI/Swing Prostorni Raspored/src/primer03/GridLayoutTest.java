package primer03;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GridLayoutTest extends JFrame {

	private static final long serialVersionUID = 2767327885683693946L;

	public GridLayoutTest() {
		
		setTitle("GridLayout example");
		
		/*
		 * GridLayout raspoređuje komponente u mreži. Ukoliko se kao broj kolona
		 * navede 0 tada će biti poštovan broj redova a broj kolona će se
		 * odrediti na osnovu ukupnog broja komponenti. Ukoliko se za broj redova
		 * navede 0 tada se poštuje broj kolona dok se broj redova izračunava na
		 * osnovu ukupnog broja komponenti Ukoliko se postave i broj redova i
		 * broj kolona tada se broj kolona ignoriše tj. posmatra se kao da je
		 * postavljen na 0.
		 */

		GridLayout layout = new GridLayout(3, 0);
		layout.setHgap(10);
		layout.setVgap(3);
		setLayout(layout);

		getContentPane().add(new JButton("Prvo"));
		getContentPane().add(new JButton("Drugo"));
		getContentPane().add(new JButton("Trece"));
		getContentPane().add(new JButton("Dugacak tekst"));
		getContentPane().add(new JButton("Veoma dugacak tekst"));
	}

	public static void main(String[] args) {
		GridLayoutTest f = new GridLayoutTest();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}

}
