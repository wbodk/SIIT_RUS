package primer17;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class UIExample {

	public static void main(String[] args) {

		// tekst na default dugmicima
		UIManager.put("OptionPane.yesButtonText", "Да");
		UIManager.put("OptionPane.noButtonText", "Не");
		UIManager.put("OptionPane.cancelButtonText", "Откажи");
		UIManager.put("OptionPane.okButtonText", "У реду");

		@SuppressWarnings("unused")
		int answer1 = JOptionPane.showConfirmDialog(null, "Да ли сте сигурни?",
				"Питање", JOptionPane.YES_NO_CANCEL_OPTION);

		@SuppressWarnings("unused")
		int answer2 = JOptionPane.showConfirmDialog(null, "Да ли сте сигурни?",
				"Питање", JOptionPane.OK_CANCEL_OPTION);
	}

}
