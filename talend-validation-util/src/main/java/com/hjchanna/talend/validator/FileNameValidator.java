/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hjchanna.talend.validator;

import com.hjchanna.talend.util.FileUtil;
import com.hjchanna.talend.dto.ValidationRequest;
import com.hjchanna.talend.dto.ValidationResponse;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;

/**
 * @author ChannaJ
 */
public class FileNameValidator implements TalendValidator {
    private static final Logger LOGGER = Logger.getLogger(FileNameValidator.class);

    private final ValidationRequest validationRequest;

    public FileNameValidator(ValidationRequest validationRequest) {
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
                boolean matches = (sourceFile.getName().matches(validationRequest.getAssertionRegex()));
                LOGGER.debug("Validating file " + sourceFile.getName() + " against regex '" + validationRequest.getAssertionRegex() + "', matches: " + matches);
                if (!matches) {
                    String fileNameWithoutExtension = FileUtil.getFileRelativePathWithoutExtension(validationDir, sourceFile);
                    validationResponse.getMessages().add(validationRequest.getName() + " is failed for object " + fileNameWithoutExtension);
                }
            }
        }

        LOGGER.info("finished validation of '" + validationRequest.getName() + "', validation fail count: " + validationResponse.getMessages().size());

        return validationResponse;
    }
}
