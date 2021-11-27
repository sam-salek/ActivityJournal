package com.samsalek.activityjournal.model;

public class Date {

    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) {
        if(year < 0 || (month < 1 || month > 12) || (day < 1 || day > 31)) {
            throw new IllegalArgumentException("Invalid date");
        }

        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString() {
        String monthString;
        String dayString;

        if(month < 10) {
            monthString = "0" + month;
        } else {
            monthString = Integer.toString(month);
        }

        if(day < 10) {
            dayString = "0" + day;
        } else {
            dayString = Integer.toString(day);
        }

        return year + "-" + monthString + "-" + dayString;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
