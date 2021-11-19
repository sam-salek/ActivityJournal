package com.samsalek.activityjournal.controller;

import com.samsalek.activityjournal.view.FXMLNames;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class RootController implements Controller {

    private static RootController instance = null;

    AnchorPane view;

    // ------------------ CONTROLLERS ------------------ //
    private SidePanelController sidePanelController;

    // Singleton. Use getInstance().
    private RootController(){}

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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/root.fxml"));
        fxmlLoader.setController(this);

        try {
            view = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sidePanelController = new SidePanelController(FXMLNames.SIDE_PANEL);
        loadFxml(sidePanelController);
    }

    private boolean loadFxml(Controller controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/" + controller.getFxmlName() + ".fxml"));
        fxmlLoader.setController(controller);

        Node node = null;
        try {
            node = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        view.getChildren().add(node);
        return true;
    }

    @Override
    public String getFxmlName() {
        return null;
    }

    public AnchorPane getView() {
        return view;
    }
}
