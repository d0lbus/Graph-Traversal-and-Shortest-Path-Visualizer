/**
 * @author Chloe Lee San Miguel
 */

package finlab;

import java.util.List;
import java.util.ArrayList;

/**
 * The Vertex class represents a vertex in a graph.
 */
public class Vertex {
    private final String vertexId; // The unique identifier for the vertex.
    private final List<Edge> connectedEdges; // The list of edges connected to this vertex.
    private int xPos; // The x-coordinate position of the vertex.
    private int yPos; // The y-coordinate position of the vertex.

    /**
     * Constructs a new Vertex object with the specified ID.
     *
     * @param id The unique identifier for the vertex.
     */
    public Vertex(String id) {
        this.vertexId = id;
        this.connectedEdges = new ArrayList<>();
    }

    /**
     * Gets the ID of the vertex.
     *
     * @return The ID of the vertex.
     */
    public String getVertexId() {
        return vertexId;
    }

    /**
     * Gets the list of edges connected to this vertex.
     *
     * @return The list of edges connected to this vertex.
     */
    public List<Edge> getConnectedEdges() {
        return connectedEdges;
    }

    /**
     * Adds an edge to the list of connected edges for this vertex.
     *
     * @param edge The edge to be added.
     */
    public void addConnectedEdge(Edge edge) {
        connectedEdges.add(edge);
    }

    /**
     * Gets the x-coordinate position of the vertex.
     *
     * @return The x-coordinate position of the vertex.
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * Sets the x-coordinate position of the vertex.
     *
     * @param x The x-coordinate position to be set.
     */
    public void setXPos(int x) {
        this.xPos = x;
    }

    /**
     * Gets the y-coordinate position of the vertex.
     *
     * @return The y-coordinate position of the vertex.
     */
    public int getYPos() {
        return yPos;
    }

    /**
     * Sets the y-coordinate position of the vertex.
     *
     * @param y The y-coordinate position to be set.
     */
    public void setYPos(int y) {
        this.yPos = y;
    }

    /**
     * Returns a string representation of the vertex (its ID).
     *
     * @return A string representation of the vertex.
     */
    @Override
    public String toString() {
        return vertexId;
    }
} // end of Vertex class
