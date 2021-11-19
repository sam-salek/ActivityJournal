package com.samsalek.activityjournal.controller;

class SidePanelController implements Controller {

    String fxmlName;

    public SidePanelController(String fxmlName) {
        this.fxmlName = fxmlName;
    }

    @Override
    public String getFxmlName() {
        return fxmlName;
    }
}
