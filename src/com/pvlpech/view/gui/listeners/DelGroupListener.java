package com.pvlpech.view.gui.listeners;

import com.pvlpech.view.utils.Constants;
import com.pvlpech.view.utils.Utils;
import org.json.JSONObject;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by pvlpech on 13.11.2018.
 */
public class DelGroupListener implements ActionListener {

    private PropertyChangeSupport support;
    private DefaultTableModel tableModel;

    public DelGroupListener(PropertyChangeSupport support, DefaultTableModel tableModel) {
        this.support = support;
        this.tableModel = tableModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JSONObject jsonObject = null;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            boolean flag = (boolean) tableModel.getValueAt(i, Constants.GROUP_FLAG_COLUMN);
            long id = (long) tableModel.getValueAt(i, Constants.GROUP_ID_COLUMN);
            if (flag) {
                jsonObject = Utils.getDelGroupJSON(id);
            }
        }
        if (jsonObject != null) {
            support.firePropertyChange(Constants.VIEW_TOPIC, null, jsonObject);
        }
    }

}
