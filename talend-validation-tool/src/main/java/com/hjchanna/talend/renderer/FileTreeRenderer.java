package com.hjchanna.talend.renderer;

import com.hjchanna.talend.util.TalendValidationToolUtils;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author hjchanna
 */
public class FileTreeRenderer extends DefaultTreeCellRenderer {

    private static JFileChooser FILE_CHOOSER;
    private static ImageIcon TALEND_IMAGE_ICON;

    static {
        try {
            FILE_CHOOSER = new JFileChooser();

            Image talendImage = ImageIO.read(TalendValidationToolUtils.getResourceAsStream("img/talend_di.png"));
            talendImage = talendImage.getScaledInstance(18, 16, Image.SCALE_SMOOTH);
            TALEND_IMAGE_ICON = new ImageIcon(talendImage);
        } catch (IOException ex) {
            TALEND_IMAGE_ICON = null;
        }
    }

    public FileTreeRenderer() {
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        if (value instanceof File) {
            boolean isTalendProject = TalendValidationToolUtils.isTalendProject((File) value);
            if (isTalendProject) {
                setIcon(TALEND_IMAGE_ICON);
            } else {
                setIcon(FILE_CHOOSER.getIcon((File) value));
            }

            setText(FILE_CHOOSER.getName((File) value));
        }

        return this;
    }
}
