package com.samsalek.activityjournal.util.event;

public class CompositeEvent<T extends Event> {

    private Class<T>[] eventClasses;

    public CompositeEvent(Class<T>... eventClasses) {
        this.eventClasses = eventClasses;
    }

    public Class<T>[] getEventClasses() {
        return eventClasses;
    }
}
