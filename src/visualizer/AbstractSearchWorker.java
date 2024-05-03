package visualizer;

import javax.swing.*;
import java.util.*;

public abstract class AbstractSearchWorker extends SwingWorker<List<Vertex>, Object> {
    List<Vertex> vertices;
    List<Edge> edges;
    Set<Vertex> visited = new HashSet<>();
    JLabel statusLabel;
    List<Vertex> solution = new LinkedList<>();
    Algorithm algorithm;

    public AbstractSearchWorker(List<Vertex> vertices, List<Edge> edges, JLabel statusLabel, Algorithm algorithm) {
        this.vertices = vertices;
        this.edges = edges;
        this.statusLabel = statusLabel;
        this.algorithm = algorithm;
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
}
