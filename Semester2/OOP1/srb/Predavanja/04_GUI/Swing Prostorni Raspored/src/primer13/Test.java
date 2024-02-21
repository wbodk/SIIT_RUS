package primer13;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Test extends JFrame {
	
	private static final long serialVersionUID = 6227605724513137387L;
	
	JProgressBar progressBar;
	JButton b = new JButton(" Start ");
	boolean running = false;
	boolean stop = false;

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
					Thread t = new MojThread(Test.this);
					t.start();
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