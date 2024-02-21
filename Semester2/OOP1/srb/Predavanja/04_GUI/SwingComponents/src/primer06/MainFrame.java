package primer06;

import java.awt.Component;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -3393550031192005914L;

	public MainFrame() {
		setSize(600, 400);
		setTitle("JTabbedPane App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane();
		ImageIcon icon = createImageIcon("/images/middle.gif");

		JComponent panel1 = makeTextPanel("Panel 1", new JButton("Dugme u prvom panelu"));
		tabbedPane.addTab("Tab 1", icon, panel1, "Panel br. 1");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel2 = makeTextPanel("Panel 2", new JCheckBox("Checkbox u drugom panelu"));
		tabbedPane.addTab("Tab 2", icon, panel2, "Panel br. 2");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		JComponent panel3 = makeTextPanel("Panel 3", new JRadioButton("Radio button u trećem panelu"));
		tabbedPane.addTab("Tab 3", icon, panel3, "Panel br. 3");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		getContentPane().add(tabbedPane);

	}

	private JComponent makeTextPanel(String text, Component c) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		panel.add(filler);
		panel.add(c);
		return panel;
	}

	private static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = MainFrame.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}

}