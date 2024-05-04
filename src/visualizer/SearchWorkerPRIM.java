package visualizer;

import com.jogamp.common.nio.Buffers;

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
    protected void done() {
        statusLabel.setText(this.toString());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        Iterator<Vertex> iterator = vertices.stream().filter(x -> x != startNode)
                .sorted(Comparator.comparing(Vertex::getVertexId))
                .iterator();

//        String output = vertices.stream().filter(x -> x != startNode).sorted(Comparator.comparing(Vertex::getVertexId)).map(x -> x.getVertexId() + "=" + parents.get(x).getVertexId()).collect(Collectors.joining(", "));

        while (iterator.hasNext()) {
            Vertex vertex = iterator.next();
            if (vertex == startNode) {
                continue;
            }
            sb.append(vertex.getVertexId())
                    .append("=")
                    .append(parents.get(vertex).getVertexId());
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    @Override
    protected List<Vertex> doInBackground() throws Exception {
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
        return solution;
    }



}
