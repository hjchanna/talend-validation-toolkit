/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hjchanna.talend.validation.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ChannaJ
 */
public class ValidationResponse {

    private final ValidationRequest validationRequest;
    private final List<String> messages = new ArrayList<>();

    public ValidationResponse(ValidationRequest validationRequest) {
        this.validationRequest = validationRequest;
    }

    public ValidationRequest getValidationRequest() {
        return validationRequest;
    }

    public List<String> getMessages() {
        return messages;
    }

}
