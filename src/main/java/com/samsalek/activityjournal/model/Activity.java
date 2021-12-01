package com.samsalek.activityjournal.model;

public class Activity {

    private Date date;
    private Time time;
    private String description;
    private Property property;

    public Activity(Date date, Time time, String description, Property property) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.property = property;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public Property getProperty() {
        return property;
    }
}
