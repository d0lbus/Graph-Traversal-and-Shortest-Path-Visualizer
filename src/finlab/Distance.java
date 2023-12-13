/**
 * @author MA. LOURDES SHAINE RAZO
 */

package finlab;

/**
 * The Distance class represents the distance value associated with a specific
 * graph node (Vertex) in a graph.
 */

public class Distance {

    private final Vertex graphNode; // The graph node (Vertex) for which the distance is associated.
    private final double distanceValue; // The distance value associated with the graph node.

    /**
     * Constructs a new Distance object with the specified graph node and distance value.
     *
     * @param node     The graph node for which the distance is associated.
     * @param distance The distance value associated with the graph node.
     */
    public Distance(Vertex node, double distance) {
        this.graphNode = node;
        this.distanceValue = distance;
    }

    /**
     * Gets the graph node associated with this distance.
     *
     * @return The graph node associated with this distance.
     */
    public Vertex getGraphNode() {
        return graphNode;
    }

    /**
     * Gets the distance value associated with this distance.
     *
     * @return The distance value associated with this distance.
     */
    public double getDistanceValue() {
        return distanceValue;
    }
} // end of Distance class
