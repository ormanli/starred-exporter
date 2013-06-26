package com.starredexporter.exporter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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

    public MainWindow() {
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showOpenDialog(chooseButton);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    System.out.println("Opening: " + file.getAbsolutePath());
                }
            }
        });
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
