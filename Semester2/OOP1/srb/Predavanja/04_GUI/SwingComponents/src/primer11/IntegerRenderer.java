package primer11;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

public class IntegerRenderer extends JLabel implements TableCellRenderer {
	private static final long serialVersionUID = -6472193701597351092L;
	int minimum;
	int maximum;

	Border unselectedBorder = null;
	Border selectedBorder = null;
	Border selectedFocusBorder = null;
	Border invalidUnselectedBorder = null;
	Border invalidSelectedBorder = null;
	Border invalidSelectedFocusBorder = null;

	int state = 0;
	static final int VALID = 0;
	static final int INVALID = 4;
	static final int SELECTED = 1;
	static final int FOCUS = 2;

	public IntegerRenderer(int minimum, int maximum) {
		this.minimum = minimum;
		this.maximum = maximum;
		setOpaque(true); // MUST do this for background to show up.
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		if (unselectedBorder == null)
			unselectedBorder = new EmptyBorder(1, 1, 1, 1);
		// unselectedBorder = BorderFactory.createMatteBorder(2,5,2,5,
		// table.getBackground());
		if (selectedBorder == null)
			selectedBorder = new EmptyBorder(1, 1, 1, 1);
		// selectedBorder = BorderFactory.createMatteBorder(2,5,2,5,
		// table.getSelectionBackground());
		if (selectedFocusBorder == null)
			selectedFocusBorder = BorderFactory.createLineBorder(table
					.getGridColor());

		if (invalidUnselectedBorder == null)
			invalidUnselectedBorder = BorderFactory
					.createLineBorder(Color.yellow);
		if (invalidSelectedBorder == null)
			invalidSelectedBorder = BorderFactory
					.createLineBorder(Color.yellow);
		if (invalidSelectedFocusBorder == null)
			invalidSelectedFocusBorder = BorderFactory
					.createLineBorder(Color.red);

		int broj = ((Integer) value).intValue();
		if (broj < minimum || broj > maximum) {
			setText("#invalid");
			state = INVALID;
		} else {
			setText("" + broj);
			state = VALID;
		}
		if (isSelected) {
			state += SELECTED;
		}
		if (hasFocus) {
			state += FOCUS;
		}

		switch (state) {
		case VALID:
			setBorder(unselectedBorder);
			setBackground(table.getBackground());
			break;
		case VALID + SELECTED:
			setBorder(selectedBorder);
			setBackground(table.getSelectionBackground());
			break;
		case VALID + SELECTED + FOCUS:
			setBorder(selectedFocusBorder);
			setBackground(table.getBackground());
			break;
		case INVALID:
			setBorder(invalidUnselectedBorder);
			setBackground(Color.yellow);
			break;
		case INVALID + SELECTED:
			setBorder(invalidSelectedBorder);
			setBackground(Color.yellow);
			break;
		case INVALID + SELECTED + FOCUS:
			setBorder(invalidSelectedFocusBorder);
			setBackground(Color.yellow);
			break;
		}

		return this;
	}
}
