package com.pvlpech.view.gui;

import com.pvlpech.view.View;
import com.pvlpech.view.gui.listeners.AddGroupListener;
import com.pvlpech.view.gui.listeners.DelGroupListener;
import com.pvlpech.view.gui.listeners.GetGroupsListener;
import com.pvlpech.view.utils.Constants;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by pvlpech on 12.11.2018.
 */
public class SwingView extends JFrame implements View {

    private PropertyChangeSupport support;
    private GroupPanel groupPanel;

    @Override
    public void init() {
        this.groupPanel.initData(support);
        this.setVisible(true);
    }

    @Override
    public void showError(String message) {
        showMessageDialog(message, Constants.ERROR_TITLE);
    }

    private void showMessageDialog(String message, String title) {
        JOptionPane.showMessageDialog(null,
                message,
                title,
                JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void showHelp() {
        showMessageDialog(Constants.HELP_MESSAGE, Constants.HELP_BUTTON_TITLE);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "info":
                showMessageDialog(evt.getNewValue().toString(), "Information");
                break;
            case "get":
                JSONObject jsonObject = (JSONObject) (evt.getNewValue());
                JSONArray jsonArray = jsonObject.getJSONArray("entities");
                if (Constants.GROUP_PREFIX.equals(jsonObject.getString("entity"))) {
                    groupPanel.refreshData(jsonArray);
                }
                break;
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public SwingView() throws HeadlessException {
        super(Constants.SWING_HEADER);

        this.support = new PropertyChangeSupport(this);
        MenuBar menuBar = new MenuBar(support);
        JTabbedPane tabbedPane = new JTabbedPane();
        groupPanel = new GroupPanel(support);

        this.setBounds(Constants.frameX, Constants.frameY, Constants.frameWidth, Constants.frameHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.setJMenuBar(menuBar);

        Container container = this.getContentPane();
        tabbedPane.add(Constants.GROUP_TAB_TITLE, groupPanel);
        container.add(tabbedPane);
    }
}
