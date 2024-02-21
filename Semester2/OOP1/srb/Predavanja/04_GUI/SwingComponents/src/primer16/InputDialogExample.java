package primer16;

import javax.swing.JOptionPane;

class InputDialogExample {
  public static void main(String[] args) {
    String name = JOptionPane.showInputDialog(null,
                     "What's yer name, pardner?");
    JOptionPane.showMessageDialog(null, "Yeehaw, " + name);
  }
}
