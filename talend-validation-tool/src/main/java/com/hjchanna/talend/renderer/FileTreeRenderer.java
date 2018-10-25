/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hjchanna.talend.renderer;

import java.awt.Component;
import java.io.File;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JTree;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;

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
//            setIcon(FileSystemView.getFileSystemView().getSystemIcon((File)value));
            setIcon(chooser.getIcon((File)value));

        }
        
        return this;
    }
}
