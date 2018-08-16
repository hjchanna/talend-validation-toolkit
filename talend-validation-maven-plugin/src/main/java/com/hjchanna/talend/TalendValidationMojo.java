package com.hjchanna.talend;

import com.hjchanna.talend.dto.ValidationResponse;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.util.List;

@Mojo(name = "validate-talend", defaultPhase = LifecyclePhase.VERIFY)
public class TalendValidationMojo extends AbstractMojo {

    @Parameter(readonly = true, defaultValue = "${project}")
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        File root = project.getModel().getProjectDirectory();

        System.out.println(root);

        getLog().info("--------------------------------------------------------");
        getLog().info("T A L E N D  V A L I D A T I O N  M A V E N  P L U G I N");
        getLog().info("--------------------------------------------------------");

        List<ValidationResponse> validationResponses = TalendValidationUtil.getInstance().validateTalendProject(root);

        for (ValidationResponse validationResponse : validationResponses) {
            getLog().info("--------------------------------------------------------");
            getLog().info("Talend standard validation failed for '" + validationResponse.getValidationRequest().getName() + "'");
            getLog().info("(Rule: " + validationResponse.getValidationRequest().getDescription() + ")");
            getLog().info("--------------------------------------------------------");

            for (String message : validationResponse.getMessages()) {
                getLog().info("\t" + message);
            }
        }

        if (!validationResponses.isEmpty()) {
            throw new MojoExecutionException("Talend standard validation failed.");
        }
    }
}
