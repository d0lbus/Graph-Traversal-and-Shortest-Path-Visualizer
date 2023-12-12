package finlab;

public class Distance {
    private final Vertex graphNode;
    private final double distanceValue;

    public Distance(Vertex node, double distance) {
        this.graphNode = node;
        this.distanceValue = distance;
    }

    public Vertex getGraphNode() {
        return graphNode;
    }

    public double getDistanceValue() {
        return distanceValue;
    }
}
