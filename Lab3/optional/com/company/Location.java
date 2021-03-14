package com.company;

import java.util.HashMap;
import java.util.Map;

public abstract class Location implements Comparable<Location> {
    private String name;
    private Map<Location, Integer> cost = new HashMap<>();

    //description etc
    Location() {
    }

    Location(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(Location node, int value) {
        cost.put(node, value);
    }

    public String getName() {
        return name;
    }

    public Map<Location, Integer> getCost() {
        return cost;
    }

    public Integer getCostLocation(Location place) {
        return cost.get(place);
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }

    @Override
    public int compareTo(Location other) {
        if (other == null || this.name == null || other.name == null) throw new NullPointerException();
        return this.name.compareTo(other.name);
    }
}
