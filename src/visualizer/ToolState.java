package visualizer;

public enum ToolState {
    VERTEX_CREATION("Add a Vertex"),
    EDGE_CREATION("Add an Edge"),
    VERTEX_DELETION("Remove a Vertex"),
    EDGE_DELETION("Remove an Edge"),
    NONE("None");

    final String NAME;

    ToolState(String stateName) {
        this.NAME = stateName;
    }
}
