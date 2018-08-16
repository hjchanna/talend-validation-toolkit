package com.hjchanna.talend;

import com.hjchanna.talend.dto.ValidationRequest;
import com.hjchanna.talend.dto.ValidationResponse;
import com.hjchanna.talend.validator.TalendValidator;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TalendValidationUtil {
    private static final Logger LOGGER = Logger.getLogger(TalendValidationUtil.class);

    private static final TalendValidationUtil INSTANCE = new TalendValidationUtil();

    private TalendValidationUtil() {

    }

    public static TalendValidationUtil getInstance() {
        return INSTANCE;
    }

    public List<ValidationResponse> validateTalendProject(File projectRoot) {
        LOGGER.info("starting to validate Talend project at " + projectRoot.getAbsolutePath());

        //read all available validation rules
        List<ValidationRequest> validationRequests = TalendValidationParser.getInstance().getValidationRequests();

        //generate validators for validation rules
        List<TalendValidator> talendValidators = TalendValidatorFactory.getInstance().getValidators(validationRequests);

        //validate
        List<ValidationResponse> validationResponses = new ArrayList<>();
        for (TalendValidator talendValidator : talendValidators) {
            validationResponses.add(talendValidator.validate(projectRoot));
        }

        LOGGER.debug("finished validation of Talend project " + projectRoot.getName());

        return validationResponses;
    }
}
