/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hjchanna.talend;

/**
 * @author ChannaJ
 */
public class Constraints {
    //validation rules resource location (under resource directory)
    public static final String VALIDATION_FILE_PATH = "validators.json";

    //validation types
    public static final String VALIDATION_TYPE_FILE_NAME = "filename";
    public static final String VALIDATION_TYPE_CONTENT_XML = "content/xml";

    //validation request levels
    public static final String REQUEST_WARN = "WARN";
    public static final String REQUEST_ERROR = "ERROR";

    //validation response levels
    public static final String RESPONSE_SUCCESS = "SUCCESS";
    public static final String RESPONSE_WARN = "WARN";
    public static final String RESPONSE_ERROR = "ERROR";
}
