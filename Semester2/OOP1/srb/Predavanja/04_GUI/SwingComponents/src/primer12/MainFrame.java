package primer12;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = -7133895007369108547L;
	private JEditorPane htmlPane;
	private JTree tree;
	public DefaultMutableTreeNode top;

	public MainFrame() {
		super("JTree Demo");
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		// ovim se postavlja ikona u aplikaciji
		setIconImage(createImageIcon("/images/ikona.gif").getImage());

		// PANEL sa Tree view
		top = new DefaultMutableTreeNode(new Podatak("Koren",
				"<h1>Programski jezik Java</h1>"));
		// Kreiramo JTree se single selection
		tree = new JTree(top);
		kreirajStablo(top);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		// Nakacimo listener na selekciju
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				if (node == null)
					return;
				Object nodeObject = node.getUserObject();
				Podatak p = (Podatak) nodeObject;
				htmlPane.setText(p.text);
			}
		});

		// Kreiramo Scroll Panel sa ovim stablom
		JScrollPane treeView = new JScrollPane(tree);
		// Kreiramo HTML viewer
		htmlPane = new JEditorPane();
		htmlPane.setContentType("text/html");
		htmlPane.setEditable(false);
		JScrollPane htmlView = new JScrollPane(htmlPane);

		// Kreiramo spliter
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setTopComponent(treeView);
		splitPane.setBottomComponent(htmlView);
		Dimension minimumSize = new Dimension(100, 50);
		htmlView.setMinimumSize(minimumSize);
		treeView.setMinimumSize(minimumSize);
		splitPane.setDividerLocation(300); // XXX: ignored in some releases
		// of Swing. bug 4101306
		splitPane.setPreferredSize(new Dimension(500, 300));
		// Dodamo spliter
		getContentPane().add(splitPane, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);

		// centriramo
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((d.width - getSize().width) / 2,
				(d.height - getSize().height) / 2);
		selectNode(top);
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = MainFrame.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	private void kreirajStablo(DefaultMutableTreeNode top) {
		DefaultMutableTreeNode cvor = null;

		cvor = new DefaultMutableTreeNode(new Podatak("Prvi deo",
				"<h2>Uvod u programski jezik Java</h2><p>Uvodne napomene.</p>"));
		top.add(cvor);

		cvor = new DefaultMutableTreeNode(
				new Podatak(
						"Drugi deo",
						"<h2>Ulaz/izlaz</h2><p>Ovo poglavlje opisuje osnove <b>ulazno-izlazne</b> operacije.</p>"));
		top.add(cvor);

		cvor = new DefaultMutableTreeNode(
				new Podatak(
						"Treći deo",
						"<h2>GUI</h2><p>Ovo poglavlje opisuje osnove <font color=\"red\">grafi\u010dkog korisni\u010dkog interfejsa</font>.</p>"));
		top.add(cvor);

	}

	public void selectNode(DefaultMutableTreeNode item) {
		TreeNode[] path = item.getPath();
		TreePath tp = new TreePath(path);
		tree.addSelectionPath(tp);
	}

	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}
}
