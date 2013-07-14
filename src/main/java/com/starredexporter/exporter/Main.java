package com.starredexporter.exporter;

import com.starredexporter.jsonorg.JSONArray;
import com.starredexporter.jsonorg.JSONObject;
import com.starredexporter.jsonorg.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        JSONTokener aa = new JSONTokener(new FileInputStream("/home/serdar/İndirilenler/starred.json"));

        JSONObject main = new JSONObject(aa);
        JSONArray items = main.getJSONArray("items");


        Index index = Index.getInstance();
        index.setTokener("/home/serdar/İndirilenler/starred.json");

        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            Exporter exporter = new Exporter("/home/serdar/İndirilenler/starred.json", i + "",1);
            new Thread(exporter).start();
        }

    }
}
