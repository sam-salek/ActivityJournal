package com.samsalek.activityjournal.controller;

import com.samsalek.activityjournal.model.Month;
import com.samsalek.activityjournal.util.event.EventHandler;
import com.samsalek.activityjournal.util.event.Event;
import com.samsalek.activityjournal.view.FXMLNames;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class SidePanelItemController extends Controller {

    @FXML private Button root;

    private Month month;

    public SidePanelItemController(Pane parent, int nr) {
        super(FXMLNames.SIDE_PANEL_ITEM, parent);
        this.month = Month.valueOf(nr);
        loadFxmlToParent(parent, this);

        root.setText(month.toString());
        initOnButtonPress();
    }

    private void initOnButtonPress() {
        root.setOnAction(actionEvent -> EventHandler.triggerEvent(new Event.MonthButtonPress(month)));
    }
}
