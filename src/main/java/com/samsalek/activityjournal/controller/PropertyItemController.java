package com.samsalek.activityjournal.controller;

import com.samsalek.activityjournal.view.FXMLNames;
import javafx.scene.layout.Pane;

public class PropertyItemController extends Controller {

    public PropertyItemController(Pane parent) {
        super(FXMLNames.PROPERTY_ITEM, parent);
        loadFxmlToParent(parent, this);
    }
}
