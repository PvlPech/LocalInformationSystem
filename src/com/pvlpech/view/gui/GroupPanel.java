package com.pvlpech.view.gui;

import com.pvlpech.view.gui.listeners.AddGroupListener;
import com.pvlpech.view.gui.listeners.DelGroupListener;
import com.pvlpech.view.gui.tables.GroupTableModel;
import com.pvlpech.view.utils.Constants;
import com.pvlpech.view.utils.Utils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.util.Iterator;

/**
 * Created by pvlpech on 13.11.2018.
 */
class GroupPanel extends JPanel {

    private GroupTableModel groupTableModel;

    GroupPanel(PropertyChangeSupport support) {
        this.setLayout(new BorderLayout());

        this.groupTableModel = new GroupTableModel();

        this.add(getAddPanel(support), BorderLayout.NORTH);
        this.add(getGroupsTable(), BorderLayout.CENTER);
        this.add(getDelPanel(support), BorderLayout.SOUTH);
    }

    private JPanel getAddPanel(PropertyChangeSupport support) {
        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JTextField numberInput = new JTextField("", 5);
        JTextField facultyInput = new JTextField("", 5);
        JButton addButton = new JButton(Constants.ADD_BUTTON_TITLE);

        addPanel.setBorder(BorderFactory.createTitledBorder(Constants.ADD_PANEL_TITLE));
        addButton.addActionListener(new AddGroupListener(support, numberInput, facultyInput));

        addPanel.add(numberInput);
        addPanel.add(facultyInput);
        addPanel.add(addButton);

        return addPanel;
    }

    private JPanel getDelPanel(PropertyChangeSupport support) {
        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JButton delButton = new JButton(Constants.DEL_BUTTON_TITLE);

        delButton.addActionListener(new DelGroupListener(support, groupTableModel));
        addPanel.add(delButton);

        return addPanel;
    }

    void refreshData(JSONArray jsonArray) {
        Iterator it = jsonArray.iterator();
        groupTableModel.setRowCount(0);
        while (it.hasNext()) {
            JSONObject item = (JSONObject) it.next();
            groupTableModel.addRow(new Object[]{false, item.getLong("id"), item.getInt("number"), item.getInt("faculty")});
        }
    }

    void initData(PropertyChangeSupport support) {
        JSONObject jsonObject = Utils.getGetGroupsJSON();
        support.firePropertyChange(Constants.VIEW_TOPIC, null, jsonObject);
    }

    private JScrollPane getGroupsTable() {
        JTable table = new JTable(groupTableModel);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        groupTableModel.addColumn(" ");
        groupTableModel.addColumn("id");
        groupTableModel.addColumn("number");
        groupTableModel.addColumn("faculty");

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(Constants.GROUP_FLAG_COLUMN).setPreferredWidth(20);
        columnModel.getColumn(Constants.GROUP_FLAG_COLUMN).setResizable(false);
        table.removeColumn(table.getColumnModel().getColumn(Constants.GROUP_ID_COLUMN)); // hide ids

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        return scrollPane;
    }

}
