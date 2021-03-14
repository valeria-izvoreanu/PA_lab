package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    public void visitableNotPayable() {
        List<Visitable> goodLoc = new ArrayList<>();
        //find all visitable and not payable places
        for (Location l : this.getNodes()) {
            if (l instanceof Visitable && !(l instanceof Payable)) {
                goodLoc.add((Visitable) l);
            }
        }
        //bubble sort
        for (int i = 0; i < goodLoc.size() - 1; i++) {
            for (int j = 0; j < goodLoc.size() - i - 1; j++) {
                if (goodLoc.get(j).getOpeningTime().compareTo(goodLoc.get(j + 1).getOpeningTime()) > 0) {
                    Collections.swap(goodLoc, j, j + 1);
                }
            }
        }
        System.out.println(Arrays.asList(goodLoc));
    }

    @Override
    public String toString() {
        return "City{" +
                "places =" + nodes +
                '}';
    }
}
