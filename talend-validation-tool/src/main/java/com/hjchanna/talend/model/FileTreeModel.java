package com.hjchanna.talend.model;

import java.io.File;
import javax.swing.event.TreeModelListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class FileTreeModel implements TreeModel {

    @Override
    public Object getRoot() {
        return "ROOT";
    }

    @Override
    public Object getChild(Object parent, int index) {
        if(parent instanceof String){
            return FileSystemView.getFileSystemView().getRoots()[index];
        }else if(parent instanceof File){
            return ((File)parent).listFiles()[index];
        }
        
        return null;
    }

    @Override
    public int getChildCount(Object parent) {
        if(parent instanceof String){
            return FileSystemView.getFileSystemView().getRoots().length;
        }else if(parent instanceof File){
            return ((File)parent).listFiles().length;
        }
        
        return 0;
    }

    @Override
    public boolean isLeaf(Object node) {
        if(node instanceof String){
            return false;
        }else if(node instanceof File){
            return !((File)node).isDirectory();
        }
        
        return true;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return 0;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {

    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {

    }
}
