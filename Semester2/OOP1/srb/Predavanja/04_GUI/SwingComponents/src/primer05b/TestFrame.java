package primer05b;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class TestFrame extends JFrame {

	private JList<ComputerComponent> list = null;

	public TestFrame() {
		super("List test!");
		setSize(250, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(new FlowLayout());

		list = new JList<ComputerComponent>(new MyListBoxModel());
		list.setCellRenderer(new MyListRenderer());
		
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) { //This line prevents double events
					System.out.println("Odabrana stavka: " + list.getSelectedValue());
				}
			}
		});

//		JScrollPane scp = new JScrollPane();
//		scp.setViewportView(list);
		JScrollPane scp = new JScrollPane(list);

		getContentPane().add(scp);
	}

	private class MyListRenderer extends JLabel implements ListCellRenderer {

		public MyListRenderer() {
			setOpaque(true);
			setHorizontalAlignment(CENTER);
			setVerticalAlignment(CENTER);
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

			ComputerComponent cc = (ComputerComponent) value;
			if ( cc == null )
				cc = (ComputerComponent) list.getModel().getElementAt(0);
			setIcon(cc.getIcon());
			setText(cc.getName());

			if ( isSelected ) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			}
			else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}

			return this;
		}
	}

	private class MyListBoxModel extends AbstractListModel {
		private ArrayList<ComputerComponent> components;

		MyListBoxModel() {
			components = new ArrayList<ComputerComponent>();
			loadComponents();
		}

		private void loadComponents() {
			components = new ArrayList<ComputerComponent>();
			components.add(new ComputerComponent("camera"));
			components.add(new ComputerComponent("cdrom"));
			components.add(new ComputerComponent("dvd"));
			components.add(new ComputerComponent("floppy"));
			components.add(new ComputerComponent("hdd"));
			components.add(new ComputerComponent("joystick"));
			components.add(new ComputerComponent("memory"));
			components.add(new ComputerComponent("mouse"));
			components.add(new ComputerComponent("usb"));
			components.add(new ComputerComponent("scanner"));
		}

		@Override
		public Object getElementAt(int index) {
			return components.get(index);
		}

		@Override
		public int getSize() {
			return components.size();
		}
	}

	public static void main(String[] args) {
		new TestFrame().setVisible(true);
	}
}
