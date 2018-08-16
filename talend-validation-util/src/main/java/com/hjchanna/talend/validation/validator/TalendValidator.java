/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hjchanna.talend.validation.validator;

import com.hjchanna.talend.validation.dto.ValidationResponse;

import java.io.File;

/**
 * @author ChannaJ
 */
public interface TalendValidator {

    public ValidationResponse validate(File root);
}
