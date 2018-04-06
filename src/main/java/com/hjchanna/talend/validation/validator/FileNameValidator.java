/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hjchanna.talend.validation.validator;

import com.hjchanna.talend.validation.TalendValidator;
import com.hjchanna.talend.validation.dto.ValidationRequest;
import com.hjchanna.talend.validation.dto.ValidationResponse;
import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author ChannaJ
 */
public class FileNameValidator implements TalendValidator {
    
    private final ValidationRequest validationRequest;
    
    public FileNameValidator(ValidationRequest validationRequest) {
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
                boolean matches = (sourceFile.getName().matches(validationRequest.getAssertionRegex()));
                if (!matches) {
                    String fileNameWithoutExtention = sourceFile.getName();
                    fileNameWithoutExtention = fileNameWithoutExtention.substring(0, fileNameWithoutExtention.lastIndexOf("."));
                    
                    validationResponse.getMessages().add(validationRequest.getName() + " is failed for object " + fileNameWithoutExtention);
                }
            }
        }
        
        return validationResponse;
    }
    
}
