package com.employeepayroll;

import java.io.File;

public class FilesUtility {

    public static boolean deleteAllFiles(File files) {
        File[] listOfFiles = files.listFiles();
        if( listOfFiles != null) {
            for (File file : listOfFiles) {
                deleteAllFiles(file);
            }
        }
        return files.delete();
    }
}
