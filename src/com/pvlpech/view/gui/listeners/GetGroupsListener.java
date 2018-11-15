package com.pvlpech.view.gui.listeners;

import com.pvlpech.view.utils.Constants;
import com.pvlpech.view.utils.Utils;
import org.json.JSONObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by pvlpech on 13.11.2018.
 */
public class GetGroupsListener implements ActionListener {

    private PropertyChangeSupport support;

    public GetGroupsListener(PropertyChangeSupport support) {
        this.support = support;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JSONObject jsonObject = Utils.getGetGroupsJSON();
        support.firePropertyChange(Constants.VIEW_TOPIC, null, jsonObject);
    }

}
