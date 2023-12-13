/**
 * @author Chloe Lee San Miguel
 * @author Nichole Jhoy Escano
 * @author Freskkie Earl Encarnacion
 * @author Jan Dolby Aquino
 */

package finlab;

import java.text.DecimalFormat;
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

    public String breadthFirstTraversal(Vertex startVertex) {
        StringBuilder result = new StringBuilder();
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

    public String shortestPath(String sourceId, String destinationId) {
        Vertex root = getVertex(sourceId);
        Vertex destination = getVertex(destinationId);

        Map<Vertex, Double> distanceMap = new HashMap<>();
        Map<Vertex, Vertex> previousVertexMap = new HashMap<>();
        Set<Vertex> visited = new HashSet<>();
        LinkedList<Vertex> queue = new LinkedList<>();

        queue.add(root);
        distanceMap.put(root, 0.0);

        while (!queue.isEmpty()) {
            Vertex currentVertex = queue.poll();
            visited.add(currentVertex);

            for (Edge edge : currentVertex.getConnectedEdges()) {
                Vertex endVertex = edge.getDestination();
                if (!visited.contains(endVertex)) {
                    double newDistance = distanceMap.get(currentVertex) + edge.getWeight();

                    if (newDistance < distanceMap.getOrDefault(endVertex, Double.MAX_VALUE)) {
                        distanceMap.put(endVertex, newDistance);
                        previousVertexMap.put(endVertex, currentVertex);
                        queue.add(endVertex);
                    }
                }
            }
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double shortestPath = Double.parseDouble(decimalFormat.format(distanceMap.getOrDefault(destination, Double.MAX_VALUE)));

        shortestPathVertices = new StringBuilder();
        if (shortestPath != Double.MAX_VALUE) {
            constructShortestPath(previousVertexMap, destination);
            return "Shortest Path from " + sourceId + " to " + destinationId + ": " + shortestPath + "\nShortest Path Sequence: " + shortestPathVertices;
        }
        return "There is no path from " + sourceId + " to " + destinationId;
    }


    public void constructShortestPath(Map<Vertex, Vertex> previousVertexMap, Vertex destination) {
        Vertex current = destination;

        while (current != null) {
            shortestPathVertices.insert(0, current + " ");
            current = previousVertexMap.get(current);
        }
    }


    public Vertex getVertex(int id) {
        return null;
    }

    public String getShortestPathVertices() {
        return shortestPathVertices.toString();
    }

} // end of GraphTraversal class



