package primer02;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DijalogFrame extends JFrame {
	public DijalogFrame() {
		setSize(400, 300);
		setTitle("Primer");
		
		FlowLayout f = new FlowLayout(FlowLayout.RIGHT);
		JPanel p = new JPanel();
		p.setLayout(f);
		JButton btnOK = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");
		p.add(btnOK);
		p.add(btnCancel);
		
		getContentPane().add(p, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Open");
		panel.add(btnNewButton);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new DijalogFrame();

	}

}
