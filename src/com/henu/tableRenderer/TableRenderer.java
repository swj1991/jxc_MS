package com.henu.tableRenderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

public class TableRenderer extends DefaultTableCellRenderer {
	public Component getTableCellRendererComponent(final JTable table,
			final Object value, final boolean isSelected,
			final boolean hasFocus, final int row, final int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, 
				row, column);
		
		if(row%2==0) {
			setBackground(Color.LIGHT_GRAY);
		} else {
			setBackground(Color.WHITE);
		}
		
//		if(column == 3) {
//			String strValue = (String) value;
//			JTextField jtf = new JTextField();
//			jtf.setText(strValue);
//			jtf.setHorizontalAlignment(JTextField.RIGHT);
//			jtf.setBorder(null);
//			if(Float.parseFloat(strValue) >= 85) {
//				jtf.setBackground(Color.red);
//			}
//			return jtf;
//		}
		return this;
		
	}

}