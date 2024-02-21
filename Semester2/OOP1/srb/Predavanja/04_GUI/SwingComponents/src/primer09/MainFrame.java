package primer09;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 2578458068428378148L;
	JPanel contentPane;
	BorderLayout borderLayout1 = new BorderLayout();
	JScrollPane jScrollPane1 = new JScrollPane();

//	JTable jTable1 = new JTable(new MyTableModel());
	JTable jTable1 = new JTable(new MyTableModelObj());

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
		this.setSize(new Dimension(400, 300));
		this.setTitle("Frame Title");
		contentPane.add(jScrollPane1, BorderLayout.CENTER);
		jScrollPane1.getViewport().add(jTable1, null);
		// uključuje automatsko sortiranje kolona
		jTable1.setAutoCreateRowSorter(true);
		JButton btnObrisi = new JButton("Obrisi selektovanog studenta");
		getContentPane().add(btnObrisi, BorderLayout.SOUTH);
		btnObrisi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jTable1.getSelectedRow() != -1) {
					MyTableModelObj mdl = (MyTableModelObj) jTable1.getModel();
					mdl.obrisiStudenta(jTable1.getSelectedRow());
				}
			}
		});
	}

	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}

}
