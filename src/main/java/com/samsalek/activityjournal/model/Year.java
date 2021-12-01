package com.samsalek.activityjournal.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Year {

    private int year;
    private final ArrayList<Month> months;

    public Year(int year) {
        this.year = year;
        months = new ArrayList<>();

        initMonths();
    }

    private void initMonths() {
        for (Month.Name month : Month.Name.values()) {
            months.add(new Month(year, month));
        }
    }

    public void replaceMonth(Month replacerMonth) {
        Month month = months.get(replacerMonth.getNr() - 1);
        int i = months.indexOf(month);
        months.set(i, replacerMonth);
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

    public Month getMonth(Month.Name month) {
        return months.get(month.ordinal());
    }

    public List<Month> getMonths() {
        return Collections.unmodifiableList(months);
    }
}
