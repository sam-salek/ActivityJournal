package com.samsalek.activityjournal.model.serialization;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Deserializer {

    /**
     * The instance of this class.
     */
    private static Deserializer instance = null;

    private Gson gson;

    // Private constructor because Singleton class. Use getInstance() instead.
    private Deserializer(){}

    /**
     * This class acts as a Singleton. Returns the instance of the class.
     * @return Instance of class.
     */
    public static Deserializer getInstance() {
        if(instance == null) {
            instance = new Deserializer();
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

    public <T> T readFile(Class<T> clazz, String filename) {
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(SerializationValues.directoryPath + File.separatorChar + filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return gson.fromJson(reader, clazz);
    }
}
