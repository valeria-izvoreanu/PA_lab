package com.company;

public class Main {
    public static void main(String[] args) {
        //creates an array of Source,
        //showcasing the constructor and toString method
        Source[] sources = new Source[3];
        sources[0] = new Source("S1", SourceType.WAREHOUSE);
        System.out.println(sources[0].getName());
        sources[1] = new Source("S2", SourceType.FACTORY);
        System.out.println(sources[1]);
        sources[2] = new Source("S3", SourceType.FACTORY);

        //creates an array of Destination
        Destination[] destinations = new Destination[3];
        destinations[0] = new Destination("D1");
        destinations[1] = new Destination("D2");
        destinations[2] = new Destination("D3");

        //creates a new instance for the Problem
        int[][] arr = {{2, 3, 1}, {5, 4, 8}, {5, 6, 8}};
        Problem prob = new Problem();
        prob.setN(3);
        prob.setCost(arr);
        prob.setSources(sources);
        prob.setDestinations(destinations);
        int[] demand = {20, 25, 25};
        int[] supply = {10, 35, 25};
        prob.setDemand(demand);
        prob.setSupply(supply);

        //prints the instance
        System.out.println(prob);

    }

}
