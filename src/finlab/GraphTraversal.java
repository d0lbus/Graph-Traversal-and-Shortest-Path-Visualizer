package finlab;

import java.util.*;

public class GraphTraversal {
    private final List<Vertex> vertexList;
    private StringBuilder shortestPathVertices;

    public GraphTraversal(Graph graph) {
        vertexList = graph.getVertices();
    }

    public String depthFirstTraversal(Vertex id) {
        StringBuilder result = new StringBuilder();
        Vertex vertex = getVertex(String.valueOf(id));
        Set<Vertex> visited = new HashSet<>();
        Stack<Vertex> stack = new Stack<>();
        stack.push(vertex);

        System.out.println("===================================");
        System.out.println("DEPTH FIRST TRAVERSAL");

        while (!stack.isEmpty()) {
            vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                result.append(vertex).append(" ");
            }

            for (Edge edge : vertex.getConnectedEdges()) {
                if (!visited.contains(edge.getDestination())) {
                    stack.push(edge.getDestination());
                }
            }
        }
        return result.toString();
    }


    public Vertex getVertex(String id) {
        return vertexList.stream()
                .filter(vertex -> vertex.getVertexId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public String breadthFirstTraversal (Vertex startVertex) {
        StringBuilder result = new StringBuilder ();
        Set<Vertex> visited = new HashSet<>();
        LinkedList<Vertex> queue = new LinkedList<>();
        queue.add(startVertex);
        visited.add(startVertex);

        System.out.println("=========================================================");
        System.out.println("BREADTH FIRST TRAVERSAL");

        while (!queue.isEmpty()) {
            startVertex = queue.poll();
            result.append(startVertex).append(" ");
    
            for (Edge edge : startVertex.getConnectedEdges()) {
                if (!visited.contains(edge.getDestination())) {
                    visited.add(edge.getDestination());
                    queue.add(edge.getDestination());
                }
            }
        }
        return result.toString();
    } 
} // end of breadthFirstTraversal



