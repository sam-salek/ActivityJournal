package com.samsalek.activityjournal.util.event;

public class EventListener<T extends Event> {

    private final Class<T> eventClass;
    private final IEventListenerAction<T> action;

    private boolean active;

    EventListener(Class<T> eventClass, IEventListenerAction<T> action) {
        this.eventClass = eventClass;
        this.action = action;
        active = true;
    }

    public void enable() {
        active = true;
    }

    public void disable() {
        active = false;
    }

    IEventListenerAction<T> getAction() {
        return action;
    }

    public Class<T> getEventClass() {
        return eventClass;
    }

    public boolean isActive() {
        return active;
    }
}
