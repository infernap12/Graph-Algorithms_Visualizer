package visualizer;

public enum Algorithm {
    DFS("Depth-First Search"),
    BFS("Breadth-First Search"),
    ;

    private String algorithmName;

    Algorithm(String name) {
        this.algorithmName = name;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }
}
