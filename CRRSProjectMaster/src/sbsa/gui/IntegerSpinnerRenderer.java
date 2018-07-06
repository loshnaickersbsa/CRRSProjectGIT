package sbsa.gui;

import java.awt.Component;

import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.TableCellRenderer;

public class IntegerSpinnerRenderer extends JSpinner implements TableCellRenderer {

	public IntegerSpinnerRenderer() {
		super();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		JSpinner spnInteger = new JSpinner();
		SpinnerNumberModel spnNumbModel = new SpinnerNumberModel();
		spnNumbModel.setMinimum(0);
		if (value != null) {
			spnNumbModel.setValue((Integer) value);
		}
		spnInteger.setModel(spnNumbModel);
		return spnInteger;
	}

}
