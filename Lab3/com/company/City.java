package com.company;

import java.util.ArrayList;
import java.util.List;

public class City {
    private List<Location> nodes = new ArrayList<>();

    public void addLocation(Location node) {
        nodes.add(node);
    }

    public List<Location> getNodes() {
        return nodes;
    }

    public Location getNode(int index) {
        return nodes.get(index);
    }

    @Override
    public String toString() {
        return "City{" +
                "places =" + nodes +
                '}';
    }
}
