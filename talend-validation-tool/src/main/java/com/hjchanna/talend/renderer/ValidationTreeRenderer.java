package com.hjchanna.talend.renderer;

import com.hjchanna.talend.Constraints;
import com.hjchanna.talend.dto.ValidationResponse;
import com.hjchanna.talend.util.TalendValidationToolUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author ChannaJ
 */
public class ValidationTreeRenderer extends DefaultTreeCellRenderer {
    
    private static ImageIcon SUCCESS_ICON;
    private static ImageIcon WARNING_ICON;
    private static ImageIcon ERROR_ICON;
    
    private static ImageIcon TALEND_IMAGE_ICON;
    
    static {
        try {
            Image talendImage = ImageIO.read(TalendValidationToolUtils.getResourceAsStream("img/talend_di.png"));
            talendImage = talendImage.getScaledInstance(18, 16, Image.SCALE_SMOOTH);
            TALEND_IMAGE_ICON = new ImageIcon(talendImage);
            
            Image successImage = ImageIO.read(TalendValidationToolUtils.getResourceAsStream("img/success.png"));
            successImage = successImage.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            SUCCESS_ICON = new ImageIcon(successImage);
            
            Image warningImage = ImageIO.read(TalendValidationToolUtils.getResourceAsStream("img/warning.png"));
            warningImage = warningImage.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            WARNING_ICON = new ImageIcon(warningImage);
            
            Image errorImage = ImageIO.read(TalendValidationToolUtils.getResourceAsStream("img/error.png"));
            errorImage = errorImage.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            ERROR_ICON = new ImageIcon(errorImage);
        } catch (IOException ex) {
            TALEND_IMAGE_ICON = null;
            SUCCESS_ICON = null;
            WARNING_ICON = null;
            ERROR_ICON = null;
        }
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        
        if (value instanceof ValidationResponse) {
            renderer.setText(
                    "<html><b>" + ((ValidationResponse) value).getValidationRequest().getName() + "</b><i> (" + ((ValidationResponse) value).getValidationRequest().getDescription() + ") </i></html>"
            );
            renderer.setToolTipText(((ValidationResponse) value).getValidationRequest().getDescription());
            renderer.setIcon(TALEND_IMAGE_ICON);
            renderer.setForeground(Color.BLACK);
        }
        
        if (value instanceof ValidationResponse.ValidationFeedback) {
            String message = ((ValidationResponse.ValidationFeedback) value).getMessage();
            
            switch (((ValidationResponse.ValidationFeedback) value).getLevel()) {
                case Constraints.RESPONSE_SUCCESS:
                    renderer.setIcon(SUCCESS_ICON);
                    renderer.setForeground(Color.GREEN.darker());
                    renderer.setText("[SUCCESS] " + message);
                    break;
                case Constraints.RESPONSE_WARN:
                    renderer.setIcon(WARNING_ICON);
                    renderer.setForeground(Color.ORANGE);
                    renderer.setText("[WARNING] " + message);
                    break;
                case Constraints.RESPONSE_ERROR:
                    renderer.setIcon(ERROR_ICON);
                    renderer.setForeground(Color.RED);
                    renderer.setText("[DANGER ] " + message);
                    break;
                default:
                    throw new AssertionError();
            }
        }
        
        return renderer;
    }
    
}
