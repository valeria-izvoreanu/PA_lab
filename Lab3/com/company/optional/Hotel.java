package com.company;

public class Hotel extends Location implements Classifiable {
    private int rank;

    Hotel() {
    }

    public Hotel(String name, int rank) {
        super(name);
        this.rank = rank;
    }

    @Override
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "rank=" + rank +
                '}';
    }
}
