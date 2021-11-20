package com.samsalek.activityjournal.controller;

import com.samsalek.activityjournal.view.FXMLNames;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class RootController extends Controller {

    private static RootController instance = null;

    @FXML AnchorPane rootAnchorPane;

    // ------------------ CONTROLLERS ------------------ //
    private SidePanelController sidePanelController;

    // Singleton. Use getInstance().
    private RootController(){
        super(FXMLNames.ROOT);
    }

    /**
     * This class acts as a Singleton. Returns the instance of the class.
     * @return Instance of class.
     */
    public static RootController getInstance() {
        if(instance == null) {
            instance = new RootController();
            instance.init();
        }
        return instance;
    }

    private void init() {
        rootAnchorPane = loadFxml(this);
        sidePanelController = new SidePanelController(FXMLNames.SIDE_PANEL, rootAnchorPane);
    }

    public AnchorPane getView() {
        return rootAnchorPane;
    }
}
