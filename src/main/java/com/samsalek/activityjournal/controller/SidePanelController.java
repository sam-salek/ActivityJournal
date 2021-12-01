package com.samsalek.activityjournal.controller;

import com.samsalek.activityjournal.model.*;
import com.samsalek.activityjournal.model.serialization.JSONDeserializer;
import com.samsalek.activityjournal.util.console.DebugConsole;
import com.samsalek.activityjournal.util.event.Event;
import com.samsalek.activityjournal.util.event.EventHandler;
import com.samsalek.activityjournal.util.event.EventListener;
import com.samsalek.activityjournal.util.event.ListenerPriority;
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
    @FXML private Button createActivityButton;

    private Year year;
    private Month currentMonth;

    private ArrayList<MonthButtonController> monthButtonControllers;

    public SidePanelController(Pane parent) {
        super(FXMLNames.SIDE_PANEL, parent);
        year = new Year(2021);

        loadFxmlToParent(parent, this);

        loadMonthData();

        anchorToParent(rootAnchorPane);
        rootAnchorPane.setPrefSize(parent.getPrefWidth(), parent.getPrefHeight());
        yearLabel.setText(year.toString());
        initButtons();

        monthButtonControllers = new ArrayList<>();
        for (Month.Name monthName : Month.Name.values()) {
            monthButtonControllers.add(new MonthButtonController(monthVBox, year, monthName));
        }

        EventHandler.addEventListenerClass(this);
    }

    private void initButtons() {
        previousYearButton.setOnAction(actionEvent -> {
            shiftYear(-1);
            EventHandler.triggerEvent(new Event.YearChangeButtonPressed(year));
        });

        nextYearButton.setOnAction(actionEvent -> {
            shiftYear(1);
            EventHandler.triggerEvent(new Event.YearChangeButtonPressed(year));
        });

        createActivityButton.setOnAction(actionEvent -> {
            if(currentMonth == null) {
                DebugConsole.warning("\"currentMonth\" is null!");
                return;
            }

            Date date = new Date(2021, 9, 3);
            Time time = new Time(4, 53);
            Activity activity = new Activity(date, time, "new activity", Property.get("test4"));
            EventHandler.triggerEvent(new Event.ActivityCreated(activity, year, currentMonth.getName()));
        });
    }

    private void shiftYear(int shiftAmount) {
        year = new Year(year.toInt() + shiftAmount);
        yearLabel.setText(year.toString());
    }

    private void loadMonthData() {
        for (Month m : year.getMonths()) {
            Month month = JSONDeserializer.getInstance().readMonthSaveFile(m);

            if(month != null) {
                year.replaceMonth(month);
            }
        }
    }

    private void setCurrentMonth(Month month) {
        currentMonth = month;
        DebugConsole.notify("\"currentMonth\" was set to " + month.toString());
    }

    @EventListener(priority = ListenerPriority.HIGHEST)
    private void onMonthButtonPressed(Event.MonthButtonPressed event) {
        setCurrentMonth(event.getMonth());
    }

    @EventListener(priority = ListenerPriority.HIGHEST)
    private void onYearChangeButtonPressed(Event.YearChangeButtonPressed event) {
        loadMonthData();
    }
}
