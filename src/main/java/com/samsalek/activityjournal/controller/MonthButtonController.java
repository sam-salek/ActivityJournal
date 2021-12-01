package com.samsalek.activityjournal.controller;

import com.samsalek.activityjournal.model.Month;
import com.samsalek.activityjournal.model.Year;
import com.samsalek.activityjournal.util.event.EventHandler;
import com.samsalek.activityjournal.util.event.Event;
import com.samsalek.activityjournal.util.event.EventListener;
import com.samsalek.activityjournal.view.FXMLNames;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class MonthButtonController extends Controller {

    @FXML private Button root;

    private Month month;

    public MonthButtonController(Pane parent, Year year, Month.Name monthName) {
        super(FXMLNames.MONTH_BUTTON, parent);
        loadFxmlToParent(parent, this);
        this.month = year.getMonth(monthName);

        root.setText(month.toString());
        initOnButtonPress();

        EventHandler.addEventListenerClass(this);
    }

    private void initOnButtonPress() {
        root.setOnAction(actionEvent -> EventHandler.triggerEvent(new Event.MonthButtonPressed(month)));
    }

    @EventListener
    private void onYearChangeButtonPressed(Event.YearChangeButtonPressed event) {
        Year y = event.getYear();
        month = event.getYear().getMonth(month.getName());
    }

}
