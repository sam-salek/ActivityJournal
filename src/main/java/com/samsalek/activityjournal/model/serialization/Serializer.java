package com.samsalek.activityjournal.model.serialization;

import com.google.gson.Gson;
import com.samsalek.activityjournal.Test;

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
        gson = SerializationValues.gson;
    }

    /**
     * Checks if the save directory exists. One is created if not.
     */
    public void verifyDirectory() {
        File directory;
        try {
            directory = new File(SerializationValues.directoryPath);
            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    System.out.println("Serializer successfully created directory folder!");
                } else {
                    System.out.println("Serializer could not create directory folder!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveFile(Test test) {
        try {
            FileWriter fileWriter = new FileWriter(SerializationValues.directoryPath + File.separatorChar + "test.json");
            gson.toJson(test, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
