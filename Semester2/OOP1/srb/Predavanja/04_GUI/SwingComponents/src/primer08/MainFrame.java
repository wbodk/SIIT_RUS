package primer08;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = -5909599979311853866L;
	JPanel contentPane;
	BorderLayout borderLayout1 = new BorderLayout();
	JScrollPane jScrollPane1;// = new JScrollPane();
	private String[] columnNames = { "Broj indeksa", "Ime", "Prezime", "Godina upisa", "Bud\u017eet/Samofinansiranje" };
	private Object[][] data = {
			{ "E10000", "Milan", "Vidakovi\u0107", 		2000, true },
			{ "E10001", "Branko", "Milosavljevi\u0107", 2002, true },
			{ "E10002", "Petar", "Petrovi\u0107", 		1995, false },
			{ "E10003", "Marko", "Markovi\u0107", 		1990, true }
			};

	JTable jTable1 = new JTable(data, columnNames);

	// Construct the frame
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Component initialization
	private void jbInit() throws Exception {
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(borderLayout1);
		this.setSize(new Dimension(800, 400));
		this.setTitle("Frame Title");
		jScrollPane1 = new JScrollPane(jTable1);
		contentPane.add(jScrollPane1, BorderLayout.CENTER);
//		jScrollPane1.getViewport().add(jTable1, null);
	}
	
	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}

}
