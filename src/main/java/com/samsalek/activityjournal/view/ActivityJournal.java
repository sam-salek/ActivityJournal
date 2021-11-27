package com.samsalek.activityjournal.view;

import com.samsalek.activityjournal.Test;
import com.samsalek.activityjournal.controller.MainController;
import com.samsalek.activityjournal.model.serialization.Deserializer;
import com.samsalek.activityjournal.model.serialization.Serializer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ActivityJournal extends Application {

    private static Stage mainStage;

    /**
     * Runs the view configured in the 'start' method.
     * @param args
     */
    public static void run(String[] args) {
        launch(args);
    }

    /**
     * Applies and configures the main root view.
     * @param stage The main window.
     */
    @Override
    public void start(Stage stage) {
        MainController mainController = MainController.getInstance();
        Scene scene = new Scene(mainController.getRootAnchorPane(), mainController.getRootAnchorPane().getPrefWidth(), mainController.getRootAnchorPane().getPrefHeight());
        stage.setScene(scene);

        stage.setMinWidth(mainController.getRootAnchorPane().getMinWidth());
        stage.setMinHeight(mainController.getRootAnchorPane().getMinHeight());
        String title = "ActivityJournal";
        stage.setTitle(title);
        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("images/icon.png")));
        stage.show();

        mainStage = stage;
        initShutdownHook();

        Test test = Deserializer.getInstance().readFile(Test.class, "test.json");
    }

    /**
     * Initializes the Shutdown Hook AKA what should happen on application shutdown.
     */
    private void initShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Serializer.getInstance().saveFile(new Test("Sam Salek", 21, 283, true));
        }));
    }

    /**
     * Terminates the application and its view.
     */
    public static void terminate() {
        Platform.exit();
    }

    /**
     * Returns the main stage for the view.
     * @return Main stage.
     */
    public static Stage getMainStage() {
        return mainStage;
    }
}
