package primer05b;

import javax.swing.ImageIcon;

public class ComputerComponent {

	private String name;
	private ImageIcon icon;
	
	public ComputerComponent(String name) {
		this.name = name;
		try {
			this.icon = new ImageIcon(ComputerComponent.class.getResource("/images/" + name + ".png"));
		}
		catch (Exception e) {
			this.icon = null;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	
	
	@Override
	public String toString() {
		return name;
	}
}
