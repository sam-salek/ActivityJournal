package com.samsalek.activityjournal.model.serialization;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.samsalek.activityjournal.model.Month;
import com.samsalek.activityjournal.model.Year;
import com.samsalek.activityjournal.util.console.DebugConsole;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONDeserializer {

    /**
     * The instance of this class.
     */
    private static JSONDeserializer instance = null;

    private Gson gson;

    // Private constructor because Singleton class. Use getInstance() instead.
    private JSONDeserializer(){}

    /**
     * This class acts as a Singleton. Returns the instance of the class.
     * @return Instance of class.
     */
    public static JSONDeserializer getInstance() {
        if(instance == null) {
            instance = new JSONDeserializer();
            instance.init();
        }
        return instance;
    }

    /**
     * Initializes this class by running necessary methods.
     */
    private void init() {
        gson = SerializationUtil.gson;
    }

    public Month readMonthSaveFile(Month month) {
        if(!SerializationUtil.yearSaveFolderExists(month.getYear())) {
            DebugConsole.error("Cannot read file since year save folder does not exist!");
            return null;
        }

        if(SerializationUtil.monthSaveFileExists(month)) {
            String filePath = SerializationUtil.getMonthSaveFilePath(month);
            return readFile(Month.class, filePath);
        } else {
            return null;
        }
    }

    private  <T> T readFile(Class<T> clazz, String filePath) {
        if(!SerializationUtil.directoryExists()) {
            DebugConsole.error("Cannot read file since directory does not exist!");
            return null;
        }

        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return gson.fromJson(reader, clazz);
    }
}
