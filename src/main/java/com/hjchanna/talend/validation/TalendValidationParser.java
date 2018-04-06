/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hjchanna.talend.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hjchanna.talend.validation.dto.ValidationRequest;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ChannaJ
 */
public class TalendValidationParser {

    private static final String VALIDATION_FILE_PATH = "validation";

    private static final TalendValidationParser INSTANCE = new TalendValidationParser();

    public static TalendValidationParser getInstance() {
        return INSTANCE;
    }

    private TalendValidationParser() {
    }

    public List<ValidationRequest> getValidationRequests() {
        List<ValidationRequest> validationRequests = new ArrayList<>();

        ObjectMapper jsonObjectMapper = new ObjectMapper();

        try {
            URI resourceUri = this.getClass().getClassLoader().getResource(VALIDATION_FILE_PATH).toURI();
            File resourceDir = new File(resourceUri);

            String fileNames[] = resourceDir.list();

            for (String fileName : fileNames) {
                File validationFile = new File(resourceDir, fileName);
                ValidationRequest validationRequest = jsonObjectMapper.readValue(validationFile, ValidationRequest.class);
                validationRequests.add(validationRequest);
            }
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(TalendValidationParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return validationRequests;
    }

    public static void main(String[] args) {
        new TalendValidationParser().getValidationRequests();
    }
}
