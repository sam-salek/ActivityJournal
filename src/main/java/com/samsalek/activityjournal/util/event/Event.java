package com.samsalek.activityjournal.util.event;

import com.samsalek.activityjournal.model.Month;
import com.samsalek.activityjournal.model.Year;

public abstract class Event {

    public static class YearChange extends Event {

        private final Year year;

        public YearChange(Year year) {
            this.year = year;
        }

        public Year getYear() {
            return year;
        }
    }

    public static class MonthButtonPress extends Event {

        private final Month month;

        public MonthButtonPress(Month month) {
            this.month = month;
        }

        public Month getMonth() {
            return month;
        }
    }
}
