package org.logistics.model;

import java.util.*;

public class Pathfinding {
    private HashMap<Vertex, LinkedList<Edge>> adjacencyList;

    //TODO: Provide better description for the Constructor JavaDoc
    /**
     * Constructor responsible for setting the graph environment
     * @param graph (Graph) | Graph Environment
     */
    public Pathfinding(Graph graph) {
        this.adjacencyList = graph.getAdjacencyList();
    }

    /**
     * Method Responsible for finding the shortest path between current vehicle position and package destination
     * @param vehicle (Vehicle) | used to find shortest path between current vehicle location and packages it contains destinations
     */
    public void find_shortest_path(Vehicle vehicle) {
        if (!vehicle.get_deliveryPackages().isEmpty()) {

            Vertex start_vertex = vehicle.getCurrent_vertex();
            start_vertex.set_distance(0);

            HashMap<Vertex, Vertex> predecessor = new HashMap<>();
            PriorityQueue<Vertex> unvisited = new PriorityQueue<>(Comparator.comparingInt(Vertex::get_distance));
            Queue<Vertex> visited = new LinkedList<>();

            unvisited.add(start_vertex);
            predecessor.put(start_vertex, null);

            while (!unvisited.isEmpty()) {
                Vertex current = unvisited.poll();
                if (!visited.contains(current)) {
                    for (Edge edge : adjacencyList.get(current)) {
                        int totalDistance = current.get_distance() + edge.getDistance_weight();

                        if (totalDistance < edge.getConnecting_node().get_distance()) {
                            edge.getConnecting_node().set_distance(totalDistance);
                            unvisited.remove(edge.getConnecting_node());
                            unvisited.add(edge.getConnecting_node());

                            predecessor.remove(edge.getConnecting_node(), current);
                            predecessor.put(edge.getConnecting_node(), current);
                        }


                    }
                    unvisited.remove(current);
                    visited.add(current);

                }
            }
            System.out.println("Visited");
            for (Vertex vertex : visited) {
                System.out.println("Vertex: " + vertex.get_node_value());
                System.out.println("Distance: " + vertex.get_distance());
                System.out.println("-----");
            }


            System.out.println("------");
            System.out.println("Predecessor");

            for (Vertex vertex : predecessor.keySet()) {
                System.out.println("Vertex: " + vertex);
                System.out.println("predecessor: " + predecessor.get(vertex));
                System.out.println("-------");
            }
        } else {
            System.out.println("Vehicle does not contain any package");
        }
    }
}
