package com.hjchanna.talend.gui_2;

import javax.swing.*;

public class MainPanel {
    private JPanel mainPanel;
    private JPanel pnlContent;
    private JPanel pnlFile;

    private void initComponents(){
//        treeProject.add
        JFileChooser fileChooser = new JFileChooser();
        pnlFile.setLayout(new BoxLayout(pnlFile, BoxLayout.X_AXIS));
        pnlFile.add(fileChooser);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
