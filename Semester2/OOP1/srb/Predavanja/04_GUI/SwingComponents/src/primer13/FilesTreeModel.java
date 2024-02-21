package primer13;

import java.io.File;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;


/**
 * Klasa koja predstavlja Tree model koji se koristi u stablu. Klasa je izvedena
 * iz DefaultTreeModel klase.
 * Klasa sadrzi odgovarajuce predefinisane metode DefaultTreeModela, koje omogucavaju
 * upravljanje modelom podataka.
 * 
 * @author Uros Krkic
 */
@SuppressWarnings("serial")
public class FilesTreeModel extends DefaultTreeModel {

	private FSObject rootNode = null;
	
	public FilesTreeModel(FSObject rootFSO) {
		super(rootFSO);
		
		rootNode = (FSObject) root;
		
		addNodes(rootFSO);
	}
	
	
	public void addNodes(FSObject folder) {
		
		if ( folder == null || !folder.getFile().exists() || !folder.getFile().isDirectory() )
			return;
		
		File[] files = folder.getFile().listFiles();
		
		if (files == null) {
			return;
		}
		
		for (int i = 0; i < files.length; i++) {
			if ( files[i].isDirectory() ) {
				FSObject fso = new FSObject(files[i]);
				folder.add(fso);
				addNodes(fso);
			}
			else {
				folder.add(new FSObject(files[i]));
			}
		}
	}
	
	@Override
	public Object getChild(Object parent, int index) {
		if (parent instanceof FSObject){
			FSObject fso = (FSObject) parent;
			if ( !fso.isDirectory()) {
				return null;
			}
			else {
				return fso.getChildAt(index);
			}
		}

		return getRoot();
	}

	
	@Override
	public int getChildCount(Object parent) {
		if (parent instanceof FSObject) {
			FSObject fso = (FSObject) parent;
			if ( !fso.isDirectory()) {
				return 0;
			}
			else {
				return fso.getChildCount();
			}
		}

		return ((DefaultMutableTreeNode)parent).getChildCount();
	}

	
	@Override
	public int getIndexOfChild(Object parent, Object child) {
        if(parent == null)
            return -1;
		int ind = ((TreeNode)parent).getIndex((TreeNode)child); 

		return ind;
	}

	
	@Override
	public boolean isLeaf(Object node) {
		if (node instanceof FSObject) {
			FSObject fso = (FSObject) node;
			return !fso.isDirectory();
		}

		return false;
	}

	
	public void clearModel() {
		rootNode.removeAllChildren();
	}
	
}
