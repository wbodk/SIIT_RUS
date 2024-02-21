package primer04b;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

@SuppressWarnings("serial")
public class TestFrame extends JFrame {

	private JComboBox<ComputerComponent> comboBox = null;

	public TestFrame() {
		super("ComboBox test !");
		setSize(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(new FlowLayout());

		comboBox = new JComboBox<ComputerComponent>(new MyComboBoxModel());

		ComboBoxRenderer  cbr = new ComboBoxRenderer();
		comboBox.setRenderer(cbr);
		
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Odabrana stavka: " + comboBox.getSelectedItem());
			}
		});

		getContentPane().add(comboBox);
		
		comboBox.setSelectedIndex(0);
	}

	private class ComboBoxRenderer extends JLabel implements ListCellRenderer {

		public ComboBoxRenderer() {
			setOpaque(true);
			setHorizontalAlignment(CENTER);
			setVerticalAlignment(CENTER);
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {

			ComputerComponent cc = (ComputerComponent) value;
			if (cc == null)
				cc = (ComputerComponent) list.getModel().getElementAt(0);
			setIcon(cc.getIcon());
			setText(cc.getName());

			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}

			return this;
		}
	}

	private class MyComboBoxModel extends AbstractListModel<ComputerComponent>  implements
			ComboBoxModel<ComputerComponent>  {
		private ArrayList<ComputerComponent> components;

		MyComboBoxModel() {
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

		ComputerComponent selection;

		@Override
		public ComputerComponent getElementAt(int index) {
			return components.get(index);
		}

		@Override
		public int getSize() {
			return components.size();
		}

		@Override
		public void setSelectedItem(Object anItem) {
			selection = (ComputerComponent) anItem;
		}

		@Override
		public Object getSelectedItem() {
			return selection;
		}
	}

	public static void main(String[] args) {
		new TestFrame().setVisible(true);
	}
}
