package com.company;

import java.time.LocalTime;

public class Restaurant extends Location implements Visitable, Classifiable {
    private LocalTime openingTime, closingTime;
    private int rank;

    public Restaurant(String name, LocalTime openingTime, LocalTime closingTime, int rank) {
        super(name);
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.rank = rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public int getRank() {
        return rank;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    @Override
    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    @Override
    public LocalTime getClosingTime() {
        return closingTime;
    }

    @Override
    public String toString() {
        return "Restaurant{" + openingTime +
                " - " + closingTime +
                ", rank=" + rank +
                '}';
    }
}
