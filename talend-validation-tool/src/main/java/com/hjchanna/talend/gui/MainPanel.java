/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hjchanna.talend.gui;

import com.hjchanna.talend.model.FileTreeModel;
import com.hjchanna.talend.renderer.FileTreeRenderer;
import javax.swing.JFileChooser;

/**
 *
 * @author hjchanna
 */
public class MainPanel extends javax.swing.JPanel {

    /**
     * Creates new form MainPanel
     */
    public MainPanel() {
        initComponents();
        
        initOthers();
    }

    @SuppressWarnings("unchecked")
    private void initOthers() {
        treeFiles.setModel(new FileTreeModel());
        treeFiles.setCellRenderer(new FileTreeRenderer());
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        pnlProjects = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        treeFiles = new javax.swing.JTree();

        jSplitPane1.setDividerLocation(251);

        pnlProjects.setLayout(new javax.swing.BoxLayout(pnlProjects, javax.swing.BoxLayout.X_AXIS));

        jScrollPane1.setViewportView(treeFiles);

        pnlProjects.add(jScrollPane1);

        jSplitPane1.setLeftComponent(pnlProjects);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel pnlProjects;
    private javax.swing.JTree treeFiles;
    // End of variables declaration//GEN-END:variables
}
