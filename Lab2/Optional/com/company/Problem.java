package com.company;

import java.util.Arrays;

//represents an instance of the given problem
public class Problem {
    int n;
    private SourceAbstract[] sources;
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
        try {
            for (int i = 0; i < destinations.length; i++) {
                for (int j = 0; j < destinations.length; j++) {
                    if (destinations[i].equals(destinations[j]) && i != j) {
                        throw new Exception("Nu sunt permise 2 destinatii la fel");
                    }
                }
            }
            this.destinations = destinations;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSources(SourceAbstract[] sources) {
        try {
            for (int i = 0; i < sources.length; i++) {
                for (int j = 0; j < sources.length; j++) {
                    if (sources[i].equals(sources[j]) && i != j) {
                        throw new Exception("Nu sunt permise 2 surse la fel");
                    }
                }
            }
            this.sources = sources;
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public SourceAbstract[] getSources() {
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
