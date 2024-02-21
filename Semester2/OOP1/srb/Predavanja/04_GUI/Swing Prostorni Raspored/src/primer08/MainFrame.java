package primer08;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1709171983555198163L;

	public MainFrame() {
		setSize(300, 200);

		getContentPane().add(bBoja, BorderLayout.NORTH);
		getContentPane().add(bIzlaz, BorderLayout.SOUTH);

		// dodajemo reakcije na dogadjaje dugmadima
		bBoja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				int r = (int) Math.round(Math.random() * 255);
				int g = (int) Math.round(Math.random() * 255);
				int b = (int) Math.round(Math.random() * 255);
				bBoja.setBackground(new Color(r, g, b));
			} // bOK menja boju na svaki klik misa
		});

		bIzlaz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				System.exit(0);
			} // bCancel prekida rad programa
		});
	}

	private JButton bBoja = new JButton("Promeni boju");
	private JButton bIzlaz = new JButton("Izlaz");

	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
		mf.setVisible(true);
	}

}
