package visualizer;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class SearchWorkerPRIM extends AbstractSearchWorker {
    Map<Vertex, Integer> result = new TreeMap<>(Comparator.comparing(Vertex::getVertexId));
    PriorityQueue<Vertex> pq = new PriorityQueue<>((vertex, t1) -> result.get(vertex).compareTo(result.get(t1)));
    Vertex startNode;
    HashMap<Vertex, Vertex> parents = new HashMap<>();

    public SearchWorkerPRIM(Vertex startNode, List<Vertex> vertices, List<Edge> edges, JLabel statusLabel) {
        super(vertices, edges, statusLabel, Algorithm.PRIM);
        this.startNode = startNode;
    }


    @Override
    public String getSolutionString() {
        return vertices.stream().filter(x -> x != startNode)
                .sorted(Comparator.comparing(Vertex::getVertexId))
                .map(x -> x.getVertexId() + "=" + parents.get(x).getVertexId())
                .collect(Collectors.joining(", "));
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
                    parents.put(neighbor, current);
                    pq.add(neighbor);

                }
            }
        }
        return getSolutionString();
    }

}
