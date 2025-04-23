import org.example.graphs.GraphA;

public static void main(String[] args) {
    GraphA graphA = new GraphA(5, true);
    graphA.addEdge(0, 1);
    graphA.addEdge(0, 3);
    graphA.addEdge(1, 2);
    graphA.addEdge(2, 3);
    graphA.addEdge(3, 4);

    graphA.printMatrix();
}