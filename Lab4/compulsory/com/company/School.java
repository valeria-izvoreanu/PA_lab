package com.company;

public class School implements Comparable<School> {
    private String name;
    private int capacity;

    public School(String name) {
        this.name = name;
    }

    public School(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public int compareTo(School other) {
        if (other == null || this.name == null || other.getName() == null) throw new NullPointerException();
        return this.name.compareTo(other.getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
