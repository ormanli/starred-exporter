package com.starredexporter.exporter;

import com.starredexporter.jsonorg.JSONArray;
import com.starredexporter.jsonorg.JSONObject;
import com.starredexporter.jsonorg.JSONTokener;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainWindow {
    private JFileChooser fileChooser = new JFileChooser();
    private JTextField filepathTextField;
    private JPanel panel1;
    private JButton chooseButton;
    private JRadioButton linksAsPlaintextRadioButton;
    private JRadioButton linksRadioButton;
    private JRadioButton linksAndContentRadioButton;
    private JProgressBar progressBar;
    private JButton importButton;
    private String path = null;

    public MainWindow() {
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        return f.isDirectory() || f.getName().endsWith(".json");
                    }

                    @Override
                    public String getDescription() {
                        return "JSON";
                    }
                });
                int returnVal = fileChooser.showOpenDialog(chooseButton);


                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    path = fileChooser.getSelectedFile().getPath();
                } else {
                    path = "";
                }
                fileChooser.setSelectedFile(null);
            }
        });
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!path.isEmpty() || (path != null)) {
                    JSONTokener aa = null;
                    try {
                        aa = new JSONTokener(new FileInputStream(path));
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }

                    JSONObject main = new JSONObject(aa);
                    JSONArray items = main.getJSONArray("items");


                    Index index = Index.getInstance();
                    index.setTokener(path);

                    progressBar.setMinimum(0);
                    progressBar.setMaximum(index.getLength());

                    for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
                        Exporter exporter = new Exporter(path, i + "", progressBar);
                        new Thread(exporter).start();
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Reader Starred Exporter");
        frame.setContentPane(new MainWindow().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
