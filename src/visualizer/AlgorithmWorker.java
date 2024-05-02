package visualizer;

import javax.swing.*;
import java.util.*;

class AlgorithmWorker extends SwingWorker<List<Vertex>, Object> {
    List<Vertex> vertices;
    List<Edge> edges;
    Algorithm algorithm;
    Vertex startNode;
    Set<Vertex> visited = new HashSet<>();
    JLabel statusLabel;

    List<Vertex> solution = new LinkedList<>();

    public AlgorithmWorker(Vertex startNode, Algorithm algorithm, List<Edge> edges, List<Vertex> vertices, JLabel statusLabel) {
        this.statusLabel = statusLabel;
        this.startNode = startNode;
        this.algorithm = algorithm;
        this.edges = edges;
        this.vertices = vertices;
    }

    @Override
    protected List<Vertex> doInBackground() throws Exception {
        switch (algorithm) {
            case DFS -> {
                dfs(startNode);
            }
            case BFS -> {
                bfs(startNode);
            }
        }

        return solution;
    }

    @Override
    protected void done() {
        if (statusLabel != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(algorithm.toString()).append(" : ");
            Iterator<Vertex> iterator = solution.iterator();
            while (iterator.hasNext()) {
                sb.append(iterator.next().getVertexId());
                if (iterator.hasNext()) {
                    sb.append(" -> ");
                }
            }
            statusLabel.setText(sb.toString());
        }

    }

    private void bfs(Vertex startNode) {
        Deque<Vertex> queue = new LinkedList<>();

        queue.add(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            solution.add(current);

            for (Edge edge : edges) {
                if (edge.v1.equals(current) && !visited.contains(edge.v2)) {
                    queue.add(edge.v2);
                    visited.add(edge.v2);
                }
            }
        }
    }

    private void dfs(Vertex node) {
        visited.add(node);
        solution.add(node);
        for (Edge edge : edges) {
            if (edge.v1.equals(node) && !visited.contains(edge.v2)) {
                dfs(edge.v2);
            }
        }
    }
}
