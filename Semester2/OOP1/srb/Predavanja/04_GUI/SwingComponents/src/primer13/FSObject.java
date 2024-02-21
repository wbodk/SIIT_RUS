package primer13;

import java.io.File;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * Klasa predstavlja apstrakciju file-ova, direktorijuma i virtuelnog direktorijuma.
 * Koncepcija za kreiranje klase preuzeta je iz jednog predloga za implementaciju
 * virtuelnog direktorijuma.
 * FSObject predstavlja bilo koji objekat: file, direktorijum, virtuelni direktorijum.
 * @author Uros Krkic
 */
public class FSObject extends DefaultMutableTreeNode {
	
	private static final long serialVersionUID = 3571886045801279828L;


	/**
	 * Fizicka reprezentacija objekta na serverovom file sistemu.
	 */
	private File file;

	
	/**
	 * Ime objekta.
	 */
	private String name;
	
	
	/**
	 * Indikator da li je objekat direktorijum.
	 */
	private boolean directory;
	
	
	/**
	 * Constructor
	 * @param physicalFile - fizicka reprezentacija objekta
	 * @author Uros Krkic
	 */
	public FSObject(File physicalFile) {
		this.file = physicalFile;
		this.name = physicalFile.getName();
		this.directory = physicalFile.isDirectory();
		if ( directory )
			this.setAllowsChildren(true);
		else
			this.setAllowsChildren(false);
		// obezbedjuje da cvor sadrzi sve potrebne podatke
		this.setUserObject(this);
	}

	
	
	
	/**
	 * Metoda vraca putanju objekta.
	 * @return - path
	 * @author Uros Krkic
	 */
	public String getRealPath() {
		return file.getAbsolutePath();
	}

	
	/**
	 * Metoda vraca boolean vrednost koja indikuje da li je objekat direktorijum.
	 * @return - true, ako jeste, inace false
	 * @author Uros Krkic
	 */
	public boolean isDirectory() {
		return directory;
	}

	
	/**
	 * Metoda omogucava da se kao argument println() metode navede instanca FTPObject.
	 * @author Uros Krkic
	 */
	public String toString() {
		return name;
	}

	

	/**
	 * Metoda vraca ime FS objekta.
	 * @return ime
	 * @author Uros Krkic
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * Metoda vraca fizicku reprezentaciju objekta u vidu file-a.
	 * @return - file
	 * @author Uros Krkic
	 */
	public File getFile() {
		return file;
	}
	
	
	/**
	 * Metoda vraca indikator da li postoji dete sa imenom prosledjenim kao argument metode.
	 * @param name - ime deteta cije se postojanje proverava
	 * @return - true, ako postoji, inace false
	 * @author Uros Krkic
	 */
	public boolean childExists(String name) {
		FSObject currentChild;
		
		if (children == null)
			return false;

		for (int i = 0; i < children.size(); i++) {
			currentChild = (FSObject)children.elementAt(i);
			if ( currentChild.getName().equalsIgnoreCase(name) ) {
				return true;
			}
		}
		
		return false;
	}
	

	
	public FSObject getChildByName(String name) {
		FSObject currentChild;
		
		if (children == null)
			return null;

		for (int i = 0; i < children.size(); i++) {
			currentChild = (FSObject)children.elementAt(i);
			if ( currentChild.getName().equalsIgnoreCase(name) ) {
				return (FSObject) children.elementAt(i);
			}
		}
		
		return null;
	}
	
	

	/**
	 * Koristi se interno u Vector klasi.
	 * @author Uros Krkic
	 */
	public boolean equals(Object o) {
		if (o instanceof FSObject) {
			return this.toString().equalsIgnoreCase(o.toString());
		}
		return false;
	}
	

	
	/**
	 * Metoda vraca virtuelnu putanju iz stabla FS objekata.
	 * @return virtuelna putanja
	 * @author Uros Krkic
	 */
	public String getTreePath() {
		
		TreeNode[] nodes = this.getPath();
		
		StringBuffer path = new StringBuffer();
		for (TreeNode fso : nodes) {
			path.append( ((FSObject)fso).getName() );
			path.append("/");
		}
		
		String retPath = path.toString();
		retPath = retPath.substring(0, retPath.length()-1);

		return retPath;
	}

}
