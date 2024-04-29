package visualizer;

import javax.swing.*;

public class EdgeManufacturer {
    private Vertex v1;
    private Vertex v2;
    private int weight;

    private EdgeManufacturer setV1(Vertex v1) {
        this.v1 = v1;
        return this;
    }

    private EdgeManufacturer setV2(Vertex v2) {
        this.v2 = v2;
        return this;
    }

    public EdgeManufacturer setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public void clear() {
        v1 = null;
        v2 = null;
        weight = 0;
    }

    Edge submit(Vertex vertex) {
        if (v1 == null) {
            v1 = vertex;
            System.out.println("First vertex submitted");
        } else {
            v2 = vertex;
            System.out.println("Second vertex submitted");
            while (true) {
                String input = JOptionPane.showInputDialog(null, "Enter Vertex ID", "Vertex", JOptionPane.PLAIN_MESSAGE);
                //cancel was pressed
                if (input == null) {
                    this.clear();
                    break;
                }
                if (input.matches("\\d+")) {
                    System.out.println("we get signal");
                    return new Edge(v1, v2, weight);
                }
            }
        }
        return null;
    }
}