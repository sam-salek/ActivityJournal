package com.samsalek.activityjournal.util.event;

import com.samsalek.activityjournal.util.console.DebugConsole;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.HashMap;

public class EventHandler {

    private static final HashMap<Class<? extends Event>, ArrayList<ArrayList<MethodContainer>>> eventListenerMap = new HashMap<>();

    private EventHandler(){}

    public static void addEventListenerClass(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if(isValidMethod(method)) {
                Class<? extends Event> clazz = (Class<? extends Event>) method.getParameterTypes()[0];
                ListenerPriority listenerPriority = method.getAnnotation(EventListener.class).priority();
                addToMap(clazz, listenerPriority, object, method);
            }
        }
    }

    public static void triggerEvent(Event event) {
        DebugConsole.notify("Event \"" + event.getClass().getSimpleName() + "\" was triggered!");

        // For each ListenerPriority...
        for(int i = (ListenerPriority.values().length - 1); i >= 0; i--) {
            ArrayList<MethodContainer> methodContainers = eventListenerMap.get(event.getClass()).get(i);

            // For each MethodContainer in the current priority list...
            for (MethodContainer methodContainer : methodContainers) {
                methodContainer.runMethod(event);
                DebugConsole.success("An EventListener in \"" +
                        methodContainer.getObject().getClass().getSimpleName() +
                        "\" heard the event \"" + event.getClass().getSimpleName() + "\"!");
            }
        }
    }

    private static void addToMap(Class<? extends Event> eventClass, ListenerPriority listenerPriority, Object object, Method method) {
        if(eventListenerMap.get(eventClass) == null) {
            eventListenerMap.put(eventClass, new ArrayList<>());

            for (ListenerPriority lp : ListenerPriority.values()) {
                eventListenerMap.get(eventClass).add(new ArrayList<>());
            }
        }

        MethodContainer methodContainer = new MethodContainer(listenerPriority, object, method);
        eventListenerMap.get(eventClass).get(listenerPriority.ordinal()).add(methodContainer);
    }

    private static boolean isValidMethod(Method method) {
        if(methodIsEventListener(method) && methodHasOneParameter(method)) {
            return methodParameterIsEvent(method.getParameterTypes()[0]);
        }
        return false;
    }

    private static boolean methodIsEventListener(Method method) {
         return method.isAnnotationPresent(EventListener.class);
    }

    private static boolean methodHasOneParameter(Method method) {
        return method.getParameterTypes().length == 1;
    }

    private static boolean methodParameterIsEvent(Class<?> methodParameterType) {
        return Event.class.isAssignableFrom(methodParameterType);
    }
}
