package visualizer;

import javax.swing.*;
import java.util.*;

class SearchWorkerDFS extends AbstractSearchWorker {
    Vertex startNode;

    public SearchWorkerDFS(Vertex startNode,
                           List<Vertex> vertices,
                           List<Edge> edges,
                           JLabel statusLabel
    ) {
        super(vertices, edges, statusLabel, Algorithm.DFS);
        this.startNode = startNode;
    }

    @Override
    protected List<Vertex> doInBackground() {
        dfs(startNode);
        return solution;
    }

    private void dfs(Vertex node) {
        visited.add(node);
        solution.add(node);
        List<Edge> adjacentEdges = edges.stream().filter(x -> x.v1 == node).sorted().toList();
        for (Edge edge : adjacentEdges) {
            if (!visited.contains(edge.v2)) {
                dfs(edge.v2);
            }
        }
    }
}
