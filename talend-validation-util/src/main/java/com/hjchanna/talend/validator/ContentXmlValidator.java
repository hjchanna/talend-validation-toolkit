package com.hjchanna.talend.validator;

import com.hjchanna.talend.Constraints;
import com.hjchanna.talend.util.FileUtil;
import com.hjchanna.talend.dto.ValidationRequest;
import com.hjchanna.talend.dto.ValidationResponse;
import com.hjchanna.talend.util.ValidationUtil;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author ChannaJ
 */
public class ContentXmlValidator implements TalendValidator {
    private static final Logger LOGGER = Logger.getLogger(ContentXmlValidator.class);
    private final ValidationRequest validationRequest;

    public ContentXmlValidator(ValidationRequest validationRequest) {
        this.validationRequest = validationRequest;
    }

    @Override
    public ValidationResponse validate(File root) {
        LOGGER.info("start validate for validation rule '" + validationRequest.getName() + "'");
        ValidationResponse validationResponse = new ValidationResponse(validationRequest);

        for (String fileLocation : validationRequest.getFileLocations()) {
            LOGGER.debug("validating directory " + fileLocation);
            File validationDir = new File(root, fileLocation);
            List<File> sourceFiles = FileUtil.listFiles(validationDir, validationRequest.getFilePattern(), validationRequest.getIncludeSubDirectories());

            for (File sourceFile : sourceFiles) {
                LOGGER.debug("starting to validate file " + sourceFile.getName());
                try {
                    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();

                    Document xmlDocument = documentBuilder.parse(sourceFile);

                    XPath xPath = XPathFactory.newInstance().newXPath();
                    XPathExpression xPathExpression = xPath.compile(validationRequest.getSourceXpath());

                    NodeList nodeList = (NodeList) xPathExpression.evaluate(xmlDocument, XPathConstants.NODESET);
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        String sourceValue = nodeList.item(i).getNodeValue();

                        boolean matches = sourceValue.matches(validationRequest.getAssertionRegex());
                        LOGGER.debug("Validated xml node " + sourceValue + " of " + sourceFile.getName() + " against regex '" + validationRequest.getAssertionRegex() + "', matches: " + matches);

                        String fileNameWithoutExtension = FileUtil.getFileRelativePathWithoutExtension(validationDir, sourceFile);

                        if (!matches) {
                            //validation fails
                            validationResponse.addFeedback(
                                    ValidationUtil.getValidationResponseLevel(validationRequest.getLevel()),
                                    validationRequest.getName() + " is failed for object " + sourceValue + " in " + fileNameWithoutExtension
                            );
                        } else {
                            //validation success
                            validationResponse.addFeedback(
                                    Constraints.RESPONSE_SUCCESS,
                                    validationRequest.getName() + " is success for object " + sourceValue + " in " + fileNameWithoutExtension
                            );
                        }
                    }
                } catch (ParserConfigurationException ex) {
                    LOGGER.error(ex.getMessage(), ex);
                } catch (SAXException ex) {
                    LOGGER.error(ex.getMessage(), ex);
                } catch (IOException ex) {
                    LOGGER.error(ex.getMessage(), ex);
                } catch (XPathExpressionException ex) {
                    LOGGER.error(ex.getMessage(), ex);
                }

            }
        }

        LOGGER.info("finished validation of '" + validationRequest.getName() + "', validation fail count: " + validationResponse.getValidationFeedbacks().size());

        return validationResponse;
    }

}
