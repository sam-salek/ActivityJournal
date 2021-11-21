package com.samsalek.activityjournal.controller;

import com.samsalek.activityjournal.view.FXMLNames;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class PropertyPanelController extends Controller {

    @FXML private AnchorPane rootAnchorPane;
    @FXML private Label nameLabel;
    @FXML private HBox propertiesHBox;

    private String name;

    private ArrayList<PropertyItemController> propertyItemControllers;

    public PropertyPanelController(String fxmlName, Pane parent, String name) {
        super(fxmlName, parent);
        this.name = name;

        loadFxmlToParent(parent, this);

        propertyItemControllers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            propertyItemControllers.add(new PropertyItemController(FXMLNames.PROPERTY_ITEM, propertiesHBox));
        }
    }

    @Override
    protected void initFXML() {
        nameLabel.setText(name);
    }

    public void setPanelName(String text) {
        nameLabel.setText(text);
    }
}
