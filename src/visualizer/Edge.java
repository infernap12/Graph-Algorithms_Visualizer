package visualizer;

import javax.swing.*;
import java.awt.*;

public class Edge extends JComponent {
    Vertex v1, v2;
    int weight;
    JLabel edgeLabel;

    public Edge(Vertex v1, Vertex v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
        this.setBounds((v1.getX() + v2.getX()) / 2, (v1.getY() + v2.getY()) / 2, Math.abs(v1.getX() - v2.getX()), Math.abs(v1.getY() - v2.getY()));
        edgeLabel = new JLabel(String.valueOf(weight));
        edgeLabel.setName("%s -> %s".formatted(v1.getVertexId(), v2.getVertexId()));
    }

    Edge getCompliment() {
        return new Edge(v2, v1, weight);
    }

    int[] getLineCoords() {
        int[] coords = new int[4];
        coords[0] = v1.getX() + (v1.getWidth() / 2);
        coords[1] = v1.getY() + (v1.getHeight() / 2);
        coords[2] = v2.getX() + (v2.getWidth() / 2);
        coords[3] = v2.getY() + (v2.getHeight() / 2);
        return coords;
    }

    @Override

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}