package org.example.graphs;

public class ApplicationB {
    public static void main(String[] args) {
        GraphB graphB = new GraphB(5, true);
        graphB.addEdge(0, 1);
        graphB.addEdge(0, 3);
        graphB.addEdge(1, 2);
        graphB.addEdge(2, 3);
        graphB.addEdge(3, 4);

        graphB.bfs();
    }
}
