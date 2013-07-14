package com.starredexporter.exporter;

import com.starredexporter.jsonorg.JSONArray;
import com.starredexporter.jsonorg.JSONObject;
import com.starredexporter.jsonorg.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;

/**
 * Gives index of JSON file in order. Singleton
 * @author ormanli
 */
public class Index {

    private int length;
    private int index = 0;
    private JButton button;
    private static Index ourInstance = new Index();

    /**
     * 
     * @return Instance of Index
     */
    public static Index getInstance() {
        return ourInstance;
    }

    private Index() {
    }

    /**
     * 
     * @param path Path of JSON file
     */
    public void setTokener(String path) {
        JSONTokener tokener = null;
        try {
            tokener = new JSONTokener(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JSONObject main = new JSONObject(tokener);
        JSONArray items = main.getJSONArray("items");
        this.length = items.length();
    }

    /**
     * Value of next index. Does not increments index.
     * @return Value of Index
     */
    public int nextIndex() {
        if (index < length) {
            return index;
        } else {
            HTMLStash stash = HTMLStash.getInstance();
            try {
                stash.printOutput();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (button != null) {
                button.setEnabled(true);
            }
            
            return -1;
        }
    }

    /**
     * Gives next index and increments index.
     * @return Value of Index
     */
    public synchronized int getIndex() {
        if (index < length) {
            int temp = index;
            System.out.println((index + 1) + " of " + length + " items.");
            index++;
            return temp;
        } else {
            return -1;
        }
    }

    /**
     * 
     * @return Length of JSON
     */
    public int getLength() {
        return length;
    }

    /**
     * 
     * @param path Path of JSON file
     * @param button Button to interact with gui
     */
    public void setTokener(String path, JButton button) {
        setTokener(path);
        this.button = button;
    }
}
