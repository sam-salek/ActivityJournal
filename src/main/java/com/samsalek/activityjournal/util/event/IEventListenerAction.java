package com.samsalek.activityjournal.util.event;

public interface IEventListenerAction<T extends Event> {

    void onEvent(T event);
}
