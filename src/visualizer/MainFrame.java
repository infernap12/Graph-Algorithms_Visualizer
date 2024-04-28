package visualizer;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.setName("Graph-Algorithms Visualizer");

        JPanel graph = new Graph();
        this.add(graph, BorderLayout.CENTER);

        setSize(800, 600);
        setResizable(false);
        setVisible(true);
    }
}

