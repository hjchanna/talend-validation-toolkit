# talend-validation-toolkit
[![CircleCI](https://circleci.com/gh/hjchanna/talend-validation-toolkit.svg?style=shield )](https://circleci.com/gh/hjchanna/talend-validation-toolkit)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a08bce06c2d1487e8f98231176dae977)](https://www.codacy.com/app/hjchanna/talend-validation-toolkit?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=hjchanna/talend-validation-toolkit&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/hjchanna/talend-validation-toolkit/branch/master/graph/badge.svg)](https://codecov.io/gh/hjchanna/talend-validation-toolkit)
[![Releases](https://img.shields.io/github/release/hjchanna/talend-validation-toolkit.svg?style=flat)](https://github.com/hjchanna/talend-validation-toolkit/releases)
> Talend Validation Toolkit is a static code analysis tool to motivate Talend developers to design Talend Projects that adheres to a coding standard. It automates the process of checking Talend components for validation rules and spare humans from this boring (but important) task. Talend Validation Toolkit ideal for Talend projects that want to enforce coding standards.

## Installing
Talend Validation Toolkit can be used for multiple purposes and methods such as;
* __As a Java Library__ (module: talend-validation-util)
* __As a Desktop application__ (module: talend-validation-tool)
* __As a Maven Plugin for Talend Project__ (module: talend-validation-maven-plugin)
* __TODO: As a Plugin for a Build Tool which configured for remote SVN Talend Project__

### As a Java Library
````
import com.hjchanna.talend.TalendValidationUtil;
import com.hjchanna.talend.ValidationResponse;
...
File talendProjectRoot = new File("path/to/your/talend/project/");
TalendValidationUtil validationUtil = TalendValidationUtil.getInstance();

//validate the talend project
List<ValidationResponse> validationResponses = validationUtil.validateTalendProject(talendProjectRoot);
````

### As a Desktop Application
Please download an run the `talend-validation-tool.jar` in the release section of this repository. The desktop application allows developers to locate Talend project clonned locally and validate them. 

![Cat](https://raw.githubusercontent.com/hjchanna/talend-validation-toolkit/master/resources/screenshots/talend-validation-tool.PNG)

### As a Maven Plugin
Add following plugin configuration to `pom.xml` of the Talend Project.
````
<plugin>
    <groupId>com.hjchanna.talend</groupId>
    <artifactId>talend-validation-maven-plugin</artifactId>
    <version>1.0</version>

    <executions>
        <execution>
            <id>validate-talend</id>
            <phase>validate</phase>
            <goals>
                <goal>validate-talend</goal>
            </goals>
        </execution>
    </executions>
</plugin>
````

### As a Build Tool Plugin
_This feature is still in development stage._

## Development Guide
### Prerequisites
* Java (JDK 1.7)
* Maven

### Project Structure and Sub Modules
Talend Validation Toolkit has serveral maven modules for the parent project. The project hierarchy as follows.
````
| talend-validation-toolkit
|----| talend-validation-util
|----| talend-validation-tool
|----| talend-validation-maven-plugin
````
### Module: talend-validation-util
talend-validation-util is the main artifact of the talend-validation-toolkit which consists of validation logics and validation configurations. Validation configurations are defined in json format in resource directory. Validation rules json template as follows;

##### JSON Template:
````
{
  "name": "Validation Rule Name",
  "description":"Validation Rule Description",
  "level": "WARN",
  "fileLocations": [
    "context" (folders to match files)
  ],
  "includeSubDirectories": true,
  "filePattern": "file pattern in regex format",
  "validationType": "content/xml", (or filename)
  "sourceXpath": "//contextParameter/@name", (if validationType is content/xml)
  "assertionRegex": "value match pattern in regex"
}
````
##### Example:
````
{
  "name": "Context Parameter Validation",
  "description":"A context variable should be in Camel Case",
  "level": "WARN",
  "fileLocations": [
    "context"
  ],
  "includeSubDirectories": true,
  "filePattern": ".*(.item)",
  "validationType": "content/xml",
  "sourceXpath": "//contextParameter/@name",
  "assertionRegex": "[a-z][A-Za-z0-9]*"
}
````

Note: Once a validattion rule is defined as a JSON, it shoul add to the `validators.json` file which is available in the resource directory. 

##### Available Validators
There are only two validation rules are defined in this stage of the project which are;
* Context Parameter Validation _(Rule: A context variable should be in Camel Case)_
* Joblet Name Validation _(Rule: A joblet name should be in Camel Case)_

### Module: talend-validation-maven-plugin
talend-validation-maven-plugin is a dependent project of talend-validation-util and it has packaged as a maven-plugin which enables to control the maven build. The plugin would be applied in `verify` phase of a maven project in default.

### Build Command
Use following maven commands to build the project from the project root. 
````
mvn clean install
````

### Authors
* Channa Jayamuni (hjchanna@gmail.com)
