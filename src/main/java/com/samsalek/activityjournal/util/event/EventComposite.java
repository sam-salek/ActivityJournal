package com.samsalek.activityjournal.util.event;

public class EventComposite<T extends Event> {

    private Class<T>[] eventClasses;

    public EventComposite(Class<T>... eventClasses) {
        this.eventClasses = eventClasses;
    }

    public Class<T>[] getEventClasses() {
        return eventClasses;
    }
}
