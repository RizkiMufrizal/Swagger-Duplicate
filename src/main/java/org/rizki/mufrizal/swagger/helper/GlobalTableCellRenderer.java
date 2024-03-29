package org.rizki.mufrizal.swagger.helper;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GlobalTableCellRenderer extends DefaultTableCellRenderer {

    private final Color color;
    private final int columChecking;
    private final String message;

    public GlobalTableCellRenderer(Color color, int columChecking, String message) {
        this.color = color;
        this.columChecking = columChecking;
        this.message = message;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        String valueCell = (String) table.getValueAt(row, this.columChecking);

        if (valueCell != null) {
            if (!valueCell.equals(this.message)) {
                component.setBackground(color);
            } else {
                component.setBackground(Color.white);
            }
        }

        return component;
    }
}
