package primer07;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

public class MenuFrame extends JFrame implements ActionListener, ItemListener {

	private static final long serialVersionUID = 6693212345715766350L;

	public MenuFrame() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private void jbInit() throws Exception {
		// Display the window.
		setSize(450, 260);
		setTitle("Meniji");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;
		JRadioButtonMenuItem rbMenuItem;
		JCheckBoxMenuItem cbMenuItem;

		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build the first menu.
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);

		// a group of JMenuItems
		menuItem = new JMenuItem("Open", KeyEvent.VK_O);
		// menuItem.setMnemonic(KeyEvent.VK_O); //moze i ovako
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		// obratiti paznju na to da ova klasa implementira ActionListener i
		// ItemListener interfejse
		menuItem.addActionListener(this);
		menu.add(menuItem);

		ImageIcon icon = createImageIcon("/images/middle.gif");
		menuItem = new JMenuItem("Opcija sa ikonom", icon);
		menuItem.setMnemonic(KeyEvent.VK_I);
		menuItem.addActionListener(this);
		menu.add(menuItem);

		// a group of radio button menu items
		menu.addSeparator();
		ButtonGroup group = new ButtonGroup();

		rbMenuItem = new JRadioButtonMenuItem("Radio button menu item br. 1");
		rbMenuItem.setSelected(true);
		rbMenuItem.setMnemonic(KeyEvent.VK_R);
		group.add(rbMenuItem);
		rbMenuItem.addActionListener(this);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Radio button menu item br. 2");
		rbMenuItem.setMnemonic(KeyEvent.VK_2);
		group.add(rbMenuItem);
		rbMenuItem.addActionListener(this);
		menu.add(rbMenuItem);

		// a group of check box menu items
		menu.addSeparator();
		cbMenuItem = new JCheckBoxMenuItem("Check box menu item br. 1");
		cbMenuItem.setMnemonic(KeyEvent.VK_C);
		cbMenuItem.addItemListener(this);
		menu.add(cbMenuItem);

		cbMenuItem = new JCheckBoxMenuItem("Check box menu item br. 2");
		cbMenuItem.setMnemonic(KeyEvent.VK_H);
		cbMenuItem.addItemListener(this);
		menu.add(cbMenuItem);

		// a group of JMenuItems
		menuItem = new JMenuItem("Exit", KeyEvent.VK_X);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
				ActionEvent.ALT_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);

		// Edit meni
		menu = new JMenu("Edit");
		menu.setMnemonic(KeyEvent.VK_E);
		menuBar.add(menu);

		// a group of JMenuItems
		menuItem = new JMenuItem("Copy", KeyEvent.VK_C);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.CTRL_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);

		setJMenuBar(menuBar);

		// popup menu
		JPopupMenu popup = new JPopupMenu();
		menuItem = new JMenuItem("A popup menu item");
		menuItem.addActionListener(this);
		popup.add(menuItem);
		menuItem = new JMenuItem("Another popup menu item");
		menuItem.addActionListener(this);
		popup.add(menuItem);

		// Add listener to the text area so the popup menu can come up.
		MouseListener popupListener = new PopupListener(popup);
		this.addMouseListener(popupListener);

	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = MenuFrame.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem source = (JMenuItem) (e.getSource());
		String s = "Opcija menija:" + source.getText();
		System.out.println(s);
		if (source.getText().equals("Exit"))
			System.exit(0);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		JMenuItem source = (JMenuItem) (e.getSource());
		String s = "Item event se dogodio:"
				+ source.getText()
				+ ". Stanje: "
				+ ((e.getStateChange() == ItemEvent.SELECTED) ? "selected"
						: "unselected");
		System.out.println(s);
	}

	class PopupListener extends MouseAdapter {
		JPopupMenu popup;

		PopupListener(JPopupMenu popupMenu) {
			popup = popupMenu;
		}

		public void mousePressed(MouseEvent e) {
			maybeShowPopup(e);
		}

		public void mouseReleased(MouseEvent e) {
			maybeShowPopup(e);
		}

		private void maybeShowPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

	public static void main(String[] args) {
		new MenuFrame().setVisible(true);
	}

}
