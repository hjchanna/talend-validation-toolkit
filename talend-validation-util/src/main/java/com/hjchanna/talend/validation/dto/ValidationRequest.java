/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hjchanna.talend.validation.dto;

/**
 * @author ChannaJ
 */
public class ValidationRequest {

    private String name;
    private String description;
    private String level;
    private String[] fileLocations;
    private Boolean includeSubDirectories;
    private String filePattern;
    private String validationType;
    private String sourceXpath;
    private String assertionRegex;

    public ValidationRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String[] getFileLocations() {
        return fileLocations;
    }

    public void setFileLocations(String[] fileLocations) {
        this.fileLocations = fileLocations;
    }

    public Boolean getIncludeSubDirectories() {
        return includeSubDirectories;
    }

    public void setIncludeSubDirectories(Boolean includeSubDirectories) {
        this.includeSubDirectories = includeSubDirectories;
    }

    public String getFilePattern() {
        return filePattern;
    }

    public void setFilePattern(String filePattern) {
        this.filePattern = filePattern;
    }

    public String getValidationType() {
        return validationType;
    }

    public void setValidationType(String validationType) {
        this.validationType = validationType;
    }

    public String getSourceXpath() {
        return sourceXpath;
    }

    public void setSourceXpath(String sourceXpath) {
        this.sourceXpath = sourceXpath;
    }

    public String getAssertionRegex() {
        return assertionRegex;
    }

    public void setAssertionRegex(String assertionRegex) {
        this.assertionRegex = assertionRegex;
    }

}
