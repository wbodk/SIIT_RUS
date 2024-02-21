package primer19;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 5703499590369653599L;

	public MainFrame() {
		setSize(250, 300);
		setTitle("My First GUI App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(new FlowLayout());

		JButton b1 = new JButton("Metal Look And Feel");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager
							.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					SwingUtilities.updateComponentTreeUI(MainFrame.this);
				} catch (Exception ex) {
				}
			}
		});
		getContentPane().add(b1);

		JButton b2 = new JButton("Windows Look And Feel");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// ovaj Look And Feel moze samo na Windows platformi
					UIManager
							.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					SwingUtilities.updateComponentTreeUI(MainFrame.this);
				} catch (Exception ex) {
				}
			}
		});
		getContentPane().add(b2);

		JButton b3 = new JButton("Motif Look And Feel");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager
							.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
					SwingUtilities.updateComponentTreeUI(MainFrame.this);
				} catch (Exception ex) {
				}
			}
		});
		getContentPane().add(b3);

		JButton b4 = new JButton("CrossPlatform Look And Feel");
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(UIManager
							.getCrossPlatformLookAndFeelClassName());
					SwingUtilities.updateComponentTreeUI(MainFrame.this);
				} catch (Exception ex) {
				}
			}
		});
		getContentPane().add(b4);

		JButton b5 = new JButton("System Look And Feel");
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
					SwingUtilities.updateComponentTreeUI(MainFrame.this);
				} catch (Exception ex) {
				}
			}
		});
		getContentPane().add(b5);

	}
	
	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}
}