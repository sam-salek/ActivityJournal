package com.samsalek.activityjournal.model;

import java.util.ArrayList;
import java.util.Arrays;

public enum Month {
    JANUARY(1),
    FEBRUARY(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AUGUST(8),
    SEPTEMBER(9),
    OCTOBER(10),
    NOVEMBER(11),
    DECEMBER(12);

    private final int nr;
    private ArrayList<Activity> properties;

    Month(int nr) {
        this.nr = nr;
        properties = new ArrayList<>();


        if(nr == 3) {
            properties.add(new Activity(new Date(2021, 9, 5),
                    new Time(13, 46),
                    "Bought a new XC70"));

            properties.add(new Activity(new Date(2021, 3, 24),
                    new Time(6, 32),
                    "Went to the cinema and saw a horror movie with a group of friends"));
        }
    }

    public static Month valueOf(int value) {
        return Arrays.stream(values())
                .filter(month -> month.nr == value)
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
    }

    public int getNr() {
        return nr;
    }

    public ArrayList<Activity> getProperties() {
        return properties;
    }
}
