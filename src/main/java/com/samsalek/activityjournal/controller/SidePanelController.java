package com.samsalek.activityjournal.controller;

import com.samsalek.activityjournal.model.Month;
import com.samsalek.activityjournal.model.Year;
import com.samsalek.activityjournal.util.event.Event;
import com.samsalek.activityjournal.util.event.EventHandler;
import com.samsalek.activityjournal.view.FXMLNames;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

class SidePanelController extends Controller {

    @FXML private AnchorPane rootAnchorPane;
    @FXML private Label yearLabel;
    @FXML private Button previousYearButton;
    @FXML private Button nextYearButton;
    @FXML private VBox monthVBox;

    private Year year;

    private ArrayList<SidePanelItemController> sidePanelItemControllers;

    public SidePanelController(Pane parent) {
        super(FXMLNames.SIDE_PANEL, parent);
        year = new Year(2021);
        loadFxmlToParent(parent, this);

        anchorToParent(rootAnchorPane);
        rootAnchorPane.setPrefSize(parent.getPrefWidth(), parent.getPrefHeight());
        yearLabel.setText(year.toString());
        initButtons();

        sidePanelItemControllers = new ArrayList<>();
        for(int i = 0; i < 12; i++) {
            sidePanelItemControllers.add(new SidePanelItemController(monthVBox, i+1));
        }
    }

    private void initButtons() {
        previousYearButton.setOnAction(actionEvent -> {
            shiftYear(-1);
            EventHandler.triggerEvent(new Event.YearChange(year));
        });
        nextYearButton.setOnAction(actionEvent -> {
            shiftYear(1);
            EventHandler.triggerEvent(new Event.YearChange(year));
        });
    }

    private void shiftYear(int shiftAmount) {
        year.setYear(year.toInt() + shiftAmount);
        yearLabel.setText(year.toString());
    }
}
