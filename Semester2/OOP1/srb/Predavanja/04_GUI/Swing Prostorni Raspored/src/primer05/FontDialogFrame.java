package primer05;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

class FontDialogFrame extends JFrame {
	public FontDialogFrame() {
		setTitle("FontDialog");
		setSize(400, 265);

		GridBagLayout layout = new GridBagLayout();
		getContentPane().setLayout(layout);

		JLabel faceLabel = new JLabel("Face: ");

		face = new JComboBox(new String[] { "Serif", "SansSerif", "Monospaced",
				"Dialog", "DialogInput" });

		JLabel sizeLabel = new JLabel("Size: ");

		size = new JComboBox(new String[] { "8", "10", "12", "15", "18", "24",
				"36", "48" });

		bold = new JCheckBox("Bold");

		italic = new JCheckBox("Italic");
		sample = new JTextArea();
		sample.setText("The quick brown fox jumps over the lazy dog");
		sample.setEditable(false);
		sample.setLineWrap(true);

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.EAST;
		c.weightx = 0;
		c.weighty = 0;
		getContentPane().add(faceLabel, c);

		c_2 = new GridBagConstraints();
		c_2.weightx = 100.0;
		c_2.gridx = 1;
		c_2.gridy = 0;
		c_2.fill = GridBagConstraints.HORIZONTAL;
		c_2.insets = new Insets(2, 2, 2, 2);
		c_2.weighty = 0;
		getContentPane().add(face, c_2);

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		c.weightx = 0;
		c.weighty = 0;
		getContentPane().add(sizeLabel, c);

		c_1 = new GridBagConstraints();
		c_1.weightx = 100.0;
		c_1.fill = GridBagConstraints.HORIZONTAL;
		c_1.gridx = 1;
		c_1.gridy = 1;
		c_1.insets = new Insets(2, 2, 2, 2);
		c_1.weighty = 0;
		getContentPane().add(size, c_1);

		c_3 = new GridBagConstraints();
		c_3.weightx = 100.0;
		c_3.gridwidth = 2;
		c_3.gridx = 0;
		c_3.gridy = 2;
		c_3.gridheight = 1;
		getContentPane().add(bold, c_3);

		c_5 = new GridBagConstraints();
		c_5.weighty = 100.0;
		c_5.gridx = 0;
		c_5.gridy = 3;
		c_5.gridwidth = 2;
		c_5.gridheight = 1;
		c_5.weightx = 100;
		getContentPane().add(italic, c_5);

		c_4 = new GridBagConstraints();
		c_4.weightx = 100.0;
		c_4.gridx = 2;
		c_4.gridy = 0;
		c_4.gridwidth = 1;
		c_4.gridheight = 4;
		c_4.fill = GridBagConstraints.BOTH;
		c_4.weighty = 100;
		getContentPane().add(sample, c_4);
	}

	public static void main(String[] args) {
		FontDialogFrame frame = new FontDialogFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;

	private JComboBox face;
	private JComboBox size;
	private JCheckBox bold;
	private JCheckBox italic;
	private JTextArea sample;
	private GridBagConstraints c_1;
	private GridBagConstraints c_2;
	private GridBagConstraints c_3;
	private GridBagConstraints c_4;
	private GridBagConstraints c_5;

}
