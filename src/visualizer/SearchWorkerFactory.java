package visualizer;

import javax.swing.*;
import java.util.List;

public class SearchWorkerFactory {

    public static AbstractSearchWorker createSearchWorker(Algorithm algorithm, Vertex vertex, List<Vertex> vertices, List<Edge> edges, JLabel displayLabel) {
        return switch (algorithm) {
            case DFS -> new SearchWorkerDFS(vertex, vertices, edges, displayLabel);
            case BFS -> new SearchWorkerBFS(vertex, vertices, edges, displayLabel);
            case DIJKSTRA -> new SearchWorkerDIJ(vertex, vertices, edges, displayLabel);
            case PRIM -> new SearchWorkerPRIM(vertex, vertices, edges, displayLabel);
        };
    }
}
