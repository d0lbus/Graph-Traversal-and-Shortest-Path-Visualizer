/**
 * @author MANUEL ALLAN CIAN TALOSIG
 */

package finlab;

import java.util.*;

/**
 * The Graph class represents a graph with vertices and edges.
 */
public class Graph {
    private final List<Vertex> vertices; // The list of vertices in the graph.
    private final Map<String, Vertex> vertexMap; // A map to quickly access vertices by their IDs.

    /**
     * Constructs a new Graph object.
     */
    public Graph() {
        this.vertices = new ArrayList<>();
        this.vertexMap = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph.
     *
     * @param vertex The vertex to be added.
     */
    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
        vertexMap.put(vertex.getVertexId(), vertex);
    }

    /**
     * Adds an edge between source and destination vertices in the graph.
     *
     * @param source      The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     * @param edge        The edge to be added.
     */
    public void addEdge(Vertex source, Vertex destination, Edge edge) {
        source.addConnectedEdge(edge);
        destination.addConnectedEdge(edge);
    }

    /**
     * Gets the list of vertices in the graph.
     *
     * @return The list of vertices in the graph.
     */
    public List<Vertex> getVertices() {
        return vertices;
    }

    /**
     * Gets a vertex by its ID.
     *
     * @param id The ID of the vertex to be retrieved.
     * @return The vertex with the specified ID, or null if not found.
     */
    public Vertex getVertexById(String id) {
        return vertexMap.get(id);
    }
} // end of Graph class
