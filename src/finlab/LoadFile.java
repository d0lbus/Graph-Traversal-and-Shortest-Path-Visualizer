/**
 * @author Chloe Lee San Miguel
 * @author Freskkie Earl Encarnacion
 */

package finlab;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.*;
import java.util.*;

public class LoadFile {
    static Graph graph = new Graph();

    public Graph loadFile() throws FileNotFoundException {
        try {
            File file = new File("testGraph.txt");
            Scanner scanner = readFile(file);
            Random random = new Random();

            while (scanner.hasNext()) {
                String sourceVertex = scanner.next();
                String destinationVertex = scanner.next();
                double weight = scanner.nextDouble();

                Vertex startVertex = position(sourceVertex, random.nextInt(400) + 50,
                        random.nextInt(300) + 50);
                Vertex endVertex = position(destinationVertex, random.nextInt(400) + 50,
                        random.nextInt(300) + 50);

                Edge edge = new Edge(startVertex, endVertex, weight);
                graph.addEdge(startVertex, endVertex, edge);

                Edge reverseEdge = new Edge(endVertex, startVertex, weight);
                graph.addEdge(endVertex, startVertex, reverseEdge);
            }

            System.out.println("=========================================================");
            System.out.println("Graph loaded successfully!");
            illustrateGraph(graph);
            displayGraph(graph);

        } catch (Exception e) {
            System.out.println("Error! File not found.");
        }
        return graph;
    } // end of loadFile method

    private static Scanner readFile(File file) {
        try {
            return new Scanner(file);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    } // end of Scanner method


    private static Vertex position(String nodeId, int x, int y) {
        Vertex vertex = graph.getVertexById(nodeId);
        if (vertex == null) {
            vertex = new Vertex(nodeId);
            graph.addVertex(vertex);
        }
        vertex.setXPos(x);
        vertex.setYPos(y);
        return vertex;
    } // end of Vertex method

    public static void displayGraph(finlab.Graph graph) {
        if (graph != null && !graph.getVertices().isEmpty()) {
            JFrame frame = new JFrame("Graph Display");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            LoadFile.GraphPanel graphPanel = new GraphPanel(graph);
            frame.add(graphPanel);

            frame.setVisible(true);
            frame.setLocationRelativeTo(null);

        } else {
            System.out.println("No graph data to visualize. Load a graph first.");
        }
    } // end of displayGraph method

    public static class GraphPanel extends JPanel {
        private final finlab.Graph graph;

        public GraphPanel(finlab.Graph graph) {
            this.graph = graph;
        }

        private void adjustNodePositions() {
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int centerX = panelWidth / 2;
            int centerY = panelHeight / 2;

            int spacingX = panelWidth / 4;
            int spacingY = panelHeight / 5;

            graph.getVertexById("A").setXPos(centerX - spacingX);
            graph.getVertexById("A").setYPos(centerY - spacingY);

            graph.getVertexById("B").setXPos(centerX + spacingX);
            graph.getVertexById("B").setYPos(centerY - spacingY);

            graph.getVertexById("C").setXPos(centerX - spacingX);
            graph.getVertexById("C").setYPos(centerY + spacingY);

            graph.getVertexById("D").setXPos(centerX + spacingX);
            graph.getVertexById("D").setYPos(centerY + spacingY);

            graph.getVertexById("E").setXPos(centerX);
            graph.getVertexById("E").setYPos(centerY + 2 * spacingY);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            adjustNodePositions();

            for (Vertex node : graph.getVertices()) {
                for (finlab.Edge edge : node.getConnectedEdges()) {
                    Vertex adjacentNode = edge.getDestination();

                    // Draw line
                    g.drawLine(node.getXPos(), node.getYPos(), adjacentNode.getXPos(), adjacentNode.getYPos());

                    // Draw edge weight
                    int weightX = (node.getXPos() + adjacentNode.getXPos()) / 2;
                    int weightY = (node.getYPos() + adjacentNode.getYPos()) / 2;
                    g.setColor(Color.BLUE);
                    g.drawString(String.valueOf(edge.getWeight()), weightX, weightY);
                    g.setColor(Color.BLACK);
                }
            }

            g.setColor(Color.BLACK);

            for (Vertex node : graph.getVertices()) {
                g.fillOval(node.getXPos() - 15, node.getYPos() - 15, 30, 30);
                g.setColor(Color.WHITE);
                g.drawString(node.getVertexId(), node.getXPos() - 5, node.getYPos() + 5);
                g.setColor(Color.BLACK);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(getWidth(), getHeight());
        }
    }

    public static void illustrateGraph(finlab.Graph graph) {
        System.out.println("Graph Illustration:");
        for (Vertex node : graph.getVertices()) {
            for (finlab.Edge edge : node.getConnectedEdges()) {
                Vertex adjacentNode = edge.getDestination();
                if (!node.getVertexId().equals(adjacentNode.getVertexId())) {
                    System.out.print(node.getVertexId() + " -- " + adjacentNode.getVertexId() +
                            ", Weight: " + edge.getWeight() + "     ");
                }
            }
            System.out.println();
        }
    } // end of illustrateGraph method
} // end of LoadFile class

