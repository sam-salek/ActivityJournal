package com.samsalek.activityjournal.util.event;

import com.samsalek.activityjournal.util.console.DebugConsole;

import java.util.ArrayList;
import java.util.HashMap;

public class EventHandler {

    private static final HashMap<Class<? extends Event>, ArrayList<EventListenerData>> eventListenerMap = new HashMap<>();

    private EventHandler(){}

    public static <T extends Event> EventListenerData<T> addListener(Class<T> eventClass, IEventListener<T> iEventListener) {
        if(eventListenerMap.get(eventClass) == null) {
            eventListenerMap.put(eventClass, new ArrayList<>());
        }

        EventListenerData<T> eventListenerData = new EventListenerData<>(eventClass, iEventListener);
        addToMap(eventClass, eventListenerData);
        return eventListenerData;
    }

    public static <T extends Event> ArrayList<EventListenerData<T>> addListeners(EventComposite eventComposite, IEventListener<T> iEventListener) {
        ArrayList<EventListenerData<T>> eventListenerDataList = new ArrayList<>();
        for (var eventClass : eventComposite.getEventClasses()) {
            EventListenerData<T> eventListenerData = addListener(eventClass, iEventListener);
            eventListenerDataList.add(eventListenerData);
        }
        return eventListenerDataList;
    }

    public static <T extends Event> void removeListener(EventListenerData<T> eventListenerData) {
        removeFromMap(eventListenerData);
    }

    public static void triggerEvent(Event event) {
        var eventListenerDataList  = eventListenerMap.get(event.getClass());
        if(eventListenerDataList == null) {
            return;
        }

        for (var listenerData : eventListenerDataList) {
            if(listenerData.isActive()) {
                listenerData.getIEventListener().onEvent(event);
                DebugConsole.notify("Event \"" + event.getClass().getSimpleName() + "\" was fired!");
            }
        }
    }

    private static void addToMap(Class<? extends Event> event, EventListenerData<? extends Event> eventListenerData) {
        eventListenerMap.get(event).add(eventListenerData);
    }

    private static void removeFromMap(EventListenerData<? extends Event> eventListenerData) {
        eventListenerMap.get(eventListenerData.getEventClass()).remove(eventListenerData);
        if(eventListenerMap.get(eventListenerData.getEventClass()).isEmpty()) {
            eventListenerMap.remove(eventListenerData.getEventClass());
        }
    }
}
