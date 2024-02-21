package primer13;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;


/**
 * Klasa koja predstavlja predefinisani TreeNodeCellRenderer. Omogucava da se svakom cvoru
 * u stablu setuje odgovarajuca ikonica i nacin prikaza, u zavisnosti od statusa korisnika
 * i vrste cvora.
 * 
 * @author Uros Krkic
 */
@SuppressWarnings("serial")
public class FilesTreeCellRenderer extends DefaultTreeCellRenderer {

	// font koji se koristi za ispis cvorova stabla
	private String fontName = "Tahoma";

	// velicina fonta koji se koristi za ispis cvorova stabla
	private int fontSize = 12;
	
	// boja stabla, odnosno boja pozadine cvora
	private Color treeColor = null;
	

	private Icon systemFolderIcon = null;

  /** Gateway za file sistem.
   *  OVA KLASA SE KORISTI ZA VIZUALIZACIJU SISTEMSKI-POVEZANIH IKONICA  
   */	
	private FileSystemView fsw = FileSystemView.getFileSystemView();
	
	/**
	 * Konstruktor.
	 * @param treeColor - boja stabla, koja se setuje da bude i pozadinska boja cvora
	 * @author Uros Krkic
	 */
	public FilesTreeCellRenderer(Color treeColor) {
		this.treeColor = treeColor;
		                            
    /* Icon for a file, directory, or folder as it would be displayed in a system file browser. */
		systemFolderIcon = fsw.getSystemIcon(new File(System.getProperty("user.home")));
	}

	
	private Font fontBold = new Font(fontName, Font.BOLD, fontSize);
	
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {

		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		// postavljanje fonta
		this.setFont(fontBold);
		
		// postavljanje pozadinske boje cvorova stabla
		this.setBackgroundNonSelectionColor(treeColor);
		this.setBackgroundSelectionColor(Color.LIGHT_GRAY);
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

		FSObject fsObject = (FSObject) node.getUserObject();
		
		/*
		 * Setovanje ikonica za FSO cvorove, u zavisnosti od njihovog statusa.
		 */
		if ( leaf ) {
			if ( fsObject.isDirectory() ) {
				setIcon(systemFolderIcon);
				setFont(fontBold);
			}
			else {
				try {
					// za svaki slucaj
					if ( fsObject.getFile().exists() ) {
					  /* Icon for a file, directory, or folder as it would be displayed in a system file browser. */
						Icon icon = fsw.getSystemIcon(fsObject.getFile());
						setIcon(icon);
					}
				}
				catch (Exception e) {}
				setFont(fontBold);
			}

		}
		else {
			// onda je direktorijum, pa nema potrebe proveravati
				setIcon(systemFolderIcon);
				setFont(fontBold);
		}
		
		return this;
	}

	
}
