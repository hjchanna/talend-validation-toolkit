/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hjchanna.talend.validation.test;

import com.hjchanna.talend.validation.TalendValidationParser;
import com.hjchanna.talend.validation.TalendValidator;
import com.hjchanna.talend.validation.TalendValidatorFactory;
import com.hjchanna.talend.validation.dto.ValidationRequest;
import com.hjchanna.talend.validation.dto.ValidationResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ChannaJ
 */
public class ValidatorTest {

    public static void main(String[] args) {
        File root = new File("D:\\git\\talend\\travisperkins\\IMPORTSUPPLIERPAYABLE");

        List<ValidationRequest> validationRequests = TalendValidationParser.getInstance().getValidationRequests();
        List<TalendValidator> talendValidators = TalendValidatorFactory.getInstance().getValidators(validationRequests);

        List<ValidationResponse> validationResponses = new ArrayList<>();
        for (TalendValidator talendValidator : talendValidators) {
            validationResponses.add(talendValidator.validate(root));
        }

        for (ValidationResponse validationResponse : validationResponses) {
            System.out.println(validationResponse.getValidationRequest().getName());
            System.out.println("------------------------------------------------------------------------");

            for (String message : validationResponse.getMessages()) {
                System.out.println("\t" + message);
            }
            System.out.println("");
        }
    }
}
