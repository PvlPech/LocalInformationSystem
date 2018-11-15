package com.pvlpech.view.gui.listeners;

import com.pvlpech.view.utils.Constants;
import com.pvlpech.view.utils.Utils;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by pvlpech on 13.11.2018.
 */
public class AddGroupListener implements ActionListener {

    private PropertyChangeSupport support;
    private JTextField numberInput;
    private JTextField facultyInput;

    public AddGroupListener(PropertyChangeSupport support, JTextField numberInput, JTextField facultyInput) {
        this.support = support;
        this.numberInput = numberInput;
        this.facultyInput = facultyInput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JSONObject jsonObject = Utils.getAddGroupJSON(Integer.parseInt(numberInput.getText()), Integer.parseInt(facultyInput.getText()));
        support.firePropertyChange(Constants.VIEW_TOPIC, null, jsonObject);
    }

}
