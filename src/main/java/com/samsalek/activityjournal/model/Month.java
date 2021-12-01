package com.samsalek.activityjournal.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Month {

    public enum Name {
        JANUARY,
        FEBRUARY,
        MARCH,
        APRIL,
        MAY,
        JUNE,
        JULY,
        AUGUST,
        SEPTEMBER,
        OCTOBER,
        NOVEMBER,
        DECEMBER
    }

    private int year;
    private Month.Name name;
    private ArrayList<Activity> activities;

    Month(int year, Month.Name monthName) {
        this.year = year;
        this.name = monthName;
        activities = new ArrayList<>();
    }

    public static Month.Name valueOf(int value) {
        return Arrays.stream(Name.values())
                .filter(month -> month.ordinal()+1 == value)
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return this.name.toString().substring(0, 1).toUpperCase() + this.name.toString().substring(1).toLowerCase();
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public int getYear() {
        return year;
    }

    public Name getName() {
        return name;
    }

    public int getNr() {
        return name.ordinal() + 1;
    }

    public List<Activity> getActivities() {
        return Collections.unmodifiableList(activities);
    }
}
