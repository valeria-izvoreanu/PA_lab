package com.company;

public abstract class Algorithm {
    public static int[][] solve(Problem pb) {
        int n = pb.getN();
        int[][] supply = new int[n][n];
        //initializes supply matrix with 0 amount;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                supply[i][j] = 0;
            }
        }
        int[] supplySource;
        supplySource = pb.getSupply();
        int[] demand;
        demand = pb.getDemand();
        int ii = 0;
        int jj = 0;
        int sumSource = 0;
        int sumDestination = 0;

        //calculates the total amount demanded and supplied
        for (int i = 0; i < n; i++) {
            sumDestination += demand[i];
            sumSource += supplySource[i];
        }

        //to calculate the supply array go through demand[] and supplySource[]
        //while there are still units requested or to supply
        //and find the optimal cost-supply amount value in a greedy manner
        while (sumDestination > 0 && sumSource > 0) {
            int min = 100000;
            int s = 0;
            //finds the minimal cost
            for (int i = 0; i < n; i++) {
                if (supplySource[i] != 0) {
                    for (int j = 0; j < n; j++) {
                        int c = pb.getCost()[i][j];
                        if (demand[j] != 0 && c <= min && s <= supplySource[i]) {
                            min = c;
                            s = supplySource[i];
                            ii = i;
                            jj = j;
                        }
                    }
                }
            }
            //if the supply that corresponds to S[ii]->D[jj] is bigger than the demand
            //amount then decrease the supply and the demand = 0 since all the
            //units demanded by Djj have been given to it
            //similarly otherwise Sii has given away every unit it has so supplySource = 0
            if (s > demand[jj]) {
                supply[ii][jj] += demand[jj];
                sumDestination -= demand[jj];
                sumSource -= demand[jj];
                supplySource[ii] -= demand[jj];
                demand[jj] = 0;
            } else {
                supply[ii][jj] += s;
                sumSource -= s;
                demand[jj] -= s;
                supplySource[ii] = 0;
            }
        }
        pb.setSupply(supplySource);
        pb.setDemand(demand);
        return supply;
    }
}
