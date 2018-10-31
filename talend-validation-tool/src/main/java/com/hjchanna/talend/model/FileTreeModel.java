package com.hjchanna.talend.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
        if (parent instanceof String) {
            return FileSystemView.getFileSystemView().getRoots()[index];
        } else if (parent instanceof File) {
            return listFiles((File) parent).get(index);
        }

        return null;
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof String) {
            return FileSystemView.getFileSystemView().getRoots().length;
        } else if (parent instanceof File) {
            return listFiles((File) parent).size();
        }

        return 0;
    }

    @Override
    public boolean isLeaf(Object node) {
        if (node instanceof String) {
            return false;
        } else if (node instanceof File) {
            return !((File) node).isDirectory();
        }

        return true;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        //do nothing
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        //do nothing
        return 0;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        //do nothing
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        //do nothing
    }

    private List<File> listFiles(File parent) {
        File[] childs = parent.listFiles();
        List<File> directories = new ArrayList<>();
        for (File file : childs) {
            if (file.isDirectory()) {
                directories.add(file);
            }
        }
        return directories;
    }
}
