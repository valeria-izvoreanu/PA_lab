package com.company;

import java.util.Random;

public class RandomProblem {
    private Problem pb = new Problem();

    RandomProblem() {
        Random rand = new Random();
        //get random m and n
        pb.setM(rand.nextInt(1000) + 1);
        pb.setN(rand.nextInt(1000) + 1);
        SourceAbstract[] sources = new SourceAbstract[pb.getN()];
        Destination[] destinations = new Destination[pb.getM()];
        int[] demand = new int[pb.getM()];
        int[] supply = new int[pb.getN()];
        int[][] arr = new int[pb.getN()][pb.getM()];
        int totalSum = 0;
        //create new sources
        //and assign random amount for supply
        for (int i = 0; i < pb.getN(); i++) {
            sources[i] = new Factory("S" + i);
            supply[i] = rand.nextInt(10000);
            totalSum += supply[i];
        }
        pb.setSources(sources);
        pb.setSupply(supply);
        //create new destinations
        for (int i = 0; i < pb.getM(); i++) {
            destinations[i] = new Destination("D" + i);
        }
        //assign amount to demand so that the sum is equal to the demand
        int j = 0;
        while (totalSum > 0 && j < pb.getM()) {
            demand[j] = rand.nextInt(10000);
            totalSum -= demand[j++];
        }
        //if any supply left add it to last destination
        if (totalSum > 0) {
            demand[j - 1] += totalSum;
        }
        pb.setDemand(demand);
        //assign random amount to cost matrix
        for (int i = 0; i < pb.getN(); i++) {
            for (int l = 0; l < pb.getM(); l++) {
                arr[i][l] = rand.nextInt(10000);
            }
        }
        pb.setCost(arr);
    }

    public Problem getPb() {
        return pb;
    }
}
