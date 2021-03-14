package com.company;

import java.time.LocalTime;

public class Church extends Location implements Visitable {
    private LocalTime openingTime, closingTime;

    public Church() {
    }

    public Church(String name, LocalTime openingTime, LocalTime closingTime) {
        super(name);
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    @Override
    public LocalTime getOpeningTime() {
        return openingTime;
    }

    @Override
    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    @Override
    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    @Override
    public String toString() {
        return "Church{" + openingTime +
                " - " + closingTime +
                '}';
    }
}
