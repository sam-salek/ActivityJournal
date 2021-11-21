package com.samsalek.activityjournal.controller;

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
        sidePanelController = new SidePanelController(FXMLNames.SIDE_PANEL, sidePanelAnchorPane);
        propertyPanelTotalController = new PropertyPanelController(FXMLNames.PROPERTY_PANEL, topPanelAnchorPane, "TOTAL");
        contentPanelController = new ContentPanelController(FXMLNames.CONTENT_PANEL, contentPanelAnchorPane);
    }

    public AnchorPane getRoot() {
        return rootAnchorPane;
    }

    public AnchorPane getRootAnchorPane() {
        return rootAnchorPane;
    }

    public AnchorPane getSidePanelAnchorPane() {
        return sidePanelAnchorPane;
    }

    public SidePanelController getSidePanelController() {
        return sidePanelController;
    }

    public PropertyPanelController getPropertyPanelTotalController() {
        return propertyPanelTotalController;
    }

    public ContentPanelController getContentPanelController() {
        return contentPanelController;
    }
}
