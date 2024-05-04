package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
    Graph graph = new Graph();
    JLabel displayLabel;
    JLabel toolStateLabel;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.setName("Graph-Algorithms Visualizer");
        this.setTitle(this.getName());

        this.add(graph, BorderLayout.CENTER);

        //statusBar
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setName("statusPanel");

        //toolState
        JLabel toolStateLabel = new JLabel(graph.state.NAME);
        toolStateLabel.setName("Mode");
        statusPanel.add(toolStateLabel, BorderLayout.WEST);

        //displayLabel
        displayLabel = new JLabel();
        displayLabel.setName("Display");
        displayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusPanel.add(displayLabel, BorderLayout.CENTER);

        this.add(statusPanel, BorderLayout.SOUTH);

        // MenuBar
        JMenuBar menuBar = new JMenuBar();

        //File
        JMenu fileMenu = buildFileMenu();
        menuBar.add(fileMenu);


        //Mode
        JMenu modeMenu = buildModeMenu();
        menuBar.add(modeMenu);

        //Algorithms
        JMenu algorithmMenu = buildAlgorithmMenu();
        menuBar.add(algorithmMenu);


        this.setJMenuBar(menuBar);


        setSize(800, 600);
        setResizable(false);
        setVisible(true);
    }

    private JMenu buildFileMenu() {
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
        exitMenuItem.addActionListener((ActionEvent e) -> System.exit(0));
        fileMenu.add(exitMenuItem);
        return fileMenu;
    }

    private JMenu buildModeMenu() {
        JMenu modeMenu = new JMenu("Mode");

        for (ToolState value : ToolState.values()) {
            JMenuItem toolMenuItem = new JMenuItem(value.NAME);
            toolMenuItem.setName(toolMenuItem.getText());
            toolMenuItem.addActionListener(e -> {
                graph.state = value;
                toolStateLabel.setText(value.NAME);
                graph.edgeFabricator.clear();

                graph.algorithm = null;
                displayLabel.setText("");
            });
            modeMenu.add(toolMenuItem);
        }
        return modeMenu;
    }

    private JMenu buildAlgorithmMenu() {
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
        return algorithmMenu;
    }


}

