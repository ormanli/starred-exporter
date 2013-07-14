package com.starredexporter.exporter;

import com.starredexporter.jsonorg.JSONArray;
import com.starredexporter.jsonorg.JSONObject;
import com.starredexporter.jsonorg.JSONTokener;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Exports items from JSON and puts to HTMLStash. Thread
 * @author ormanli
 */
public class Exporter implements Runnable {

    private String name;
    private String path;
    private JProgressBar progressBar;
    private int mode;

    /**
     * 
     * @param path
     * @param name
     */
    public Exporter(String path, String name) {
        this.path = path;
        this.name = name;
        this.mode = 3;
    }

    /**
     * 
     * @param path
     * @param name
     * @param progressBar
     */
    public Exporter(String path, String name, JProgressBar progressBar) {
        this.path = path;
        this.name = name;
        this.progressBar = progressBar;
        this.mode = 3;
    }

    /**
     * 
     * @param path Path of JSON file
     * @param name Name of thread
     * @param progressBar Progressbar to interact with gui
     * @param mode Mode of export
     */
    public Exporter(String path, String name, JProgressBar progressBar, int mode) {
        this.path = path;
        this.name = name;
        this.progressBar = progressBar;
        this.mode = mode;
    }

    /**
     * 
     * @param name Name of thread
     * @param path Path of JSON file
     * @param mode Mode of export
     */
    public Exporter(String name, String path, int mode) {
        this.name = name;
        this.path = path;
        this.mode = mode;
    }

    @Override
    public void run() {
        Index index = Index.getInstance();

        while (index.nextIndex() > -1) {
            
            int indexValue = index.getIndex();
            
            if (indexValue > -1) {
                StringBuffer result = new StringBuffer();

                JSONTokener tokener = null;
                try {
                    tokener = new JSONTokener(new FileInputStream(path));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                JSONObject main = new JSONObject(tokener);
                JSONArray items = main.getJSONArray("items");

                JSONObject object = items.getJSONObject(indexValue);

                JSONArray objectArray = object.optJSONArray("alternate");
                JSONObject link = objectArray.optJSONObject(0);

                result.append("--------");
                result.append("<li>");
                

                if (mode > 1) {
                    result.append("<h2>");
                    result.append(" <a href=\"");
                }

                result.append(link.optString("href"));

                if (mode > 1) {
                    result.append("\">");
                    result.append(object.optString("title"));
                    result.append(("</a> "));
                    result.append("</h2>");
                }

                if (mode > 2) {
                    if (!object.isNull("content")) {
                        JSONObject content = object.optJSONObject("content");
                        if (!content.isNull("content")) {
                            String contentString = content.optString("content");
                            result.append(contentString);
                        }
                    }
                }

                result.append("</li>");

                HTMLStash stash = HTMLStash.getInstance();
                stash.put(result.toString());

                if (progressBar != null) {
                    progressBar.setValue(indexValue+1);
                }
            }
        }
    }
}
