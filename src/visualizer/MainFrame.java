package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
    Graph graph = new Graph();
    JLabel displayLabel;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.setName("Graph-Algorithms Visualizer");
        this.setTitle(this.getName());


        this.add(graph, BorderLayout.CENTER);

        //statusBar
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setName("statusPanel");
        this.add(statusPanel, BorderLayout.SOUTH);

        //toolState
        JLabel toolStateLabel = new JLabel(graph.state.NAME);
        toolStateLabel.setName("Mode");
//        toolStateLabel.setForeground(Color.WHITE);
        statusPanel.add(toolStateLabel, BorderLayout.WEST);

        //displayLabel
        displayLabel = new JLabel();
        displayLabel.setName("Display");
        displayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusPanel.add(displayLabel, BorderLayout.CENTER);

// MenuBar
        JMenuBar menuBar = new JMenuBar();
//File
        {
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
        }
//Mode
        {
            JMenu modeMenu = new JMenu("Mode");

            for (ToolState value : ToolState.values()) {
                JMenuItem toolMenuItem = new JMenuItem(value.NAME);
                toolMenuItem.setName(toolMenuItem.getText());
                toolMenuItem.addActionListener(e -> {
                    graph.state = value;
                    toolStateLabel.setText(value.NAME);

                    graph.algorithm = null;
                    displayLabel.setText("");
                });
                modeMenu.add(toolMenuItem);

            }


            /*JMenuItem vertexMenuItem = new JMenuItem("Add a Vertex");
            vertexMenuItem.setName(vertexMenuItem.getText());
            vertexMenuItem.addActionListener(e -> {
                graph.state = ToolState.VERTEX_CREATION;
                toolStateLabel.setText(graph.state.NAME);
                graph.algorithm = null;
                displayLabel.setText("");
            });
            modeMenu.add(vertexMenuItem);

            JMenuItem edgeMenuItem = new JMenuItem("Add an Edge");
            edgeMenuItem.setName(edgeMenuItem.getText());
            edgeMenuItem.addActionListener(e -> {
                graph.state = ToolState.EDGE_CREATION;
                toolStateLabel.setText(graph.state.NAME);
            });
            modeMenu.add(edgeMenuItem);

            JMenuItem removeVertexMenuItem = new JMenuItem("Remove a Vertex");
            removeVertexMenuItem.setName(removeVertexMenuItem.getText());
            removeVertexMenuItem.addActionListener(e -> {
                graph.state = ToolState.VERTEX_DELETION;
            });
            modeMenu.add(removeVertexMenuItem);

            JMenuItem removeEdgeMenuItem = new JMenuItem("Remove an Edge");
            removeEdgeMenuItem.setName(removeEdgeMenuItem.getText());
            removeEdgeMenuItem.addActionListener(e -> {
                graph.state = ToolState.EDGE_DELETION;
                toolStateLabel.setText(graph.state.NAME);
            });
            modeMenu.add(removeEdgeMenuItem);

            JMenuItem noneMenuItem = new JMenuItem("None");
            noneMenuItem.setName(noneMenuItem.getText());
            noneMenuItem.addActionListener(e -> {
                graph.state = ToolState.NONE;
                toolStateLabel.setText(graph.state.NAME);
                graph.algorithm = null;
                displayLabel.setText("");
            });
            modeMenu.add(noneMenuItem);*/

            menuBar.add(modeMenu);
        }
//Algorithms
        {
            JMenu algorithmMenu = new JMenu("Algorithms");

            for (Algorithm value : Algorithm.values()) {
                JMenuItem algorithmMenuItem = new JMenuItem(value.getAlgorithmName());
                algorithmMenuItem.setName(value.name());
                algorithmMenuItem.addActionListener((ActionEvent e) -> {
                    displayLabel.setText("Please choose a starting vertex");
                    graph.state = ToolState.NONE;
                    toolStateLabel.setText(graph.state.NAME);
                    graph.algorithm = value;
                });
                algorithmMenu.add(algorithmMenuItem);
            }
            menuBar.add(algorithmMenu);


            //freakin ai code completion just stealing other projects code lol
            /*JMenuItem dijkstraMenuItem = new JMenuItem("Dijkstra");
            dijkstraMenuItem.setName(dijkstraMenuItem.getText());
            dijkstraMenuItem.addActionListener((ActionEvent e) -> {
            });
            algorithmMenu.add(dijkstraMenuItem);

            JMenuItem primMenuItem = new JMenuItem("Prim's");
            primMenuItem.setName(primMenuItem.getText());
            primMenuItem.addActionListener((ActionEvent e) -> {
            });
            algorithmMenu.add(primMenuItem);

            JMenuItem kruskalMenuItem = new JMenuItem("Kruskal's");
            kruskalMenuItem.setName(kruskalMenuItem.getText());
            kruskalMenuItem.addActionListener((ActionEvent e) -> {
            });
            algorithmMenu.add(kruskalMenuItem);*/

        }

        this.setJMenuBar(menuBar);


        setSize(800, 600);
        setResizable(false);
        setVisible(true);
    }


}

