package visualizer;

import javax.swing.*;
import java.util.*;

public class SearchWorkerDIJ extends AbstractSearchWorker {
    Map<Vertex, Integer> result = new TreeMap<>(Comparator.comparing(Vertex::getVertexId));
    PriorityQueue<Vertex> pq = new PriorityQueue<>((vertex, t1) -> result.get(vertex).compareTo(result.get(t1)));
    Vertex startNode;

    public SearchWorkerDIJ(Vertex startNode, List<Vertex> vertices, List<Edge> edges, JLabel statusLabel) {
        super(vertices, edges, statusLabel, Algorithm.DIJKSTRA);
        this.startNode = startNode;
    }

    @Override
    public String getSolutionString() {
        final StringBuilder sb = new StringBuilder();
        Iterator<Vertex> iterator = vertices.stream().sorted(Comparator.comparing(Vertex::getVertexId)).iterator();

        while (iterator.hasNext()) {
            Vertex vertex = iterator.next();
            if (vertex == startNode) {
                continue;
            }
            sb.append(vertex.getVertexId())
                    .append("=")
                    .append(result.get(vertex));
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    @Override
    protected String doInBackground() {
        for (Vertex vertex : vertices) {
            result.put(vertex, Integer.MAX_VALUE);
        }

        result.put(startNode, 0);
        pq.add(startNode);

        while (!pq.isEmpty()) {
            Vertex current = pq.poll();

            List<Edge> adjacentEdges = edges.stream()
                    .filter(x -> x.v1 == current)
                    .toList();

            for (Edge edge : adjacentEdges) {
                Vertex neighbor = edge.v2;
                int newDist = result.get(current) + edge.weight;
                if (newDist < result.get(neighbor)) {
                    result.put(neighbor, newDist);
                    pq.add(neighbor);

                }
            }
        }
        return getSolutionString();
    }

}
