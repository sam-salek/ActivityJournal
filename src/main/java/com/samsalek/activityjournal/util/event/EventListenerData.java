package com.samsalek.activityjournal.util.event;

public class EventListenerData<T extends Event> {

    private final Class<T> eventClass;
    private final IEventListener<T> iEventListener;

    private boolean active;

    EventListenerData(Class<T> eventClass, IEventListener<T> iEventListener) {
        this.eventClass = eventClass;
        this.iEventListener = iEventListener;
        active = true;
    }

    public void enableListener() {
        active = true;
    }

    public void disableListener() {
        active = false;
    }

    IEventListener<T> getIEventListener() {
        return iEventListener;
    }

    public Class<T> getEventClass() {
        return eventClass;
    }

    public boolean isActive() {
        return active;
    }
}
