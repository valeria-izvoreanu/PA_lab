package com.company;

import java.util.stream.IntStream;

public abstract class Algorithm {
    public static long vogelSolve(Problem pb) {
        int[][] cost = pb.getCost();
        int[] supply = pb.getSupply();
        int[] demand = pb.getDemand();
        int[] rowDifference = new int[pb.getN()];
        int[] rowMinIndex = new int[pb.getN()];//saves index for min on row
        int[] columnDifference = new int[pb.getM()];
        int[] columnMinIndex = new int[pb.getM()];//saves index for min on column
        long finalCost = 0;
        int cellsLeft = 0;

        //while there are left cells that haven't been computed
        //do algorithm
        while (cellsLeft < (pb.getN() * pb.getM())) {

            int[] mins;
            for (int i = 0; i < pb.getN(); i++) {
                //if there is supply left on source
                //get penalty by subtracting from the second min
                //the minimum cost on row
                if (supply[i] != 0) {
                    mins = getPenalty(cost[i]);
                    rowMinIndex[i] = mins[2];
                    rowDifference[i] = mins[1] - mins[0];

                } else rowDifference[i] = -1;
            }
            //similarly for column and destination
            for (int i = 0; i < pb.getM(); i++) {
                if (demand[i] != 0) {
                    mins = getPenalty(getColumn(cost, i));
                    columnMinIndex[i] = mins[2];
                    columnDifference[i] = mins[1] - mins[0];
                } else columnDifference[i] = -1;
            }
            //find the maximum amongst penalties and
            //if it belongs to a row or a column
            int max = -1;
            int maxIndex = 0;
            boolean row = true;
            for (int j = 0; j < pb.getN(); j++) {
                if (max <= rowDifference[j]) {
                    max = rowDifference[j];
                    maxIndex = j;
                }
            }
            for (int j = 0; j < pb.getM(); j++) {
                if (max <= columnDifference[j]) {
                    max = columnDifference[j];
                    maxIndex = j;
                    row = false;
                }
            }
            if (row) {
                //depending if supply is bigger or smaller than demand
                //compute final cost and make cost cells unusable(assign to them
                //big number so that they can't be chosen for penalty)
                //subtract the chosen amount from supply and demand
                if (supply[maxIndex] > demand[rowMinIndex[maxIndex]]) {
                    finalCost = finalCost + cost[maxIndex][rowMinIndex[maxIndex]] * demand[rowMinIndex[maxIndex]];
                    for (int i = 0; i < pb.getN(); i++) {
                        if (cost[i][rowMinIndex[maxIndex]] != 2000000000) {
                            cellsLeft++;
                        }
                        cost[i][rowMinIndex[maxIndex]] = 2000000000;
                    }
                    supply[maxIndex] -= demand[rowMinIndex[maxIndex]];
                    demand[rowMinIndex[maxIndex]] = 0;

                } else {
                    finalCost = finalCost + cost[maxIndex][rowMinIndex[maxIndex]] * supply[maxIndex];
                    for (int i = 0; i < pb.getM(); i++) {
                        if (cost[maxIndex][i] != 2000000000) {
                            cellsLeft++;
                        }
                        cost[maxIndex][i] = 2000000000;
                    }
                    demand[rowMinIndex[maxIndex]] -= supply[maxIndex];
                    supply[maxIndex] = 0;
                }
            } else {
                //similarly if maximum belongs to column penalty
                if (demand[maxIndex] > supply[columnMinIndex[maxIndex]]) {
                    finalCost = finalCost + cost[columnMinIndex[maxIndex]][maxIndex] * supply[columnMinIndex[maxIndex]];
                    for (int i = 0; i < pb.getM(); i++) {
                        if (cost[columnMinIndex[maxIndex]][i] != 2000000000) {
                            cellsLeft++;
                        }
                        cost[columnMinIndex[maxIndex]][i] = 2000000000;
                    }
                    demand[maxIndex] -= supply[columnMinIndex[maxIndex]];
                    supply[columnMinIndex[maxIndex]] = 0;
                } else {
                    finalCost = finalCost + cost[columnMinIndex[maxIndex]][maxIndex] * demand[maxIndex];
                    for (int i = 0; i < pb.getN(); i++) {
                        if (cost[i][maxIndex] != 2000000000) {
                            cellsLeft++;
                        }
                        cost[i][maxIndex] = 2000000000;
                    }
                    supply[columnMinIndex[maxIndex]] -= demand[maxIndex];
                    demand[maxIndex] = 0;
                }
            }

        }
        return finalCost;
    }


    //get cost column
    private static int[] getColumn(int[][] matrix, int column) {
        return IntStream.range(0, matrix.length)
                .map(i -> matrix[i][column]).toArray();
    }

    private static int[] getPenalty(int[] costs) {
        int[] mins = new int[3];
        mins[0] = 1000000000;
        mins[1] = 1000000000;

        //find minimum, second minimum and index for minimum
        for (int i = 0; i < costs.length; i++) {
            if (costs[i] <= mins[0]) {
                mins[1] = mins[0];
                mins[0] = costs[i];
                mins[2] = i;
            } else if (costs[i] <= mins[1]) {
                mins[1] = costs[i];
            }
        }
        return mins;
    }

}
