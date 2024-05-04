package visualizer;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

class SearchWorkerBFS extends AbstractSearchWorker {
    Vertex startNode;
    Set<Vertex> visited = new HashSet<>();
    List<Vertex> solution = new LinkedList<>();

    public SearchWorkerBFS(Vertex startNode,
                           List<Vertex> vertices,
                           List<Edge> edges,
                           JLabel statusLabel
    ) {
        super(vertices, edges, statusLabel, Algorithm.BFS);
        this.startNode = startNode;
    }

    @Override
    protected String doInBackground() {
        bfs(startNode);

        return getSolutionString();
    }

    private void bfs(Vertex startNode) {
        Deque<Vertex> queue = new LinkedList<>();

        queue.add(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            solution.add(current);

            List<Edge> adjacentEdges = edges.stream().filter(x -> x.v1 == current).sorted().toList();
            for (Edge edge : adjacentEdges) {
                if (!visited.contains(edge.v2)) {
                    queue.add(edge.v2);
                    visited.add(edge.v2);
                }
            }
        }
    }

    @Override
    public String getSolutionString() {
        return "BFS : " + solution.stream()
                .map(Vertex::getVertexId)
                .collect(Collectors.joining(" -> "));
    }
}
