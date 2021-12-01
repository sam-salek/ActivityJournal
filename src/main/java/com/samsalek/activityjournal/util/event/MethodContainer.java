package com.samsalek.activityjournal.util.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodContainer {

    private final ListenerPriority listenerPriority;
    private final Object object;
    private final Method method;

    public MethodContainer(ListenerPriority listenerPriority, Object object, Method method) {
        this.listenerPriority = listenerPriority;
        this.object = object;
        this.method = method;
    }

    public <T extends Event> void runMethod(T event) {
        try {
            method.setAccessible(true);
            method.invoke(object, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public ListenerPriority getListenerPriority() {
        return listenerPriority;
    }

    public Object getObject() {
        return object;
    }

    public Method getMethod() {
        return method;
    }
}
