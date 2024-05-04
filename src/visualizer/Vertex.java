package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
                System.out.println(this + " clicked");
                Graph graph = (Graph) Vertex.this.getParent();
                ToolState state = graph.state;
                if (state == ToolState.EDGE_CREATION) {
                    Edge edge = graph.edgeFabricator.submit(Vertex.this);
                    if (edge != null) {
                        System.out.println("adding our boy");
                        graph.add(edge);
                        graph.edges.add(edge);
                        graph.edges.add(edge.getCompliment());
                        graph.revalidate();
                        graph.repaint();
                        graph.edgeFabricator.clear();
                    }

                } else if (state == ToolState.VERTEX_DELETION) {
                    graph.remove(Vertex.this);
                    graph.vertices.remove(Vertex.this);
                    graph.edges.removeIf(edge -> edge.v1 == Vertex.this || edge.v2 == Vertex.this);
                    graph.revalidate();
                    graph.repaint();
                } else if (state == ToolState.NONE && graph.algorithm != null) {
                    MainFrame frame = (MainFrame) graph.getParent().getParent().getParent().getParent();
                    AbstractSearchWorker worker = null;
                    switch (graph.algorithm) {
                        case DFS -> {
                            worker = new SearchWorkerDFS(Vertex.this, graph.vertices, graph.edges, frame.displayLabel);
                        }
                        case BFS -> {
                            worker = new SearchWorkerBFS(Vertex.this, graph.vertices, graph.edges, frame.displayLabel);
                        }
                        case DIJKSTRA -> {
                            worker = new SearchWorkerDIJ(Vertex.this, graph.vertices, graph.edges, frame.displayLabel);
                        }
                        case PRIM -> {
                            worker = new SearchWorkerPRIM(Vertex.this, graph.vertices, graph.edges, frame.displayLabel);
                        }
                        default -> throw new IllegalStateException("Unexpected value: " + graph.algorithm);
                    }
                    worker.execute();
                }
            }
        });
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Vertex{");
        sb.append("vertexId='").append(vertexId).append('\'');
        sb.append('}');
        return sb.toString();
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
