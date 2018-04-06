/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hjchanna.talend.validation.validator;

import com.hjchanna.talend.validation.TalendValidator;
import com.hjchanna.talend.validation.dto.ValidationRequest;
import com.hjchanna.talend.validation.dto.ValidationResponse;
import com.sun.xml.internal.ws.api.policy.ValidationProcessor;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author ChannaJ
 */
public class ContentXmlValidator implements TalendValidator {

    private final ValidationRequest validationRequest;

    public ContentXmlValidator(ValidationRequest validationRequest) {
        this.validationRequest = validationRequest;
    }

    @Override
    public ValidationResponse validate(File root) {
        ValidationResponse validationResponse = new ValidationResponse(validationRequest);

        for (String fileLocation : validationRequest.getFileLocations()) {
            File validationDir = new File(root, fileLocation);
            File[] sourceFiles = validationDir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.matches(validationRequest.getFilePattern());
                }
            });

            for (File sourceFile : sourceFiles) {
                try {
                    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();

                    Document xmlDocument = documentBuilder.parse(sourceFile);

                    XPath xPath = XPathFactory.newInstance().newXPath();
                    XPathExpression xPathExpression = xPath.compile(validationRequest.getSourceXpath());

                    NodeList nodeList = (NodeList) xPathExpression.evaluate(xmlDocument, XPathConstants.NODESET);
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        String sourceValue = nodeList.item(i).getNodeValue();

                        boolean mathches = sourceValue.matches(validationRequest.getAssertionRegex());
                        if (!mathches) {
                            String fileNameWithoutExtention = sourceFile.getName();
                            fileNameWithoutExtention = fileNameWithoutExtention.substring(0, fileNameWithoutExtention.lastIndexOf("."));

                            validationResponse.getMessages().add(validationRequest.getName() + " is failed for object " + sourceValue + " in " + fileNameWithoutExtention);
                        }
                    }
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(ContentXmlValidator.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SAXException ex) {
                    Logger.getLogger(ContentXmlValidator.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ContentXmlValidator.class.getName()).log(Level.SEVERE, null, ex);
                } catch (XPathExpressionException ex) {
                    Logger.getLogger(ContentXmlValidator.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

        return validationResponse;
    }

}
