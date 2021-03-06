package com.company;

import java.util.Arrays;

public class Solution {
    //x[i][j] = how many units Si supplies to Dj
    private int[][] x;

    public int computeCost(Problem pb) {
        int sum = 0;

        //uses solve method from algorithm class to fin out the minimum cost
        x = Algorithm.solve(pb);
        for (int i = 0; i < pb.getN(); i++) {
            for (int j = 0; j < pb.getN(); j++) {
                sum += (x[i][j] * pb.getCost()[i][j]);
            }
        }
        return sum;
    }
}
