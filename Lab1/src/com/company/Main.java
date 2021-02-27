package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //read number of vertices
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        //create adjacency list
        ArrayList<ArrayList<Integer>> adj
                = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());
        //choose a parent for each vertex, since it's a tree
        //each vertex should have one parent
        int parent;
        for (int i = 1; i < n; i++) {
            parent = ((int) (Math.random() * 1000)) % i;
            adj.get(parent).add(i);
        }
        printTree(adj, 0, 0);

    }

    static void printTree(ArrayList<ArrayList<Integer>> adjacencyList, int x, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        //if it's a leaf the vertex has a - in front, else it has a +
        if (adjacencyList.get(x).size() == 0) System.out.print("-");
        else System.out.print("+");
        System.out.println("node" + x);
        //DFS, depth being the level on which the vertex is on
        for (int j = 0; j < adjacencyList.get(x).size(); j++) {
            printTree(adjacencyList, adjacencyList.get(x).get(j), depth + 1);
        }
    }

}
