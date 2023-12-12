package finlab;

import java.util.*;

public class GraphTraversal {
    private final List<Vertex> vertexList;
    private StringBuilder shortestPathVertices;

    public GraphTraversal(Graph graph) {
        vertexList = graph.getVertices();
    }

    public Vertex getVertex(String id) {
        return vertexList.stream()
                .filter(vertex -> vertex.getVertexId().equals(id))
                .findFirst()
                .orElse(null);
    }

}
