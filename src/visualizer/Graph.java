package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph extends JPanel {
    private static final int VERTEX_HEIGHT = 50;
    private static final int VERTEX_WIDTH = 50;

    ToolState state = ToolState.VERTEX_CREATION;
    Algorithm algorithm = null;

    List<Vertex> vertices = new ArrayList<>();
    List<Edge> edges = new ArrayList<>();
    EdgeManufacturer edgeFabricator = new EdgeManufacturer();

    public Graph() {
        super();
        this.setName("Graph");
        setLayout(null);
        this.setBackground(Color.BLACK);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (Graph.this.state == ToolState.VERTEX_CREATION) {

                    String input;
                    while (true) {
                        input = JOptionPane.showInputDialog(null, "Enter Vertex ID", "Vertex", JOptionPane.PLAIN_MESSAGE);
                        //cancel was pressed
                        if (input == null) {
                            break;
                        }
                        if (input.length() == 1 && !input.isBlank()) {

                            Vertex vertex = new Vertex(input);
                            Graph.this.add(vertex);
                            vertices.add(vertex);
                            vertex.setBounds(e.getX() - (Graph.VERTEX_WIDTH / 2), e.getY() - (Graph.VERTEX_HEIGHT / 2), Graph.VERTEX_WIDTH, Graph.VERTEX_HEIGHT);
                            Graph.this.revalidate();
                            Graph.this.repaint();
                            break;
                        }
                    }
                }


            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //fuck this, do it in graph
        for (Edge edge : this.edges) {
            g.setColor(Color.WHITE);
            int[] coords = edge.getLineCoords();
            System.out.println(Arrays.toString(coords));
            g.drawLine(coords[0], coords[1], coords[2], coords[3]);
            g.setColor(Color.WHITE);
            g.drawString(Integer.toString(edge.weight), coords[0] + (coords[2] - coords[0]) / 2, coords[1] + (coords[3] - coords[1]) / 2);
        }
    }
}
