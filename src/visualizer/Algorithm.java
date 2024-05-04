package visualizer;

public enum Algorithm {
    DFS("Depth-First Search"),
    BFS("Breadth-First Search"),
    DIJKSTRA("Dijkstra's Algorithm"),
    PRIM("Prim's Algorithm");

    private final String algorithmName;

    Algorithm(String name) {
        this.algorithmName = name;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }
}
