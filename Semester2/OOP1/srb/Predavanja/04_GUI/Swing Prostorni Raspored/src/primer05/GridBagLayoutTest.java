package primer05;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GridBagLayoutTest extends JFrame {

	private static final long serialVersionUID = -6658453665732000699L;

	public GridBagLayoutTest() {

		setTitle("GridBagLayout example");

		/*
		 * Layout manager.
		 */
		GridBagLayout gbl = new GridBagLayout();

		/*
		 * Objekat koji odredjuje svojstva komponente koja se dodaje u
		 * GridBagLayout raspored.
		 */
		GridBagConstraints gbc = new GridBagConstraints();

		getContentPane().setLayout(gbl);

		// GridBagLayout deli contejner na mrezu redova i kolona.

		/*
		 * Odredjuju red, odnosno kolonu, posmatrano od gornjeg-levog ugla.
		 * Skroz leva kolona ima vrednost gridx=0, dok prvi red ima vrednost
		 * gridy=0. (Napomena: gridx odredjuje kolone, a gridy redove).
		 * 
		 * Vrednosti ne smeju biti negativne.
		 */
		gbc.gridx = 0;
		gbc.gridy = 0;

		/*
		 * Specificira broj kolona (gridwidth) ili redova (gridheight) koje ce
		 * komponenta zauzeti. Default vrednosti su 1.
		 */
		gbc.gridwidth = 1;
		gbc.gridheight = 1;

		/*
		 * Interno popunjavanje. Odreduje koliko ce se piksela dodati na
		 * velicinu komponente. Default vrednost je 0. Pikseli se dodaju sa obe
		 * strane (horizontalno ili vertikalno), sto znaci da ce se
		 * sirina/visina komponente povecati za vrednost (ipad x 2) piksela.
		 */
		gbc.ipadx = 0;
		gbc.ipady = 0;

		/*
		 * Eksterno popunjavanje. Odredjuje minimalni prostor izmedju komponente
		 * i njene ivice prikaza. Po defaultu, to je 0 za sve pravce. Insets(int
		 * top, int left, int bottom, int right)
		 */
		gbc.insets = new Insets(0, 0, 0, 0);

		/*
		 * Koristi se kada je komponenta manja od podrucja prikaza. Ovim se
		 * odredjuje gde ce se komponenta pozicionirati. Konstante:
		 * GridBagConstraint.FIRST_LINE_START GridBagConstraint.PAGE_START
		 * GridBagConstraint.FIRST_LINE_END GridBagConstraint.LINE_START
		 * GridBagConstraint.CENTER GridBagConstraint.LINE_END
		 * GridBagConstraint.LAST_LINE_START GridBagConstraint.PAGE_END
		 * GridBagConstraint.LAST_LINE_END
		 * ------------------------------------------------- |FIRST_LINE_START
		 * PAGE_START FIRST_LINE_END| | | | | |LINE_START CENTER LINE_END| | | |
		 * | |LAST_LINE_START PAGE_END LAST_LINE_END|
		 * ------------------------------------------------- Default vrednost je
		 * CENTER.
		 */
		gbc.anchor = GridBagConstraints.CENTER;

		/*
		 * Odredjuje kako se raspodeljuje prostor izmedju kolona (weightx) i
		 * redova (weighty). Utice na ponasanje komponenti prilikom resize-a
		 * prozora. Sve dok se ne navede bar jedna ne-nula vrednost, sve
		 * komponente se grupisu u centru kontejnera. Vrednosti se navode u
		 * intervalu 0.0 do 1.0.
		 * 
		 * Defuault vrednosti su 0.0.
		 */
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;

		Container pane = getContentPane();

		JButton button;
		pane.setLayout(gbl);

		button = new JButton("Button 1");
		gbc.gridx = 0;
		gbc.gridy = 0;
		/*
		 * Ukoliko bi se ovde stavilo 1.0, Button 1 bi dobijao duplo vise
		 * horizontalnog prostora od ostala dva dugmeta u istom redu. *Probati
		 * sa 0.5 i 1.0, i pri tome raditi horizontalni resize.
		 */
		gbc.weightx = 0.5;

		/*
		 * NAPOMENA: Dva nacina dodavanja komponente u GridBagLayout. 1.
		 * container.add(komponenta, constraint)
		 * 
		 * 2. gridBagLayout.setConstraint(komponenta, constarint)
		 * container.add(komponenta)
		 */
		// 1.
		pane.add(button, gbc);

		// 2.
		// gbl.setConstraints(button, gbc);
		// pane.add(button);
		GridBagConstraints gbc2 = new GridBagConstraints();
		button = new JButton("Button 2");
		gbc2.gridx = 1;
		gbc2.gridy = 0;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbc2.weightx = 0.5;
		pane.add(button, gbc2);

		GridBagConstraints gbc3 = new GridBagConstraints();
		button = new JButton("Button 3");
		gbc3.gridx = 2;
		gbc3.gridy = 0;
		gbc3.fill = GridBagConstraints.HORIZONTAL;
		gbc3.weightx = 0.5;
		pane.add(button, gbc3);

		GridBagConstraints gbc4 = new GridBagConstraints(); 
		gbc4.fill = GridBagConstraints.HORIZONTAL;
		button = new JButton("Button 4");
		gbc4.gridx = 0;
		gbc4.gridy = 1;
		gbc4.gridwidth = 3;
		gbc4.ipady = 40; // interni padding za 40 piksela
		gbc4.weightx = 0.0;
		pane.add(button, gbc4);

		GridBagConstraints gbc5 = new GridBagConstraints(); 
		button = new JButton("Button 5");
		gbc5.gridx = 1; // druga kolona
		gbc5.gridwidth = 2; // prostire se na dve kolone
		gbc5.gridy = 2; // treci red
		gbc5.fill = GridBagConstraints.HORIZONTAL;
		gbc5.ipady = 0; // reset na default
		gbc5.weighty = 1.0; // zahteva dodatni vertikalni extra-space
		gbc5.anchor = GridBagConstraints.PAGE_END; // postavlja se u donji-desni
													// ugao
		gbc5.insets = new Insets(10, 0, 0, 0); // eksterna popuna (10 piksela sa
												// gornje strane)
		pane.add(button, gbc5);
	}

	public static void main(String[] args) {
		GridBagLayoutTest f = new GridBagLayoutTest();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}
}
