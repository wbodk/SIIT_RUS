package primer13;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class TestFrame extends JFrame {

	private static final long serialVersionUID = -8924323759500675814L;
	private FilesTree tree = null;

	public TestFrame() {
		super("JTree file system test");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(new FlowLayout());

		FSObject root = new FSObject(new File("C://Temp"));

		tree = new FilesTree(root);

		JScrollPane scp = new JScrollPane();
		scp.setPreferredSize(new Dimension(300, 300));
		scp.setViewportView(tree);

		getContentPane().add(scp);
	}

	public static void main(String[] args) {
		new TestFrame().setVisible(true);
	}
}
