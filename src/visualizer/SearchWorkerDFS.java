package visualizer;

import javax.swing.*;
import java.util.*;

class SearchWorkerDFS extends AbstractSearchWorker {
    Vertex startNode;
    Set<Vertex> visited = new HashSet<>();
    List<Vertex> solution = new LinkedList<>();

    public SearchWorkerDFS(Vertex startNode,
                           List<Vertex> vertices,
                           List<Edge> edges,
                           JLabel statusLabel
    ) {
        super(vertices, edges, statusLabel, Algorithm.DFS);
        this.startNode = startNode;
    }

    @Override
    protected String doInBackground() {
        dfs(startNode);
        return getSolutionString();
    }

    @Override
    public String getSolutionString() {
        StringBuilder sb = new StringBuilder();
        sb.append(algorithm.toString()).append(" : ");
        Iterator<Vertex> iterator = solution.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().getVertexId());
            if (iterator.hasNext()) {
                sb.append(" -> ");
            }
        }
        return sb.toString();

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
