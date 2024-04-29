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
    private java.util.List<JPanel> vertices = new ArrayList<>();
    List<Edge> edges = new ArrayList<Edge>();
    EdgeManufacturer edgeFabricator = new EdgeManufacturer();

    public Graph() {
        super();
        this.setName("Graph");
        setLayout(null);
        this.setBackground(Color.BLACK);
//        createDebugVertices();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (Graph.this.state != ToolState.VERTEX_CREATION) {
                    return;
                }
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
        }
//        drawDebugVertices();
    }

//    private void createDebugVertices() {
//        vertices = new JPanel[4];
//        for (int i = 0; i < 4; i++) {
//            vertices[i] = new Vertex(String.valueOf(i));
//            this.add(vertices[i]);
//        }
//    }

    enum ToolState {
        NONE("None"), EDGE_CREATION("Add an Edge"), EDGE_DELETION("Remove an Edge"), VERTEX_CREATION("Add a Vertex"), VERTEX_DELETION("Remove a Vertex");

        final String NAME;

        ToolState(String stateName) {
            this.NAME = stateName;
        }
    }

//    private void drawDebugVertices() {
//        int width = Graph.VERTEX_WIDTH;
//        int height = Graph.VERTEX_HEIGHT;
//        vertices[0].setBounds(0, 0, width, height);
//        vertices[1].setBounds(this.getWidth() - width, 0, width, height);
//        vertices[2].setBounds(0, this.getHeight() - height, width, height);
//        vertices[3].setBounds(this.getWidth() - width, this.getHeight() - height, width, height);
//    }
}
