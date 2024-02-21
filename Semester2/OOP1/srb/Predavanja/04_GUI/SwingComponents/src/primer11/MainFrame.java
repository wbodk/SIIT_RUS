package primer11;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = -7010571962734080965L;
	JPanel contentPane;
	BorderLayout borderLayout1 = new BorderLayout();
	JScrollPane jScrollPane1 = new JScrollPane();

	JTable jTable1 = new JTable(new MyTableModel());

	// Construct the frame
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			jbInit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Component initialization
	private void jbInit() throws Exception {
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(borderLayout1);
		this.setSize(new Dimension(400, 300));
		this.setTitle("Frame Title");
		contentPane.add(jScrollPane1, BorderLayout.CENTER);
		jScrollPane1.getViewport().add(jTable1, null);

		jTable1.setDefaultEditor(Integer.class, new IntegerEditor(1973, 2024));
		jTable1.setDefaultRenderer(Integer.class, new IntegerRenderer(1973, 2024));

	}
	
	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}

}
