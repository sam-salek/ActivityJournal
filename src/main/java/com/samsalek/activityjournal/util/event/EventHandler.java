package com.samsalek.activityjournal.util.event;

import java.util.ArrayList;
import java.util.HashMap;

public class EventHandler {

    private static HashMap<Class<? extends Event>, ArrayList<IEventListener>> eventListenerMap = new HashMap<>();

    private EventHandler(){}

    public static <T extends Event> void addListener(Class<T> eventClass, IEventListener<T> IEventListener) {
        if(eventListenerMap.get(eventClass) == null) {
            eventListenerMap.put(eventClass, new ArrayList<>());
        }

        addToMap(IEventListener, eventClass);
    }

    public static void fireEvent(Event event) {
        for (var listener : eventListenerMap.get(event.getClass())) {
            listener.onEvent(event);
            System.out.println("Event \"" + event.getClass().getSimpleName() + "\" was fired!");
        }
    }

    private static void addToMap(IEventListener IEventListener, Class<? extends Event> event) {
        eventListenerMap.get(event).add(IEventListener);
    }
}
