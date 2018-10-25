package com.hjchanna.talend.gui;

import com.hjchanna.talend.model.FileTreeModel;
import com.hjchanna.talend.renderer.FileTreeRenderer;

/**
 *
 * @author hjchanna
 */
public class ProjectsPanel extends javax.swing.JPanel {

    public ProjectsPanel() {
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

        scTreeFiles = new javax.swing.JScrollPane();
        treeFiles = new javax.swing.JTree();
        pnlProjectOverview = new javax.swing.JPanel();
        btnOpen = new javax.swing.JButton();
        pnlProjectInfo = new javax.swing.JPanel();
        lblType = new javax.swing.JLabel();
        lblProjectType = new javax.swing.JLabel();
        lblProject = new javax.swing.JLabel();
        lblProjectName = new javax.swing.JLabel();

        treeFiles.setRootVisible(false);
        treeFiles.setRowHeight(24);
        scTreeFiles.setViewportView(treeFiles);

        btnOpen.setText("Open");

        lblType.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblType.setText("Type : ");

        lblProjectType.setText(" ");

        lblProject.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblProject.setText("Project :");

        lblProjectName.setText(" ");

        javax.swing.GroupLayout pnlProjectInfoLayout = new javax.swing.GroupLayout(pnlProjectInfo);
        pnlProjectInfo.setLayout(pnlProjectInfoLayout);
        pnlProjectInfoLayout.setHorizontalGroup(
            pnlProjectInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProjectInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProjectInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProject)
                    .addComponent(lblType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlProjectInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProjectName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblProjectType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlProjectInfoLayout.setVerticalGroup(
            pnlProjectInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProjectInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProjectInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProject)
                    .addComponent(lblProjectName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlProjectInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblType)
                    .addComponent(lblProjectType))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlProjectOverviewLayout = new javax.swing.GroupLayout(pnlProjectOverview);
        pnlProjectOverview.setLayout(pnlProjectOverviewLayout);
        pnlProjectOverviewLayout.setHorizontalGroup(
            pnlProjectOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProjectOverviewLayout.createSequentialGroup()
                .addContainerGap(289, Short.MAX_VALUE)
                .addComponent(btnOpen)
                .addGap(6, 6, 6))
            .addGroup(pnlProjectOverviewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlProjectInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlProjectOverviewLayout.setVerticalGroup(
            pnlProjectOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProjectOverviewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlProjectInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOpen)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scTreeFiles)
            .addComponent(pnlProjectOverview, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scTreeFiles, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlProjectOverview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOpen;
    private javax.swing.JLabel lblProject;
    private javax.swing.JLabel lblProjectName;
    private javax.swing.JLabel lblProjectType;
    private javax.swing.JLabel lblType;
    private javax.swing.JPanel pnlProjectInfo;
    private javax.swing.JPanel pnlProjectOverview;
    private javax.swing.JScrollPane scTreeFiles;
    private javax.swing.JTree treeFiles;
    // End of variables declaration//GEN-END:variables
}
