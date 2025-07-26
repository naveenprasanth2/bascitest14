package org.example.graphs;

public class DisjointSet {
    private int[] parent;
    private int[] rank;

    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    private int findLeader(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            return findLeader(parent[x]);
        }
    }

    public boolean isSame(int x, int y) {
        return findLeader(x) == findLeader(y);
    }
    
}
