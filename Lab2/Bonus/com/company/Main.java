package com.company;

public class Main {
    public static void main(String[] args) {
        //creates an array of SourceAbstract,
        //showcasing the constructor and toString method
        SourceAbstract[] sources = new SourceAbstract[3];
        sources[0] = new Warehouse("S1");
        sources[1] = new Factory("S2");
        sources[2] = new Factory("S3");

        //creates an array of Destination
        Destination[] destinations = new Destination[4];
        destinations[0] = new Destination("D1");
        destinations[1] = new Destination("D2");
        destinations[2] = new Destination("D3");
        destinations[3] = new Destination("D4");

        //creates a new instance for the Problem
        int[][] arr = {{3, 1, 7, 4}, {2, 6, 5, 9}, {8, 3, 3, 2}};
        Problem prob = new Problem();
        prob.setN(3);
        prob.setM(4);
        prob.setCost(arr);
        prob.setSources(sources);
        prob.setDestinations(destinations);
        int[] demand = {250, 350, 400, 200};
        int[] supply = {300, 400, 500};
        prob.setDemand(demand);
        prob.setSupply(supply);

        //creates a solution class which will solve the problem
        Solution solution = new Solution();
        long answer = solution.computeCost(prob);
        System.out.println("Raspuns exemplu = " + answer);

        solution = new Solution();
        //create random problem instance
        RandomProblem randomProblem = new RandomProblem();
        long rs = solution.computeCost(randomProblem.getPb());
        System.out.println("Raspuns = " + rs);
    }

}
