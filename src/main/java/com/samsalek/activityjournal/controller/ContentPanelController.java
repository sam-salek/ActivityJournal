package com.samsalek.activityjournal.controller;

import com.samsalek.activityjournal.util.event.EventHandler;
import com.samsalek.activityjournal.util.event.Event;
import com.samsalek.activityjournal.util.event.EventListenerData;
import com.samsalek.activityjournal.view.FXMLNames;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ContentPanelController extends Controller {

    @FXML private AnchorPane rootAnchorPane;
    @FXML private AnchorPane firstTopPanelAnchorPane;
    @FXML private AnchorPane secondTopPanelAnchorPane;
    @FXML private AnchorPane listAnchorPane;

    private PropertyPanelController firstPropertyPanelController;
    private PropertyPanelController secondPropertyPanelController;

    public ContentPanelController(String fxmlName, Pane parent) {
        super(fxmlName, parent);
        loadFxmlToParent(parent, this);
        anchorToParent(rootAnchorPane);

        firstPropertyPanelController = new PropertyPanelController(FXMLNames.PROPERTY_PANEL, firstTopPanelAnchorPane, "2021");
        secondPropertyPanelController = new PropertyPanelController(FXMLNames.PROPERTY_PANEL, secondTopPanelAnchorPane, "JANUARY");

        EventListenerData test = EventHandler.addListener(Event.YearChange.class, event -> {
            firstPropertyPanelController.setPanelName(event.getYear().toString());
        });
        EventHandler.addListener(Event.MonthButtonPress.class, event -> {
            secondPropertyPanelController.setPanelName(event.getMonth().toString());
        });
    }
}
