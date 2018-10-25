package com.hjchanna.talend;


import com.hjchanna.talend.gui.MainPanel;
import javax.swing.*;

public class TalendValidationTool {
    public static void main(String[] args) {
        JPanel mainPanel = new MainPanel();

        System.out.println(System.getProperty("user.home"));

        JFrame jFrame = new JFrame("Talend Validation Tool");
        jFrame.setContentPane(mainPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}

