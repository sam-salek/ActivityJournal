package com.samsalek.activityjournal.controller;

import com.samsalek.activityjournal.model.Month;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class SidePanelItemController extends Controller {

    @FXML Button root;

    Month month;

    public SidePanelItemController(String fxmlName, Pane parent, int nr) {
        super(fxmlName);
        this.month = Month.valueOf(nr);

        loadFxmlToParent(parent, this);
    }

    @Override
    protected void initFXML() {
        root.setText(month.toString());
    }
}
