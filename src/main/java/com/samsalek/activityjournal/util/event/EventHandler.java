package com.samsalek.activityjournal.util.event;

import com.samsalek.activityjournal.util.console.DebugConsole;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handles the interaction between events and their listeners.
 * This class can create listeners to events, and can trigger an Event.
 */
public class EventHandler {


    private static final HashMap<Class<? extends Event>, ArrayList<EventListener>> eventListenerMap = new HashMap<>();

    private EventHandler(){}

    /**
     * Creates and adds a listener of Event to the EventHandler.
     * @param eventClass Class of event the listener should listen to.
     * @param iEventListenerAction The action that should be taken when the event is triggered.
     * @param <T> Any class of type Event.
     * @return The created listener.
     */
    public static <T extends Event> EventListener<T> createListener(Class<T> eventClass, IEventListenerAction<T> iEventListenerAction) {
        if(eventListenerMap.get(eventClass) == null) {
            eventListenerMap.put(eventClass, new ArrayList<>());
        }

        EventListener<T> eventListener = new EventListener<>(eventClass, iEventListenerAction);
        addToMap(eventClass, eventListener);
        return eventListener;
    }

    /**
     * Creates and adds a listener of CompositeEvent to the EventHandler.
     * @param compositeEvent A composite of Events.
     * @param iEventListenerAction The action that should be taken when any of the events contained in compositeEvent is triggered.
     * @param <T> Any class of type Event.
     * @return The created listener.
     */
    public static <T extends Event> ArrayList<EventListener<T>> createListener(CompositeEvent compositeEvent, IEventListenerAction<T> iEventListenerAction) {
        ArrayList<EventListener<T>> eventListenerList = new ArrayList<>();
        for (var eventClass : compositeEvent.getEventClasses()) {
            EventListener<T> eventListener = createListener(eventClass, iEventListenerAction);
            eventListenerList.add(eventListener);
        }
        return eventListenerList;
    }

    /**
     * Removes a listener from the EventHandler.
     * @param eventListener The listener to be removed.
     * @param <T> Any class of type Event.
     */
    public static <T extends Event> void removeListener(EventListener<T> eventListener) {
        removeFromMap(eventListener);
    }

    /**
     * Triggers one or more events. Every listener of the event will have their action performed.
     * @param events The events to be triggered.
     */
    public static void triggerEvent(Event... events) {
        for (Event event : events) {
            triggerEvent(event);
        }
    }

    private static void triggerEvent(Event event) {
        if(eventListenerMap.get(event.getClass()) == null) {
            return;
        }

        for (var listener : eventListenerMap.get(event.getClass())) {
            if(listener.isActive()) {
                listener.getAction().onEvent(event);
                DebugConsole.notify("Event \"" + event.getClass().getSimpleName() + "\" was fired!");
            }
        }
    }



    private static void addToMap(Class<? extends Event> event, EventListener<? extends Event> eventListener) {
        eventListenerMap.get(event).add(eventListener);
    }

    private static void removeFromMap(EventListener<? extends Event> eventListener) {
        eventListenerMap.get(eventListener.getEventClass()).remove(eventListener);
        if(eventListenerMap.get(eventListener.getEventClass()).isEmpty()) {
            eventListenerMap.remove(eventListener.getEventClass());
        }
    }
}
