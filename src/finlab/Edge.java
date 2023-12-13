/**
 * @author ARVIN MALALUAN
 */

package finlab;

/**
 * The Edge class represents a weighted edge in a graph, connecting a source
 * vertex to a destination vertex with a specified weight.
 */
public class Edge {

    private Vertex source; // The source vertex of the edge.
    private Vertex destination; // The destination vertex of the edge.
    private double weight; // The weight associated with the edge.

    /**
     * Constructs a new Edge object with the specified source, destination, and weight.
     *
     * @param source      The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     * @param weight      The weight associated with the edge.
     */
    public Edge(Vertex source, Vertex destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    /**
     * Gets the source vertex of the edge.
     *
     * @return The source vertex of the edge.
     */
    public Vertex getSource() {
        return source;
    }

    /**
     * Gets the destination vertex of the edge.
     *
     * @return The destination vertex of the edge.
     */
    public Vertex getDestination() {
        return destination;
    }

    /**
     * Gets the weight associated with the edge.
     *
     * @return The weight associated with the edge.
     */
    public double getWeight() {
        return weight;
    }
} // end of Edge class