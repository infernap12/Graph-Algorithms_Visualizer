package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Vertex extends JPanel {
    JLabel vertexLabel;
    private final String vertexId;

    public Vertex(String vertexId) {
        this.vertexId = vertexId;
        this.setLayout(null);

        this.setName("Vertex " + vertexId);
        this.setBackground(new Color(0, 0, 0, 0));

        buildVertexLabel(vertexId);

        System.out.println("vertexLabel = " + vertexLabel.getText());
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                Vertex source = (Vertex) e.getSource();
                System.out.println(this + " clicked");
                Graph parentGraph = (Graph) source.getParent();
                Graph.ToolState state = parentGraph.state;
                if (state != Graph.ToolState.EDGE_CREATION) {
                    return;
                }
                Edge edge = parentGraph.edgeFabricator.submit(Vertex.this);
                if (edge != null) {
                    System.out.println("adding our boy");
                    parentGraph.add(edge);
                    parentGraph.edges.add(edge);
                    parentGraph.edges.add(edge.getCompliment());
                    parentGraph.revalidate();
                    parentGraph.repaint();
                    parentGraph.edgeFabricator.clear();
                }
            }
        });
    }

    private void buildVertexLabel(String vertexId) {
        this.vertexLabel = new JLabel(String.valueOf(vertexId));
        vertexLabel.setName("VertexLabel " + vertexId);
        vertexLabel.setBounds(0, 0, 50, 50);
        vertexLabel.setHorizontalAlignment(JLabel.CENTER);
        vertexLabel.setVerticalAlignment(JLabel.CENTER);
        vertexLabel.setOpaque(false);
        vertexLabel.setForeground(Color.RED);// Set label color to make it stand out
        vertexLabel.setFont(vertexLabel.getFont().deriveFont(vertexLabel.getFont().getSize() * 2f));
        this.add(vertexLabel);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillOval(0, 0, this.getWidth(), this.getHeight());
    }

    public String getVertexId() {
        return vertexId;
    }
}
