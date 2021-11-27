package com.samsalek.activityjournal.controller;

import com.samsalek.activityjournal.model.Activity;
import com.samsalek.activityjournal.util.event.EventHandler;
import com.samsalek.activityjournal.util.event.Event;
import com.samsalek.activityjournal.view.FXMLNames;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class ContentPanelController extends Controller {

    @FXML private AnchorPane rootAnchorPane;
    @FXML private AnchorPane firstTopPanelAnchorPane;
    @FXML private AnchorPane secondTopPanelAnchorPane;
    @FXML private ScrollPane activityItemsScrollPane;
    @FXML private FlowPane activityItemsFlowPane;

    private final PropertyPanelController firstPropertyPanelController;
    private final PropertyPanelController secondPropertyPanelController;

    public ContentPanelController(Pane parent) {
        super(FXMLNames.CONTENT_PANEL, parent);
        loadFxmlToParent(parent, this);
        anchorToParent(rootAnchorPane);
        anchorToParent(activityItemsFlowPane);

        firstPropertyPanelController = new PropertyPanelController(firstTopPanelAnchorPane, "2021");
        secondPropertyPanelController = new PropertyPanelController(secondTopPanelAnchorPane, "JANUARY");

        initEventListeners();
    }

    private void initEventListeners() {
        EventHandler.createListener(Event.YearChange.class, event -> {
            firstPropertyPanelController.setPanelName(event.getYear().toString());
        });

        EventHandler.createListener(Event.MonthButtonPress.class, event -> {
            secondPropertyPanelController.setPanelName(event.getMonth().toString());
            populateActivityItems(event);
        });
    }

    private void populateActivityItems(Event.MonthButtonPress event) {
        activityItemsFlowPane.getChildren().clear();
        for (Activity activity : event.getMonth().getProperties()) {
            new ActivityItemController(activityItemsFlowPane, activity);
        }
    }
}
