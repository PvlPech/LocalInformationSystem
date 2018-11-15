package com.pvlpech.view.gui.tables;

import javax.swing.table.DefaultTableModel;

/**
 * Created by pvlpech on 14.11.2018.
 */
public class GroupTableModel extends DefaultTableModel {
    /*
     * JTable uses this method to determine the default renderer/ editor for
     * each cell. If we didn't implement this method, then the last column
     * would contain text ("true"/"false"), rather than a check box.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        return col == 0;
    }


}
