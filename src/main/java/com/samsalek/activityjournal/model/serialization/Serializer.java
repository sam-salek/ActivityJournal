package com.samsalek.activityjournal.model.serialization;

import com.google.gson.Gson;
import com.samsalek.activityjournal.Test;
import com.samsalek.activityjournal.util.console.DebugConsole;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Serializer {

    /**
     * The instance of this class.
     */
    private static Serializer instance = null;

    private Gson gson;

    // Private constructor because Singleton class. Use getInstance() instead.
    private Serializer(){}

    /**
     * This class acts as a Singleton. Returns the instance of the class.
     * @return Instance of class.
     */
    public static Serializer getInstance() {
        if(instance == null) {
            instance = new Serializer();
            instance.init();
        }
        return instance;
    }

    /**
     * Initializes this class by running necessary methods.
     */
    private void init() {
        gson = SerializationUtil.gson;
        verifyDirectory();
    }

    /**
     * Checks if the save directory exists. One is created if not.
     */
    public void verifyDirectory() {
        File directory;
        try {
            directory = new File(SerializationUtil.directoryPath);
            if (!directory.exists()) {
                DebugConsole.warning("Directory not found!");
                if (directory.mkdirs()) {
                    DebugConsole.success("Serializer successfully created directory folder!");
                } else {
                    DebugConsole.error("Serializer could not create directory folder!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveFile(Test test) {
        try {
            FileWriter fileWriter = new FileWriter(SerializationUtil.directoryPath + File.separatorChar + "test.json");
            gson.toJson(test, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
