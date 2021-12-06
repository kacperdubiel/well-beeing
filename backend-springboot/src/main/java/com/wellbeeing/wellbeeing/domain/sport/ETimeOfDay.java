package com.wellbeeing.wellbeeing.domain.sport;

public enum ETimeOfDay {
    MORNING(8, 0),
    NOON(12, 0),
    AFTERNOON(16, 0),
    EVENING(20, 0),
    NIGHT(23, 0);

    private final int hour;
    private final int minutes;

    ETimeOfDay(int hour, int minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

}
