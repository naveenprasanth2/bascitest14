package org.example.graphs;

import java.util.*;

public class GraphB {
    private final boolean undirected;
    private final List<List<Integer>> adj;

    public GraphB(int n, boolean undirected) {
        this.undirected = undirected;
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        if (undirected) {
            adj.get(v).add(u);
        }
    }

    public void printList() {
        for (List<Integer> row : adj) {
            System.out.println(row);
        }
    }

    private void bfsCore(int source, boolean[] visited) {
        if (!visited[source]) {
            visited[source] = true;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(source);

            while (!queue.isEmpty()) {
                System.out.println(queue.peek());
                int top = queue.poll();
                List<Integer> neighbours = adj.get(top);
                for (int neighbour : neighbours) {
                    if (!visited[neighbour]) {
                        visited[neighbour] = true;
                        queue.add(neighbour);
                    }
                }
            }
        }
    }

    public void bfs() {
        boolean[] visited = new boolean[adj.size()];
        Arrays.fill(visited, false);
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                bfsCore(i, visited);
            }
        }
    }

    public void dfsCoreIterative(int source, boolean[] visited){
        if(!visited[source]){
            visited[source] = true;
            Stack<Integer> stack = new Stack<>();
        }
    }

}
