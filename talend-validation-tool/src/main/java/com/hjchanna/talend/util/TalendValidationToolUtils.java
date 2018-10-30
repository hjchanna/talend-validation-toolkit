package com.hjchanna.talend.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;

/**
 *
 * @author ChannaJ
 */
public class TalendValidationToolUtils {

    private static final String TALEND_PROJECT_FILENAME = "talend.project";

    public static final boolean isTalendProject(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.equalsIgnoreCase(TALEND_PROJECT_FILENAME);
                }
            });
            if (files != null) {
                for (File childFile : files) {
                    if ((!childFile.isDirectory()) && childFile.getName().equalsIgnoreCase(TALEND_PROJECT_FILENAME)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static InputStream getResourceAsStream(String resource) {
        return TalendValidationToolUtils.class.getClassLoader().getResourceAsStream(resource);
    }
}
