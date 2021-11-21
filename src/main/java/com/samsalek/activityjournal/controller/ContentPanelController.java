package com.samsalek.activityjournal.controller;

import com.samsalek.activityjournal.util.event.EventHandler;
import com.samsalek.activityjournal.util.event.Event;
import com.samsalek.activityjournal.view.FXMLNames;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ContentPanelController extends Controller {

    @FXML private AnchorPane rootAnchorPane;
    @FXML private AnchorPane topPanelAnchorPane;

    private PropertyPanelController propertyPanelController;

    public ContentPanelController(String fxmlName, Pane parent) {
        super(fxmlName, parent);

        loadFxmlToParent(parent, this);

        propertyPanelController = new PropertyPanelController(FXMLNames.PROPERTY_PANEL, topPanelAnchorPane, "JANUARY");

        EventHandler.addListener(Event.MonthButtonPress.class, event -> {
            propertyPanelController.setPanelName(event.getMonth().toString());
        });
    }

    @Override
    protected void initFXML() {
        anchorToParent(rootAnchorPane);
    }

    public PropertyPanelController getPropertyPanelController() {
        return propertyPanelController;
    }
}
