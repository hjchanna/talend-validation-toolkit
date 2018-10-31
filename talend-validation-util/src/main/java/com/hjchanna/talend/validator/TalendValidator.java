package com.hjchanna.talend.validator;

import com.hjchanna.talend.dto.ValidationResponse;

import java.io.File;

/**
 * @author ChannaJ
 */
public interface TalendValidator {

    ValidationResponse validate(File root);
}
