package com.pvlpech.view;

import java.beans.PropertyChangeListener;

/**
 * Created by pvlpech on 11.11.2018.
 */
public interface View extends PropertyChangeListener {

    void init();

    void showError(String message);

    void showHelp();

    void addPropertyChangeListener(PropertyChangeListener pcl);

    void removePropertyChangeListener(PropertyChangeListener pcl);

}
