package com.samsalek.activityjournal.model.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

class SerializationUtil {

    public static final Gson gson = new GsonBuilder()
                                        .setPrettyPrinting()
                                        .serializeNulls()
                                        .create();

    /**
     * Name of the main directory folder.
     */
    public static final String directoryName = ".activity_journal";

    /**
     * Path to the main directory for all save files.
     */
    public static final String directoryPath = System.getProperty("user.home") + File.separatorChar + directoryName;

    public static boolean directoryExists() {
        File directory = new File(directoryPath);
        return directory.exists();
    }
}
