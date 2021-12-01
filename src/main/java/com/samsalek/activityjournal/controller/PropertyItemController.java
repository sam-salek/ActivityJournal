package com.samsalek.activityjournal.controller;

import com.samsalek.activityjournal.model.Property;
import com.samsalek.activityjournal.view.FXMLNames;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class PropertyItemController extends Controller {

    @FXML private Label nameLabel;
    @FXML private Label valueLabel;

    Property property;

    public PropertyItemController(Pane parent, Property property) {
        super(FXMLNames.PROPERTY_ITEM, parent);
        loadFxmlToParent(parent, this);

        this.property = property;
        nameLabel.setText(property.getName());
        valueLabel.setText(Double.toString(property.getValue()));
    }
}
