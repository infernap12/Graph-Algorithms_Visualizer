package visualizer;

import javax.swing.*;
import java.awt.*;

public class Graph extends JPanel {
    private JPanel[] vertices;

    public Graph() {
        super();
        this.setName("Graph");
        this.setBackground(Color.BLACK);
        vertices = new JPanel[4];
        for (int i = 0; i < 4; i++) {
            vertices[i] = new Vertex(i);
            this.add(vertices[i]);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = 50;
        int height = 50;
        vertices[0].setBounds(0, 0, width, height);
        vertices[1].setBounds(this.getWidth() - width, 0, width, height);
        vertices[2].setBounds(0, this.getHeight() - height, width, height);
        vertices[3].setBounds(this.getWidth() - width, this.getHeight() - height, width, height);
    }
}
