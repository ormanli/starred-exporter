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

        Exporter exporter1 = new Exporter("/home/serdar/İndirilenler/starred.json", "1");
        Exporter exporter2 = new Exporter("/home/serdar/İndirilenler/starred.json", "2");
        Exporter exporter3 = new Exporter("/home/serdar/İndirilenler/starred.json", "3");
        Exporter exporter4 = new Exporter("/home/serdar/İndirilenler/starred.json", "4");

        new Thread(exporter1).start();
        new Thread(exporter2).start();
        new Thread(exporter3).start();
        new Thread(exporter4).start();
    }
}
