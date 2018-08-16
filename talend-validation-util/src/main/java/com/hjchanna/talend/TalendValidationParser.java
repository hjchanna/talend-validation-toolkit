/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hjchanna.talend;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hjchanna.talend.dto.ValidationRequest;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ChannaJ
 */
public class TalendValidationParser {
    private static final Logger LOGGER = Logger.getLogger(TalendValidationParser.class);
    private static final TalendValidationParser INSTANCE = new TalendValidationParser();

    private TalendValidationParser() {
    }

    public static TalendValidationParser getInstance() {
        return INSTANCE;
    }

    /**
     * @return parse validation rules defined in resources and return as ValidationRequest objects
     */
    public List<ValidationRequest> getValidationRequests() {
        List<ValidationRequest> validationRequests = new ArrayList<>();

        ObjectMapper jsonObjectMapper = new ObjectMapper();
        try {
            LOGGER.info("start to parse validation rules at " + Constraints.VALIDATION_FILE_PATH);

            //parsing validators.json
            InputStream validationRuleResource = getResourceAsStream(Constraints.VALIDATION_FILE_PATH);
            String[] validationRuleFileNames = jsonObjectMapper.readValue(validationRuleResource, new TypeReference<String[]>() {
            });

            //parse validators
            for (String validationRuleFileName : validationRuleFileNames) {
                LOGGER.info("parsing validation rule " + validationRuleFileName);
                InputStream resourceUri = getResourceAsStream(validationRuleFileName);
                ValidationRequest validationRequest = jsonObjectMapper.readValue(resourceUri, ValidationRequest.class);
                validationRequests.add(validationRequest);
            }

            LOGGER.info("finished parsing validation rules with rule count: " + validationRequests.size());
        } catch (IOException ex) {
            LOGGER.error("error parsing validation rules", ex);
        }

        return validationRequests;
    }

    private InputStream getResourceAsStream(String resource) {
        return getClass().getClassLoader().getResourceAsStream(resource);
    }
}
