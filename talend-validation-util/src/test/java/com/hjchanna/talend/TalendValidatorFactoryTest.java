package com.hjchanna.talend;

import com.hjchanna.talend.dto.ValidationRequest;
import com.hjchanna.talend.dto.ValidationResponse;
import com.hjchanna.talend.validator.TalendValidator;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TalendValidatorFactoryTest {

    @Test
    public void getValidators() {
        //test get instance
        TalendValidatorFactory talendValidatorFactory = TalendValidatorFactory.getInstance();
        assertNotNull(talendValidatorFactory);

        //test validation factory 1
        ValidationRequest vr1 = new ValidationRequest();
        vr1.setValidationType(Constraints.VALIDATION_TYPE_FILE_NAME);

        ValidationRequest vr2 = new ValidationRequest();
        vr2.setValidationType(Constraints.VALIDATION_TYPE_CONTENT_XML);

        List<ValidationRequest> validationRequests = Arrays.asList(new ValidationRequest[]{
                vr1, vr2
        });

        List<TalendValidator> talendValidators = talendValidatorFactory.getValidators(validationRequests);
        assertEquals(2, talendValidators.size());
    }
}