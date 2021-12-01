package com.samsalek.activityjournal.controller;

import com.samsalek.activityjournal.model.Activity;
import com.samsalek.activityjournal.model.Month;
import com.samsalek.activityjournal.model.serialization.JSONSerializer;
import com.samsalek.activityjournal.util.event.EventHandler;
import com.samsalek.activityjournal.util.event.Event;
import com.samsalek.activityjournal.util.event.EventListener;
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

        EventHandler.addEventListenerClass(this);
    }

    private void populateActivityItems(Month month) {
        clearActivityItems();
        for (Activity activity : month.getActivities()) {
            new ActivityItemController(activityItemsFlowPane, activity);
        }
    }

    private void clearActivityItems() {
        activityItemsFlowPane.getChildren().clear();
    }

    @EventListener
    private void onYearChangeButtonPressed(Event.YearChangeButtonPressed event) {
        firstPropertyPanelController.setPanelName(event.getYear().toString());
        clearActivityItems();
    }

    @EventListener
    private void onMonthButtonPressed(Event.MonthButtonPressed event) {
        secondPropertyPanelController.setPanelName(event.getMonth().toString());
        populateActivityItems(event.getMonth());
    }

    @EventListener
    private void onActivityCreated(Event.ActivityCreated event) {
        new ActivityItemController(activityItemsFlowPane, event.getActivity());
        //event.getMonth().addActivity(event.getActivity());
        event.getYear().getMonth(event.getMonthName()).addActivity(event.getActivity());

        for (Month month : event.getYear().getMonths()) {
            JSONSerializer.getInstance().saveMonth(month);
        }
    }
}
