package com.samsalek.activityjournal.model.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.samsalek.activityjournal.model.Month;

import java.io.File;

class SerializationUtil {

    public static final Gson gson = new GsonBuilder()
                                        .setPrettyPrinting()
                                        .serializeNulls()
                                        .create();

    /**
     * Name of the main directory folder.
     */
    private static final String directoryName = ".activity_journal";

    /**
     * Path to the main directory for all save files.
     */
    public static final String directoryPath = System.getProperty("user.home") + File.separatorChar + directoryName;

    public static boolean directoryExists() {
        File directory = new File(directoryPath);
        return directory.exists();
    }

    public static boolean yearSaveFolderExists(int year) {
        File yearFolder = new File(directoryPath + File.separatorChar + year);
        return yearFolder.exists();
    }

    public static boolean monthSaveFileExists(Month month) {
        File monthSaveFile = new File(getMonthSaveFilePath(month));
        return monthSaveFile.exists();
    }

    public static String getMonthSaveFilePath(Month month) {
        return SerializationUtil.directoryPath + File.separatorChar + month.getYear() + File.separatorChar + getMonthSaveFileName(month) + ".json";
    }

    private static String getMonthSaveFileName(Month month) {
        return month.getYear() + "_" + month.getNr() + month.toString().toLowerCase();
    }
}
