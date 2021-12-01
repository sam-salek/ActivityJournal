package com.samsalek.activityjournal.controller;

import com.samsalek.activityjournal.model.Property;
import com.samsalek.activityjournal.view.FXMLNames;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MainController extends Controller {

    private static MainController instance = null;

    @FXML private AnchorPane rootAnchorPane;
    @FXML private AnchorPane sidePanelAnchorPane;
    @FXML private AnchorPane topPanelAnchorPane;
    @FXML private AnchorPane contentPanelAnchorPane;

    // ------------------ CONTROLLERS ------------------ //
    private SidePanelController sidePanelController;
    private PropertyPanelController propertyPanelTotalController;
    private ContentPanelController contentPanelController;

    // Singleton. Use getInstance().
    private MainController(){
        super(FXMLNames.MAIN, null);
    }

    /**
     * This class acts as a Singleton. Returns the instance of the class.
     * @return Instance of class.
     */
    public static MainController getInstance() {
        if(instance == null) {
            instance = new MainController();
            instance.init();
        }
        return instance;
    }

    private void init() {
        rootAnchorPane = loadFxmlAsNode(this);

        Property.create("test1", 1);
        Property.create("test2", 2);
        Property.create("test3", 3);
        Property.create("test4", 4);
        Property.create("test5", 5);

        sidePanelController = new SidePanelController(sidePanelAnchorPane);
        propertyPanelTotalController = new PropertyPanelController(topPanelAnchorPane, "TOTAL");
        contentPanelController = new ContentPanelController(contentPanelAnchorPane);
    }

    public AnchorPane getRootAnchorPane() {
        return rootAnchorPane;
    }
}
