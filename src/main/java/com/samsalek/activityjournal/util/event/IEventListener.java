package com.samsalek.activityjournal.util.event;

public interface IEventListener<T extends Event> {

    void onEvent(T event);
}
