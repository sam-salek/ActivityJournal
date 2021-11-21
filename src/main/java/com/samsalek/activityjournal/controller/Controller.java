package com.samsalek.activityjournal.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public abstract class Controller implements Initializable {

    protected String fxmlName;
    protected Pane parent;

    private ArrayList<Controller> observers;

    public Controller(String fxmlName, Pane parent) {
        this.fxmlName = fxmlName;
        this.parent = parent;

        this.observers = new ArrayList<>();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initFXML();
    }

    protected void initFXML(){};

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

    /**
     * Anchors node to its parent.
     * @param node Child.
     */
    protected void anchorToParent(Node node) {
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
    }

    public String getFxmlName() {
        return fxmlName;
    }
}
