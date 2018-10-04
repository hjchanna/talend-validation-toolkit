/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hjchanna.talend.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChannaJ
 */
public class ValidationResponse {
    private final ValidationRequest validationRequest;
    private final List<ValidationFeedback> validationFeedbacks = new ArrayList<>();

    public ValidationResponse(ValidationRequest validationRequest) {
        this.validationRequest = validationRequest;
    }

    public ValidationRequest getValidationRequest() {
        return validationRequest;
    }

    public void addFeedback(String level, String message) {
        this.validationFeedbacks.add(new ValidationFeedback(level, message));
    }

    public List<ValidationFeedback> getValidationFeedbacks() {
        return validationFeedbacks;
    }

    public class ValidationFeedback {
        private String level;
        private String message;

        public ValidationFeedback(String level, String message) {
            this.level = level;
            this.message = message;
        }

        public String getLevel() {
            return level;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return "[" + level + "] " + message;
        }
    }

}
