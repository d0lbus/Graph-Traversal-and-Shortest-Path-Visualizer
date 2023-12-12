package finlab;

import java.util.List;
import java.util.ArrayList;

public class Vertex {
    private final String vertexId;
    private final List<Edge> connectedEdges;
    private int xPos;
    private int yPos;

    public Vertex(String id) {
        this.vertexId = id;
        this.connectedEdges = new ArrayList<>();
    }

    public String getVertexId() {
        return vertexId;
    }

    public List<Edge> getConnectedEdges() {
        return connectedEdges;
    }

    public void addConnectedEdge(Edge edge) {
        connectedEdges.add(edge);
    }

    public int getXPos() {
        return xPos;
    }

    public void setXPos(int x) {
        this.xPos = x;
    }
    public int getYPos() {
        return yPos;
    }

    public void setYPos(int y) {
        this.yPos = y;
    }

    @Override
    public String toString() {
        return vertexId;
    }
}
