package com.samsalek.activityjournal.controller;

import com.samsalek.activityjournal.model.Activity;
import com.samsalek.activityjournal.view.FXMLNames;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ActivityItemController extends Controller {

    @FXML private AnchorPane rootAnchorPane;
    @FXML private Label dateLabel;
    @FXML private Label timeLabel;
    @FXML private Label activityLabel;
    @FXML private AnchorPane propertyItemSlotAnchorPane;

    private PropertyItemController propertyItemController;

    private Activity activity;

    public ActivityItemController(Pane parent, Activity activity) {
        super(FXMLNames.ACTIVITY_ITEM, parent);
        loadFxmlToParent(parent, this);
        this.activity = activity;

        dateLabel.setText(activity.getDate().toString());
        timeLabel.setText(activity.getTime().toString());
        activityLabel.setText(activity.getDescription());
        propertyItemController = new PropertyItemController(propertyItemSlotAnchorPane);
        //rootAnchorPane.setMinSize(parent.getPrefWidth(), parent.getPrefHeight());

    }
}
