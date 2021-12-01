package com.samsalek.activityjournal.model.serialization;

import com.google.gson.Gson;
import com.samsalek.activityjournal.model.Month;
import com.samsalek.activityjournal.model.Year;
import com.samsalek.activityjournal.util.console.DebugConsole;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JSONSerializer {

    /**
     * The instance of this class.
     */
    private static JSONSerializer instance = null;

    private Gson gson;

    // Private constructor because Singleton class. Use getInstance() instead.
    private JSONSerializer(){}

    /**
     * This class acts as a Singleton. Returns the instance of the class.
     * @return Instance of class.
     */
    public static JSONSerializer getInstance() {
        if(instance == null) {
            instance = new JSONSerializer();
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
    private void verifyDirectory() {
        try {
            File directory = new File(SerializationUtil.directoryPath);
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

    private void verifyYearSaveFolder(int year) {
        try {
            String yearString = Integer.toString(year);
            File yearFolder = new File(SerializationUtil.directoryPath + File.separatorChar + yearString);
            if (!yearFolder.exists()) {
                DebugConsole.warning("Save folder for year \"" + yearString + "\" not found!");
                if (yearFolder.mkdirs()) {
                    DebugConsole.success("Serializer successfully created save folder for year \"" + yearString + "\"!");
                } else {
                    DebugConsole.error("Serializer could not create save folder for year \"" + yearString + "\"!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveMonth(Month month) {
        verifyYearSaveFolder(month.getYear());
        String filePath = SerializationUtil.getMonthSaveFilePath(month);
        saveFile(month, filePath);
    }

    private void saveFile(Object objectToSave, String saveFilePath) {
        try {
            String filepath = saveFilePath + ".json";
            FileWriter fileWriter = new FileWriter( filepath);
            gson.toJson(objectToSave, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
