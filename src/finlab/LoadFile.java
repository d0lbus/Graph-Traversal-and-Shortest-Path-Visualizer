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

/**
 * The LoadFile class is responsible for loading graph data from a file,
 * creating the graph, and displaying it.
 */
public class LoadFile {
    static Graph graph = new Graph(); // The static graph to store the loaded data.

    /**
     * Loads graph data from a file and displays the graph.
     *
     * @return The graph loaded from the file.
     * @throws FileNotFoundException If the specified file is not found.
     */
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

    /**
     * Reads a file and returns a Scanner for the file.
     *
     * @param file The file to be read.
     * @return A Scanner for the file.
     */
    private static Scanner readFile(File file) {
        try {
            return new Scanner(file);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    } // end of Scanner method

    /**
     * Sets the position of a vertex with the given ID or creates a new vertex if not found.
     *
     * @param nodeId The ID of the vertex.
     * @param x      The x-coordinate of the vertex.
     * @param y      The y-coordinate of the vertex.
     * @return The vertex with the specified ID.
     */
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

    /**
     * Displays the graph using a graphical user interface.
     *
     * @param graph The graph to be displayed.
     */
    public static void displayGraph(finlab.Graph graph) {
        if (graph != null && !graph.getVertices().isEmpty()) {
            JFrame frame = new JFrame("Graph Illustration");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 300);
            LoadFile.GraphPanel graphPanel = new GraphPanel(graph);
            frame.add(graphPanel);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);

        } else {
            System.out.println("No graph data to visualize. Load a graph first.");
        }
    } // end of displayGraph method

    /**
     * The GraphPanel class is a JPanel for displaying the graph.
     */
    public static class GraphPanel extends JPanel {
        private final finlab.Graph graph;

        /**
         * Constructs a GraphPanel with the given graph.
         *
         * @param graph The graph to be displayed.
         */
        public GraphPanel(finlab.Graph graph) {
            this.graph = graph;
        }

        /**
         * Adjusts the positions of the nodes in the graph.
         */
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

        /**
         * Paints the components of the graph on the panel.
         *
         * @param g The Graphics object.
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            adjustNodePositions();

            for (Vertex node : graph.getVertices()) {
                for (finlab.Edge edge : node.getConnectedEdges()) {
                    Vertex adjacentNode = edge.getDestination();
                    g.drawLine(node.getXPos(), node.getYPos(), adjacentNode.getXPos(), adjacentNode.getYPos());
                    int weightX = (node.getXPos() + adjacentNode.getXPos()) / 2;
                    int weightY = (node.getYPos() + adjacentNode.getYPos()) / 2;
                    g.setColor(Color.RED);
                    g.drawString(String.valueOf(edge.getWeight()), weightX, weightY);
                    g.setColor(Color.BLACK);
                }
            }

            g.setColor(Color.BLACK);

            for (Vertex node : graph.getVertices()) {
                g.fillOval(node.getXPos() - 15, node.getYPos() - 15, 30, 30);
                g.setColor(Color.YELLOW);
                g.drawString(node.getVertexId(), node.getXPos() - 5, node.getYPos() + 5);
                g.setColor(Color.BLACK);
            }
        }

        /**
         * Gets the preferred size of the panel.
         *
         * @return The preferred size.
         */
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(getWidth(), getHeight());
        }
    }

    /**
     * Prints the illustration of the graph to the console.
     *
     * @param graph The graph to be illustrated.
     */
    public static void illustrateGraph(finlab.Graph graph) {
        System.out.println("=========================================================");
        System.out.println("Graph Illustration:");
        for (Vertex node : graph.getVertices()) {
            for (finlab.Edge edge : node.getConnectedEdges()) {
                Vertex adjacentNode = edge.getDestination();
                if (!node.getVertexId().equals(adjacentNode.getVertexId())) {
                    System.out.print(node.getVertexId() + " to " + adjacentNode.getVertexId() +
                            ", Weight: " + edge.getWeight() + "     ");
                }
            }
            System.out.println();
        }
    } // end of illustrateGraph method
} // end of LoadFile class

