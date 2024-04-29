package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.setName("Graph-Algorithms Visualizer");
        this.setTitle(this.getName());

        Graph graph = new Graph();
        this.add(graph, BorderLayout.CENTER);

        JLabel toolStateLabel = new JLabel(graph.state.NAME);
        toolStateLabel.setName("Mode");
//        toolStateLabel.setForeground(Color.WHITE);
        this.add(toolStateLabel, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.setName(newMenuItem.getText());
        newMenuItem.addActionListener((ActionEvent e) -> {
            graph.edges.clear();
            graph.vertices.clear();
            graph.removeAll();
            graph.repaint();
        });
        fileMenu.add(newMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setName(exitMenuItem.getText());
        exitMenuItem.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);



        JMenu modeMenu = new JMenu("Mode");

        JMenuItem vertexMenuItem = new JMenuItem("Add a Vertex");
        vertexMenuItem.setName(vertexMenuItem.getText());

        JMenuItem edgeMenuItem = new JMenuItem("Add an Edge");
        edgeMenuItem.setName(edgeMenuItem.getText());

        JMenuItem removeVertexMenuItem = new JMenuItem("Remove a Vertex");
        removeVertexMenuItem.setName(removeVertexMenuItem.getText());

        JMenuItem removeEdgeMenuItem = new JMenuItem("Remove an Edge");
        removeEdgeMenuItem.setName(removeEdgeMenuItem.getText());

        JMenuItem noneMenuItem = new JMenuItem("None");
        noneMenuItem.setName(noneMenuItem.getText());

        vertexMenuItem.addActionListener(e -> {
            graph.state = Graph.ToolState.VERTEX_CREATION;
            toolStateLabel.setText(graph.state.NAME);
        });

        edgeMenuItem.addActionListener(e -> {
            graph.state = Graph.ToolState.EDGE_CREATION;
            toolStateLabel.setText(graph.state.NAME);
        });

        removeVertexMenuItem.addActionListener(e -> {
            graph.state = Graph.ToolState.VERTEX_DELETION;
        });

        removeEdgeMenuItem.addActionListener(e -> {
            graph.state = Graph.ToolState.EDGE_DELETION;
            toolStateLabel.setText(graph.state.NAME);
        });

        noneMenuItem.addActionListener(e -> {
            graph.state = Graph.ToolState.NONE;
            toolStateLabel.setText(graph.state.NAME);
        });

        menuBar.add(modeMenu);
        modeMenu.add(vertexMenuItem);
        modeMenu.add(edgeMenuItem);
        modeMenu.add(noneMenuItem);
        this.setJMenuBar(menuBar);


        setSize(800, 600);
        setResizable(false);
        setVisible(true);
    }


}

