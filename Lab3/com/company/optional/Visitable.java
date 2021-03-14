package com.company;

import java.time.Duration;
import java.time.LocalTime;

public interface Visitable {
    LocalTime getOpeningTime();

    void setOpeningTime(LocalTime time);

    LocalTime getClosingTime();

    void setClosingTime(LocalTime time);

    default void defaultTime() {
        setOpeningTime(LocalTime.of(9, 30));
        setClosingTime(LocalTime.of(20, 0));
    }

    static Duration getVisitingDuration(Location loc) {
        return Duration.between(((Visitable) loc).getOpeningTime(), ((Visitable) loc).getClosingTime());
    }
}
