package com.hjchanna.talend.validation.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    /**
     * @param root       root directory to find
     * @param location   sub directory relative to the root to list files
     * @param pattern    file name pattern
     * @param listSubDir list in sub directories
     * @return all files which matches given pattern inclusive or exclusive files in sub directories
     */
    public static List<File> listFiles(File root, String location, String pattern, boolean listSubDir) {
        File validationDir = new File(root, location);
        return listFiles(validationDir, pattern, listSubDir);
    }

    /**
     * @param directory    parent directory to find
     * @param pattern      file name pattern
     * @param lookupSubDir lookup in sub dirs
     * @return all files which matches given pattern inclusive or exclusive files in sub directories
     */
    public static List<File> listFiles(File directory, final String pattern, boolean lookupSubDir) {
        List<File> files = new ArrayList<>();

        for (File child : directory.listFiles()) {
            if (child.isDirectory() && lookupSubDir) {
                files.addAll(listFiles(child, pattern, true));
            } else {
                if (child.getName().matches(pattern)) {
                    files.add(child);
                }
            }
        }

        return files;
    }

    public static String getFileRelativePathWithoutExtension(File root, File child) {
        String relativePath = child.getAbsolutePath().replace(root.getAbsolutePath()+File.separator, "");
        return relativePath.substring(0, relativePath.lastIndexOf("."));
    }
}

