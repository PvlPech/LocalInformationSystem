package com.pvlpech.view.utils;


import org.json.JSONObject;

/**
 * Created by pvlpech on 14.11.2018.
 */
public class Utils {

    public static JSONObject getAddGroupJSON(int number, int faculty) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("operation", "add");
        jsonObject.put("entity", "group");
        jsonObject.put("number", number);
        jsonObject.put("faculty", faculty);
        return jsonObject;
    }

    public static JSONObject getDelGroupJSON(long id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("operation", "del");
        jsonObject.put("entity", "group");
        jsonObject.put("id", id);
        return jsonObject;
    }

    public static JSONObject getGetGroupsJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("operation", "get");
        jsonObject.put("entity", "group");
        return jsonObject;
    }

    public static JSONObject getHelpJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("operation", "help");
        return jsonObject;
    }

    public static JSONObject getExitJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("operation", "exit");
        return jsonObject;
    }

}
