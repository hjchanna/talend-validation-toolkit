package com.hjchanna.talend.gui;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hjchanna.talend.Constraints;
import com.hjchanna.talend.TalendValidationUtil;
import com.hjchanna.talend.dto.ValidationResponse;
import com.hjchanna.talend.model.ValidationTreeModel;
import com.hjchanna.talend.renderer.ValidationTreeRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 *
 * @author ChannaJ
 */
public class TalendProjectView extends javax.swing.JPanel {
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnScan;
    private javax.swing.JLabel lblCreationDate;
    private javax.swing.JLabel lblHost;
    private javax.swing.JLabel lblProductVersion;
    private javax.swing.JLabel lblProjectLocation;
    private javax.swing.JLabel lblProjectName;
    private javax.swing.JLabel lblScanSummary;
    private javax.swing.JTree treeValidations;
    // End of variables declaration//GEN-END:variables
    private static final TalendProjectView INSTANCE = new TalendProjectView();
    private File projectDir;

    public TalendProjectView() {
        initComponents();

        btnScan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scan();
            }
        });
        treeValidations.setCellRenderer(new ValidationTreeRenderer());
    }

    public static TalendProjectView getInstance(File dir) {
        INSTANCE.setProjectDir(dir);

        return INSTANCE;
    }

    private void setProjectDir(File dir) {
        this.projectDir = dir;
        try {
            updateProjectInfo();
        } catch (Exception ex) {
        }
        scan();
    }

    private void updateProjectInfo() throws Exception {
        File projectFile = new File(projectDir, "talend.project");
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(projectFile);

        document.getDocumentElement().normalize();

        Node node = document.getElementsByTagName("TalendProperties:Project").item(0);
        NamedNodeMap attrs = node.getAttributes();

        String label = attrs.getNamedItem("label").getNodeValue();
        String productVersion = attrs.getNamedItem("productVersion").getNodeValue();
        String type = attrs.getNamedItem("type").getNodeValue();
        String creationDate = attrs.getNamedItem("creationDate").getNodeValue();
        String url = attrs.getNamedItem("url").getNodeValue();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(url);
        String location = jsonNode.get("location").asText();
        String storage = jsonNode.get("storage").asText();

        lblProjectName.setText(label + " (" + type + ")");
        lblProductVersion.setText(productVersion);
        lblProjectLocation.setText(this.projectDir.getCanonicalPath());
        lblCreationDate.setText(creationDate);
        lblHost.setText("(" + storage.toUpperCase() + ") " + location);
    }

    private void scan() {
        List<ValidationResponse> validations
                = TalendValidationUtil.getInstance().validateTalendProject(projectDir);

        ValidationTreeModel validationTreeModel = new ValidationTreeModel();
        validationTreeModel.setValidationResponses(validations);
        treeValidations.setModel(validationTreeModel);

        int checks = 0;
        int success = 0;
        int warning = 0;
        int error = 0;
        for (ValidationResponse validation : validations) {
            checks++;
            for (ValidationResponse.ValidationFeedback validationFeedback : validation.getValidationFeedbacks()) {
                switch (validationFeedback.getLevel()) {
                    case Constraints.RESPONSE_SUCCESS:
                        success++;
                        break;
                    case Constraints.RESPONSE_WARN:
                        warning++;
                        break;
                    case Constraints.RESPONSE_ERROR:
                        error++;
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        }

        lblScanSummary.setText("<html>"
                + "Validated against " + checks + " rule(s), "
                + "<font color='green'>" + success + " success(es), </font>"
                + "<font color='orange'>" + warning + " warning(s), </font>"
                + "<font color='red'>" + error + " danger(s) </font>"
                + "</html>");

        expandAllNodes(treeValidations, 0, treeValidations.getRowCount());
    }

    private void expandAllNodes(javax.swing.JTree tree, int startingIndex, int rowCount) {
        for (int i = startingIndex; i < rowCount; ++i) {
            tree.expandRow(i);
        }

        if (tree.getRowCount() != rowCount) {
            expandAllNodes(tree, rowCount, tree.getRowCount());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        lblProjectName = new javax.swing.JLabel();
        lblProductVersion = new javax.swing.JLabel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        btnScan = new javax.swing.JButton();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        treeValidations = new javax.swing.JTree();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        lblCreationDate = new javax.swing.JLabel();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        lblHost = new javax.swing.JLabel();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        lblProjectLocation = new javax.swing.JLabel();
        lblScanSummary = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Project Name: ");

        lblProjectName.setText(" ");

        lblProductVersion.setText(" ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Product Version: ");

        btnScan.setText("Refresh");

        treeValidations.setRootVisible(false);
        treeValidations.setRowHeight(18);
        jScrollPane1.setViewportView(treeValidations);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Creation Date: ");

        lblCreationDate.setText(" ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Source: ");

        lblHost.setText(" ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Location: ");

        lblProjectLocation.setText(" ");

        lblScanSummary.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblScanSummary.setText("Scan Summary:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnScan)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblScanSummary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblProjectName, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                                    .addComponent(lblProductVersion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblCreationDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblHost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblProjectLocation, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lblProjectName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(lblProjectLocation))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(lblProductVersion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(lblCreationDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(lblHost))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblScanSummary)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnScan)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
}
