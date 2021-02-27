package com.company;

import java.util.Arrays;

//represents an instance of the given problem
public class Problem {
    int n;
    private Source[] sources;
    private Destination[] destinations;
    private int[] supply;
    private int[] demand;

    //cost[i][j] = how much it costs fo Si to transport
    //supplies to Dj
    private int[][] cost;

    //sets the size of the problem,
    //as in how many sources and destinations
    public void setN(int n) {
        this.n = n;
    }

    public void setCost(int[][] cost) {
        this.cost = cost;
    }

    public void setDestinations(Destination[] destinations) {
        this.destinations = destinations;
    }

    public void setSources(Source[] sources) {
        this.sources = sources;
    }

    //creates an array which saves how many units each
    //destination is requiring
    public void setDemand(int[] demand) {
        this.demand = demand;
    }

    //creates an array with the capacity of each source
    public void setSupply(int[] supply) {
        this.supply = supply;
    }

    public int getN() {
        return n;
    }

    public int[] getDemand() {
        return demand;
    }

    public int[] getSupply() {
        return supply;
    }

    public int[][] getCost() {
        return cost;
    }

    public Destination[] getDestinations() {
        return destinations;
    }

    public Source[] getSources() {
        return sources;
    }

    //turns an instance from the class Problem in a string
    @Override
    public String toString() {
        return "Problem{" +
                "n=" + n +
                ", sources=" + Arrays.toString(sources) +
                ", destinations=" + Arrays.toString(destinations) +
                ", supply=" + Arrays.toString(supply) +
                ", demand=" + Arrays.toString(demand) +
                ", cost=" + Arrays.deepToString(cost) +
                '}';
    }
}
