package com.samsalek.activityjournal.model;

import java.util.Arrays;

public enum Month {
    JANUARY(1),
    FEBRUARY(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AUGUST(8),
    SEPTEMBER(9),
    OCTOBER(10),
    NOVEMBER(11),
    DECEMBER(12);

    private final int nr;

    Month(int nr) {
        this.nr = nr;
    }

    public static Month valueOf(int value) {
        return Arrays.stream(values())
                .filter(month -> month.nr == value)
                .findFirst()
                .orElse(null);
    }

    public int getNr() {
        return nr;
    }

    @Override
    public String toString() {
        return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
    }
}
