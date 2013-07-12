package com.starredexporter.exporter;

import com.starredexporter.jsonorg.JSONArray;
import com.starredexporter.jsonorg.JSONObject;
import com.starredexporter.jsonorg.JSONTokener;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Exporter implements Runnable {
    private String name;
    private String path;
    private JProgressBar progressBar;
    private int mode;

    public Exporter(String path, String name) {
        this.path = path;
        this.name = name;
    }

    public Exporter(String path, String name, JProgressBar progressBar) {
        this.path = path;
        this.name = name;
        this.progressBar = progressBar;
    }

    public Exporter(String path, String name, JProgressBar progressBar, int mode) {
        this.path = path;
        this.name = name;
        this.progressBar = progressBar;
        this.mode = mode;
    }

    @Override
    public void run() {
        Index index = Index.getInstance();

        while (index.nextIndex() > -1) {
            int indexValue = index.getIndex();

            if (indexValue > 0) {
                StringBuffer result = new StringBuffer();

                System.err.println(name + " aldım");

                JSONTokener tokener = null;
                try {
                    tokener = new JSONTokener(new FileInputStream(path));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                //TODO mode eklenecek
                JSONObject main = new JSONObject(tokener);
                JSONArray items = main.getJSONArray("items");

                JSONObject object = items.getJSONObject(indexValue);

                JSONArray objectArray = object.optJSONArray("alternate");
                JSONObject link = objectArray.optJSONObject(0);

                result.append("<li>");
                result.append("--------");
                result.append("<h2>");
                result.append(" <a href=\"");
                result.append(link.optString("href"));
                result.append("\">");
                result.append(object.optString("title"));
                result.append(("</a> "));
                result.append("</h2>");
                if (!object.isNull("content")) {
                    JSONObject content = object.optJSONObject("content");
                    if (!content.isNull("content")) {
                        String contentString = content.optString("content");
                        result.append(contentString);
                    }
                }
                result.append("</li>");

                HTMLStash stash = HTMLStash.getInstance();
                stash.put(result.toString());

                if (progressBar != null) {
                    progressBar.setValue(indexValue);
                }
            }
        }
    }
}
