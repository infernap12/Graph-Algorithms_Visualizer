package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Graph extends JPanel {
    private static final int VERTEX_HEIGHT = 50;
    private static final int VERTEX_WIDTH = 50;

    private JPanel[] vertices;

    public Graph() {
        super();
        this.setName("Graph");
        setLayout(null);
        this.setBackground(Color.BLACK);
//        createDebugVertices();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
                        vertex.setBounds(e.getX() - (Graph.VERTEX_WIDTH / 2),
                                e.getY() - (Graph.VERTEX_HEIGHT / 2),
                                Graph.VERTEX_WIDTH,
                                Graph.VERTEX_HEIGHT);
                        Graph.this.revalidate();
                        Graph.this.repaint();
                        break;
                    }
                }


            }
        });
    }

    private void createDebugVertices() {
        vertices = new JPanel[4];
        for (int i = 0; i < 4; i++) {
            vertices[i] = new Vertex(String.valueOf(i));
            this.add(vertices[i]);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        drawDebugVertices();
    }

    private void drawDebugVertices() {
        int width = Graph.VERTEX_WIDTH;
        int height = Graph.VERTEX_HEIGHT;
        vertices[0].setBounds(0, 0, width, height);
        vertices[1].setBounds(this.getWidth() - width, 0, width, height);
        vertices[2].setBounds(0, this.getHeight() - height, width, height);
        vertices[3].setBounds(this.getWidth() - width, this.getHeight() - height, width, height);
    }
}
