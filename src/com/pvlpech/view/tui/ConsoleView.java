package com.pvlpech.view.tui;

import com.pvlpech.view.View;
import com.pvlpech.view.utils.Constants;
import com.pvlpech.view.utils.Utils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pvlpech on 11.11.2018.
 */
public class ConsoleView implements View {

    private PropertyChangeSupport support;

    private final String[] POSSIBLE_ACTION_PATTERN = {
            "\\A(" + Constants.EXIT_PREFIX + ")\\z",
            "\\A(" + Constants.HELP_PREFIX + ")\\z",
            "\\A(" + Constants.ADD_PREFIX + " " + Constants.GROUP_PREFIX + " [0-9]+ [0-9]+)\\z",
            "\\A(" + Constants.GET_PREFIX + " " + Constants.GROUP_PREFIX + ")\\z",
            "\\A(" + Constants.DEL_PREFIX + " " + Constants.GROUP_PREFIX + " -?[0-9]+)\\z"
    };

    public ConsoleView() {
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "info":
                System.out.println(evt.getNewValue().toString());
                break;
            case "get":
                JSONObject jsonObject = (JSONObject) (evt.getNewValue());
                JSONArray jsonArray = jsonObject.getJSONArray("entities");
                Iterator it = jsonArray.iterator();
                while (it.hasNext()) {
                    JSONObject item = (JSONObject) it.next();
                    System.out.println(item.toString());
                }
        }
        printInput();
    }

    private void printInput() {
        Scanner scan = new Scanner(System.in);
        setAction(scan.nextLine());
    }

    @Override
    public void init() {
        System.out.println("Please type 'Help' for more information");
        printInput();
    }

    @Override
    public void showError(String message) {
        System.out.println(message);
        printInput();
    }

    @Override
    public void showHelp() {
        System.out.println(Constants.HELP_MESSAGE);
        printInput();
    }

    private void setAction(String action) {
        if (isCommandValid(action)) {
            String[] args = action.split(" ");
            JSONObject jsonObject = null;
            switch (args[0]) {
                case Constants.EXIT_PREFIX:
                    jsonObject = Utils.getExitJSON();
                    break;
                case Constants.HELP_PREFIX:
                    jsonObject = Utils.getHelpJSON();
                    break;
                case Constants.ADD_PREFIX:
                    jsonObject = getAddJSON(args);
                    break;
                case Constants.GET_PREFIX:
                    jsonObject = getGetJSON(args);
                    break;
                case Constants.DEL_PREFIX:
                    jsonObject = getDelJSON(args);
                    break;
            }
            if (jsonObject != null) {
                support.firePropertyChange(Constants.VIEW_TOPIC, null, jsonObject);
            }
        } else {
            showError("Incorrect command");
        }
    }

    private JSONObject getAddJSON(String[] args) {
        switch (args[1]) {
            case Constants.GROUP_PREFIX:
                return Utils.getAddGroupJSON(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        }
        return null;
    }

    private JSONObject getDelJSON(String[] args) {
        switch (args[1]) {
            case Constants.GROUP_PREFIX:
                return Utils.getDelGroupJSON(Long.parseLong(args[2]));
        }
        return null;
    }

    private JSONObject getGetJSON(String[] args) {
        switch (args[1]) {
            case Constants.GROUP_PREFIX:
                return Utils.getGetGroupsJSON();
        }
        return null;
    }

    private boolean isCommandValid(String command) {
        if (command == null || command.isEmpty()) {
            return false;
        }
        Pattern pattern;
        Matcher matcher;
        for (String mask : POSSIBLE_ACTION_PATTERN) {
            pattern = Pattern.compile(mask);
            matcher = pattern.matcher(command);

            if (matcher.find()){
                return true;
            }
        }
        return false;
    }

}
