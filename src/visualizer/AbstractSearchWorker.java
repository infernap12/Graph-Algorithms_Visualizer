package visualizer;

import javax.swing.*;
import java.util.*;

public abstract class AbstractSearchWorker extends SwingWorker<String, Object> {
    final List<Vertex> vertices;
    final List<Edge> edges;
    JLabel statusLabel;
    Algorithm algorithm;

    public AbstractSearchWorker(List<Vertex> vertices, List<Edge> edges, JLabel statusLabel, Algorithm algorithm) {
        this.vertices = vertices;
        this.edges = edges;
        this.statusLabel = statusLabel;
        this.algorithm = algorithm;
    }

    public abstract String getSolutionString();

    @Override
    protected void done() {
        if (statusLabel != null) {
            statusLabel.setText(this.getSolutionString());
        }

    }
}
