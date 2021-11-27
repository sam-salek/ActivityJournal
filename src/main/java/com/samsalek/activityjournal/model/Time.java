package com.samsalek.activityjournal.model;

public class Time {

    private int hour;
    private int minute;

    public Time(int hour, int minute) {
        if((hour < 0 || hour > 24) || (minute < 0  || minute > 60)) {
            throw new IllegalArgumentException("Invalid time");
        }

        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public String toString() {
        String hourString;
        String minuteString;

        if(hour < 10) {
            hourString = "0" + hour;
        } else {
            hourString = Integer.toString(hour);
        }

        if(minute < 10) {
            minuteString = "0" + minute;
        } else {
            minuteString = Integer.toString(minute);
        }

        return hourString + ":" + minuteString;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
