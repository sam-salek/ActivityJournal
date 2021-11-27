package com.samsalek.activityjournal.model;

public class Activity {

    private Date date;
    private Time time;
    private String description;

    public Activity(Date date, Time time, String description) {
        this.date = date;
        this.time = time;
        this.description = description;
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
}
