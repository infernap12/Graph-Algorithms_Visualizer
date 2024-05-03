package visualizer;

import javax.swing.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class SearchWorkerBFS extends AbstractSearchWorker {
    Vertex startNode;

    public SearchWorkerBFS(Vertex startNode,
                           List<Vertex> vertices,
                           List<Edge> edges,
                           JLabel statusLabel
    ) {
        super(vertices, edges, statusLabel, Algorithm.BFS);
        this.startNode = startNode;
    }

    @Override
    protected List<Vertex> doInBackground() throws Exception {
        bfs(startNode);

        return solution;
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

}
