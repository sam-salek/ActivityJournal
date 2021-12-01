package com.samsalek.activityjournal.model;

import java.util.ArrayList;

public class Property {

    private static final int MAX_AMOUNT = 5;
    private static final ArrayList<Property> INSTANCES = new ArrayList<>(MAX_AMOUNT);

    private String name;
    private float value;

    private Property(String name, float value) {
        this.name = name;
        this.value = value;
    }

    public static Property create(String name, float value) {
        if(INSTANCES.size() < MAX_AMOUNT) {
            Property property = new Property(name, value);
            INSTANCES.add(property);
            return property;
        } else {
            return null;
        }
    }

    public static Property get(String name) {
        for (Property p : INSTANCES) {
            if(p.name.equals(name)) {
                return p;
            }
        }

        return null;
    }

    public static int count() {
        return INSTANCES.size();
    }

    public static int getMaxAmount() {
        return MAX_AMOUNT;
    }

    public String getName() {
        return name;
    }

    public float getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
