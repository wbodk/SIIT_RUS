package primer12;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Test extends JFrame {
	JProgressBar progressBar;
	JButton b = new JButton(" Start ");
	private boolean running = false;
	private boolean stop = false;

	public Test() {
		setSize(300, 200);
		setTitle("Freeze demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(new FlowLayout());

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!running) {
					running = true;
					b.setText(" Stop ");
					for (int i = 1; i <= 10; i++) {
						if (stop) {
							stop = false;
							break;
						}
						try {
							Thread.currentThread().sleep(1000);
						} catch (Exception ex) {
						}
						progressBar.setValue(i * 10);
						System.out.println("Done " + i * 10 + "%");
					}
					running = false;
					b.setText(" Start ");
				} else {
					stop = true;
					b.setText(" Start ");
				}
			}
		});
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		getContentPane().add(progressBar);
		getContentPane().add(b);
	}

	public static void main(String[] args) {
		Test mf = new Test();
		mf.setVisible(true);
	}

}