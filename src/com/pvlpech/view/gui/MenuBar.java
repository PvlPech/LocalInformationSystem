package com.pvlpech.view.gui;

import com.pvlpech.view.gui.listeners.GetHelpListener;
import com.pvlpech.view.utils.Constants;

import javax.swing.*;
import java.beans.PropertyChangeSupport;

/**
 * Created by pvlpech on 13.11.2018.
 */
class MenuBar extends JMenuBar {

    private JMenuItem helpItem;
    private JMenu menu;

    MenuBar(PropertyChangeSupport support) {
        this.helpItem = new JMenuItem(Constants.HELP_BUTTON_TITLE);
        this.menu = new JMenu(Constants.MENU_TITLE);

        helpItem.addActionListener(new GetHelpListener(support));

        menu.add(helpItem);
        this.add(menu);
    }
}
