package primer13;

import java.awt.Color;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;


public class FilesTree extends JTree implements TreeSelectionListener {

	private static final long serialVersionUID = 498328200258826695L;

	/**
	 * TreeModel za stablo.
	 */
	private FilesTreeModel filesTreeModel = null;
	
	/**
	 * TreeNodeCellRenderer za stablo.
	 */
	private FilesTreeCellRenderer renderer = null;
	
	/**
	 * boja stabla
	 */
	private Color treeColor = new Color(240, 248, 255);
	
	/**
	 * Predstavlja selektovanog korisnika u stablu sa korisnicima.
	 */
	@SuppressWarnings("unused")
	private FSObject selectedFSObject = null;
	
	public FilesTree(FSObject root) {
		filesTreeModel = new FilesTreeModel(root);
		renderer = new FilesTreeCellRenderer(treeColor);
		
		initializeTree();
		
		refreshTree();
	}
	
	/**
	 * Metoda vrsi inicijalizaciju stabla.
	 * @author Uros Krkic
	 */
	private void initializeTree() {

		this.setModel(filesTreeModel);
		this.setCellRenderer(renderer);
						
		this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		this.addTreeSelectionListener(this);
		
		this.setBackground(treeColor);
		
		this.setRootVisible(true);
		this.setShowsRootHandles(true);
		
		this.setOpaque(true);
	}
	
	/**
	 * Metoda vrsi osvezavanje stabla (viewer-a)
	 */
	public void refreshTree() {
		SwingUtilities.updateComponentTreeUI(this);
		this.clearSelection();
	}
	
	/**
	 * Metoda iz TreeSelectionListener-a. Obradjuje dogadjaje selekcije (klika) odgovarajuceg
	 * cvora stabla.
	 */       
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.getLastSelectedPathComponent();
		
		if ( !(node instanceof FSObject) ) {
			return;
		}
		
		FSObject fsObject = (FSObject)node.getUserObject();
		
		selectedFSObject = fsObject;
	}
	
	public FilesTreeModel getTreeModel() {
		return filesTreeModel;
	}
	
	
}

