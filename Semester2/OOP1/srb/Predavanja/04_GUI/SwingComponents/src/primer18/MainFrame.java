package primer18;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -4721959017220541810L;
	MyDlg dlg = new MyDlg(this);

	public MainFrame() {
		setSize(300, 200);
		setTitle("My First GUI App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(new FlowLayout());

		JButton b = new JButton("Pritisni me");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlg.setVisible(true);
				System.out.println("Posle set visible");
			}
		});
		getContentPane().add(b);
	}

	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}
}