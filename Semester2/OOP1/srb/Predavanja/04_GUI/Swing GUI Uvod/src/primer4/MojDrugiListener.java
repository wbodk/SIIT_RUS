package primer4;

import java.awt.event.WindowEvent;

public class MojDrugiListener extends MojListener {
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}
