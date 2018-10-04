package com.hjchanna.talend;

import com.hjchanna.talend.dto.ValidationResponse;
import org.junit.Test;

import java.io.File;
import java.net.URI;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TalendValidationUtilTest {

    @Test
    public void validateTalendProject() {
        try {
            URI t = TalendValidationUtilTest.class.getClassLoader().getResource(".").toURI();
            File f = new File(t);
            System.out.println(f);

            URI talendProjectURI = getClass().getClassLoader().getResource("sample-talend-project").toURI();
            File talendProjectRoot = new File(talendProjectURI);
            System.out.println(talendProjectRoot.getAbsolutePath());

            List<ValidationResponse> validationResponses = TalendValidationUtil.getInstance().validateTalendProject(talendProjectRoot);

            for (ValidationResponse validationResponse : validationResponses) {
                System.out.println("------------------------------------------------------------------------");
                System.out.println(validationResponse.getValidationRequest().getName());
                System.out.println("------------------------------------------------------------------------");

                for (ValidationResponse.ValidationFeedback feedback : validationResponse.getValidationFeedbacks()) {
                    System.out.println("\t" + feedback);
                }
                System.out.println();
            }

            assertEquals(2, validationResponses.size());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}