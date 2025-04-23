package org.example.graphs;

import java.util.Arrays;

public class GraphA {
    private final int[][] matrix;
    private final boolean undirected;
    public GraphA(int n, boolean undirected) {
        matrix = new int[n][n];
        this.undirected = undirected;
    }

    public void addEdge(int u, int v) {
        matrix[u][v] = 1;
        if (undirected) {
            matrix[v][u] = 1;
        }
    }

    public void printMatrix() {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
