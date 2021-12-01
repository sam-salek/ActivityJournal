package com.samsalek.activityjournal.util.event;

import com.samsalek.activityjournal.model.Activity;
import com.samsalek.activityjournal.model.Month;
import com.samsalek.activityjournal.model.Year;

public abstract class Event {

    public static class YearChangeButtonPressed extends Event {

        private final Year year;

        public YearChangeButtonPressed(Year year) {
            this.year = year;
        }

        public Year getYear() {
            return year;
        }
    }

    public static class MonthButtonPressed extends Event {

        private final Month month;

        public MonthButtonPressed(Month month) {
            this.month = month;
        }

        public Month getMonth() {
            return month;
        }
    }

    public static class ActivityCreated extends Event {

        private final Activity activity;
        private final Year year;
        private final Month.Name monthName;

        public ActivityCreated(Activity activity, Year year, Month.Name monthName) {
            this.activity = activity;
            this.year = year;
            this.monthName = monthName;
        }

        public Activity getActivity() {
            return activity;
        }

        public Year getYear() {
            return year;
        }

        public Month.Name getMonthName() {
            return monthName;
        }
    }
}
