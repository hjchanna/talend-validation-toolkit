package com.hjchanna.talend.util;

import com.hjchanna.talend.Constraints;

public class ValidationUtil {
    public static String getValidationResponseLevel(String validationRequestLevel) {
        if (validationRequestLevel == null) {
            throw new AssertionError("No such validation request level");
        }

        switch (validationRequestLevel) {
            case Constraints.REQUEST_WARN:
                return Constraints.RESPONSE_WARN;
            case Constraints.REQUEST_ERROR:
                return Constraints.RESPONSE_ERROR;
            default:
                throw new AssertionError("No such validation request level " + validationRequestLevel);
        }

    }
}
