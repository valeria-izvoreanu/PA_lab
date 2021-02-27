package com.company;

//represents the destinations which require the commodity
//identified by its name
public class Destination {
    private String name;

    public Destination(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
