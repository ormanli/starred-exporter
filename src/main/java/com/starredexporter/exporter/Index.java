package com.starredexporter.exporter;

import com.starredexporter.jsonorg.JSONArray;
import com.starredexporter.jsonorg.JSONObject;
import com.starredexporter.jsonorg.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Index {

    private int length;
    private int index = 0;


    private static Index ourInstance = new Index();

    public static Index getInstance() {
        return ourInstance;
    }

    private Index() {

    }

    public void setTokener(String path) {
        JSONTokener tokener = null;
        try {
            tokener = new JSONTokener(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        JSONObject main = new JSONObject(tokener);
        JSONArray items = main.getJSONArray("items");
        this.length = items.length();
    }

    public synchronized int getIndex() {
        if (index < length) {
            int temp = index;
            index++;
            return temp;
        } else {
            HTMLStash stash = HTMLStash.getInstance();
            try {
                stash.printOutput();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return -1;
        }
    }
}
