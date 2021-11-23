package com.samsalek.activityjournal.model;

import java.util.ArrayList;

public class Year {

    private int year;
    private ArrayList<Month> months;

    public Year(int year) {
        this.year = year;
        months = new ArrayList<>();

        initMonths();
    }

    private void initMonths() {
        for (Month month : Month.values()) {
            months.add(month);
        }
    }

    @Override
    public String toString() {
        return Integer.toString(year);
    }

    public int toInt() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
