package com.samsalek.activityjournal.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

abstract class Controller implements Initializable {

    String fxmlName;

    public Controller(String fxmlName) {
        this.fxmlName = fxmlName;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initFXML();
    }

    protected void loadFxmlToParent(Pane parent, Controller controller) {
        Node node = loadFxmlAsNode(controller);

        if(parent != null) {
            parent.getChildren().add(node);
        }
    }

    protected <T> T loadFxmlAsNode(Controller controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/" + controller.getFxmlName()));
        fxmlLoader.setController(controller);

        T node = null;
        try {
            node = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return node;
    }

    protected void initFXML(){};

    public String getFxmlName() {
        return fxmlName;
    }
}
