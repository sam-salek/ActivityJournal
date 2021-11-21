package com.samsalek.activityjournal.controller;

import javafx.scene.layout.Pane;

public class PropertyItemController extends Controller {

    public PropertyItemController(String fxmlName, Pane parent) {
        super(fxmlName, parent);

        loadFxmlToParent(parent, this);
    }
}
