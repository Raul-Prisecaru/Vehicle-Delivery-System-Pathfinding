package org.logistics.model;

import java.util.*;

public class Pathfinding {
    private HashMap<Vertex, LinkedList<Edge>> adjacencyList;


    /**
     * Method responsible for finding the shortest distance between two vertexes
     * @param vehicle Vehicle that should find the shortest path
     */

    public Pathfinding(Graph graph) {
        this.adjacencyList = graph.getAdjacencyList();
    }
    public void find_shortest_path(Vehicle vehicle) {
        if (!vehicle.get_deliveryPackages().isEmpty()) {

            Vertex start_vertex = vehicle.getCurrent_vertex();
            start_vertex.set_distance(0);

            PriorityQueue<Vertex> unvisited = new PriorityQueue<>(Comparator.comparingInt(Vertex::get_distance));
            Queue<Vertex> visited = new LinkedList<>();

            unvisited.add(start_vertex);

            while (!unvisited.isEmpty()) {
                Vertex current = unvisited.poll();
                if (!visited.contains(current)) {
                    for (Edge edge : adjacencyList.get(current)) {
                        int totalDistance = current.get_distance() + edge.getDistance_weight();

                        if (totalDistance < edge.getConnecting_node().get_distance()) {
                            edge.getConnecting_node().set_distance(totalDistance);
                            unvisited.remove(edge.getConnecting_node());
                            unvisited.add(edge.getConnecting_node());
                        }


                    }
                    unvisited.remove(current);
                    visited.add(current);

                }
            }
        for (Vertex vertex : visited) {
            System.out.println("Vertex: " + vertex.get_node_value());
            System.out.println("Distance: " + vertex.get_distance());
            System.out.println("-----");
            }
        } else {
            System.out.println("Vehicle does not contain any package");
        }
    }
}
