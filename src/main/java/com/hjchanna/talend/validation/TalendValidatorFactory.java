/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hjchanna.talend.validation;

import com.hjchanna.talend.validation.dto.ValidationRequest;
import com.hjchanna.talend.validation.validator.ContentXmlValidator;
import com.hjchanna.talend.validation.validator.FileNameValidator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ChannaJ
 */
public class TalendValidatorFactory {

    private static final TalendValidatorFactory INSTANCE = new TalendValidatorFactory();

    public static TalendValidatorFactory getInstance() {
        return INSTANCE;
    }

    private TalendValidatorFactory() {
    }

    public List<TalendValidator> getValidators(List<ValidationRequest> validationRequests) {
        List<TalendValidator> talendValidators = new ArrayList<>();

        for (ValidationRequest validationRequest : validationRequests) {
            TalendValidator talendValidator = getValidator(validationRequest);
            talendValidators.add(talendValidator);
        }

        return talendValidators;
    }

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
