package com.hjchanna.talend;

import com.hjchanna.talend.gui.MainPanel;
import com.hjchanna.talend.util.TalendValidationToolUtils;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class TalendValidationTool {

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame.setDefaultLookAndFeelDecorated(true);

        JPanel mainPanel = new MainPanel();

        JFrame jFrame = new JFrame("Talend Validation Tool");
        jFrame.setContentPane(mainPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setIconImage(ImageIO.read(TalendValidationToolUtils.getResourceAsStream("img/talend_di.png")));
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
