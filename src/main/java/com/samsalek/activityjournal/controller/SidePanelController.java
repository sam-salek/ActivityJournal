package com.samsalek.activityjournal.controller;

import com.samsalek.activityjournal.model.Year;
import com.samsalek.activityjournal.view.FXMLNames;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

class SidePanelController extends Controller {

    @FXML Label yearLabel;
    @FXML Button previousYearButton;
    @FXML Button nextYearButton;
    @FXML VBox monthVBox;

    Year year;

    ArrayList<SidePanelItemController> sidePanelItemControllers;

    public SidePanelController(String fxmlName, Pane parent) {
        super(fxmlName);
        year = new Year(2021);

        loadFxml(parent, this);

        sidePanelItemControllers = new ArrayList<>();
        for(int i = 0; i < 12; i++) {
            sidePanelItemControllers.add(new SidePanelItemController(FXMLNames.SIDE_PANEL_ITEM, monthVBox, i+1));
        }
    }

    @Override
    public void initFXML() {
        yearLabel.setText(year.toString());
        initButtons();
    }

    public void initButtons() {
        previousYearButton.setOnAction(actionEvent -> onButtonPress(-1));
        nextYearButton.setOnAction(actionEvent -> onButtonPress(1));
    }

    private void onButtonPress(int shift) {
        year = new Year(year.toInt() + shift);
        yearLabel.setText(year.toString());
    }
}