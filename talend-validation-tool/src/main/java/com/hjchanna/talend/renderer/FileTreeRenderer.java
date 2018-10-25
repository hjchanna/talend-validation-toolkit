package com.hjchanna.talend.renderer;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author hjchanna
 */
public class FileTreeRenderer extends DefaultTreeCellRenderer{
    JFileChooser chooser = new JFileChooser();

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus); 
        if(value instanceof File){
            setIcon(chooser.getIcon((File)value));
            setText(((File)value).getName());
        }
        
        return this;
    }
}
