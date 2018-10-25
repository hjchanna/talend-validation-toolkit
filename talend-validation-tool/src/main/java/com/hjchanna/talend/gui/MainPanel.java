package com.hjchanna.talend.gui;

/**
 *
 * @author hjchanna
 */
public class MainPanel extends javax.swing.JPanel {

    public MainPanel() {
        initComponents();
        
        initOthers();
    }

    @SuppressWarnings("unchecked")
    private void initOthers() {
        this.projectsPanel = new ProjectsPanel();
        
        this.pnlProjects.add(this.projectsPanel);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        pnlProjects = new javax.swing.JPanel();

        jSplitPane1.setDividerLocation(350);

        pnlProjects.setLayout(new javax.swing.BoxLayout(pnlProjects, javax.swing.BoxLayout.X_AXIS));
        jSplitPane1.setLeftComponent(pnlProjects);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private ProjectsPanel projectsPanel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel pnlProjects;
    // End of variables declaration//GEN-END:variables
}
