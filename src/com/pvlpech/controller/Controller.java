package com.pvlpech.controller;

import com.pvlpech.model.loader.Loader;
import com.pvlpech.view.View;
import org.json.JSONObject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by pvlpech on 11.11.2018.
 */
public class Controller implements PropertyChangeListener {

    private View view;
    private Loader loader;

    public Controller(View view, Loader loader) {
        this.view = view;
        this.loader = loader;

        this.view.addPropertyChangeListener(this);
        this.loader.addPropertyChangeListener(this.view);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        JSONObject jsonObject = (JSONObject) evt.getNewValue();
        String operation = jsonObject.getString("operation");
        if ("exit".equalsIgnoreCase(operation)) {
            return;
        } else if ("help".equalsIgnoreCase(operation)) {
            view.showHelp();
        } else {
            loader.performAction(jsonObject);
        }
    }
}
