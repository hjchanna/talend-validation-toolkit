/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hjchanna.talend;

import com.hjchanna.talend.dto.ValidationRequest;
import com.hjchanna.talend.validator.ContentXmlValidator;
import com.hjchanna.talend.validator.FileNameValidator;
import com.hjchanna.talend.validator.TalendValidator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChannaJ
 */
public class TalendValidatorFactory {
    private static final Logger LOGGER = Logger.getLogger(TalendValidatorFactory.class);
    private static final TalendValidatorFactory INSTANCE = new TalendValidatorFactory();

    private TalendValidatorFactory() {
    }

    public static TalendValidatorFactory getInstance() {
        return INSTANCE;
    }

    /**
     * @param validationRequests defined in resources
     * @return Implements of TalendValidator for given ValidationRequests
     */
    public List<TalendValidator> getValidators(List<ValidationRequest> validationRequests) {
        LOGGER.info("starting to resolve validators for validation requests or rules");
        List<TalendValidator> talendValidators = new ArrayList<>();

        for (ValidationRequest validationRequest : validationRequests) {
            TalendValidator talendValidator = getValidator(validationRequest);
            LOGGER.debug("resolved validator for '" + validationRequest.getName() + "' as " + talendValidator.getClass());
            talendValidators.add(talendValidator);
        }

        LOGGER.info("finished to resolving validators for validation requests or rules");

        return talendValidators;
    }

    //get correct validator for name
    private TalendValidator getValidator(ValidationRequest validationRequest) {
        switch (validationRequest.getValidationType()) {
            case Constraints.VALIDATION_TYPE_FILE_NAME:
                return new FileNameValidator(validationRequest);
            case Constraints.VALIDATION_TYPE_CONTENT_XML:
                return new ContentXmlValidator(validationRequest);
            default:
                throw new AssertionError();
        }
    }

}
