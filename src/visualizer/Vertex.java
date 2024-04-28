package visualizer;

import javax.swing.*;
import java.awt.*;

public class Vertex extends JPanel {
    JLabel vertexLabel;
    private String vertexId;

    public Vertex(String vertexId) {
        this.vertexId = vertexId;
        this.setLayout(null);
        this.setName("Vertex " + vertexId);
        this.vertexLabel = new JLabel(String.valueOf(vertexId));
        vertexLabel.setName("VertexLabel " + vertexId);
        vertexLabel.setBounds(0, 0, 50, 50);
        vertexLabel.setHorizontalAlignment(JLabel.CENTER);
        vertexLabel.setVerticalAlignment(JLabel.CENTER);
        vertexLabel.setOpaque(false);
        vertexLabel.setForeground(Color.RED);// Set label color to make it stand out
        vertexLabel.setFont(vertexLabel.getFont().deriveFont(vertexLabel.getFont().getSize() * 2f));
        this.setBackground(new Color(0, 0, 0, 0));
        this.add(vertexLabel);
        System.out.println("vertexLabel = " + vertexLabel.getText());
    }


 @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillOval(0, 0, this.getWidth(), this.getHeight());
    }
}
