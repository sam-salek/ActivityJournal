package com.samsalek.activityjournal.util.event;

import com.samsalek.activityjournal.model.Month;

public class Event {

    private Event(){}

    public static class MonthButtonPress extends Event {

        private Month month;

        public MonthButtonPress(Month month) {
            this.month = month;
        }

        public Month getMonth() {
            return month;
        }
    }
}
