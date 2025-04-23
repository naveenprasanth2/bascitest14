package org.example.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphB {
    private final boolean undirected;
    private final List<List<Integer>> adj;

    public GraphB(int n, boolean undirected){
        this.undirected = undirected;
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v){
        adj.get(u).add(v);
        if (undirected){
            adj.get(v).add(u);
        }
    }

    public void printList() {
        for (List<Integer> row : adj) {
            System.out.println(row);
        }
    }
}
