package org.logistics.model;

import java.util.*;

public class bellman_ford_customerLocation {
    private Graph graph;
    private HashMap<Vertex<String>, LinkedList<Edge>> adjacencyList;

    /**
     * Constructor
     * @param graph - Graph Environment
     */
    public bellman_ford_customerLocation(Graph graph) {
        this.graph = graph;
        this.adjacencyList = graph.getAdjacencyList();
    }

    /**
     * Method Responsible for updating the adjacencyList
     */
    public void update_adjacencyList() {
        this.adjacencyList = graph.getAdjacencyList();
    }

    /**
     * Method Responsible for finding the shortest path to the highest priority package in the vehicle
     * @param vehicle - Vehicle to find shortest path for
     */
    public void find_shortest_customer(Vehicle vehicle) {
        HashMap<Vertex<String>, Vertex<String>> predecessor = new HashMap<>();
        PriorityQueue<Vertex<String>> unvisited = new PriorityQueue<>(Comparator.comparingInt(Vertex::getDistance));
        Queue<Vertex<String>> visited = new LinkedList<>();

        for (Vertex<String> vertex : adjacencyList.keySet()) {
            vertex.setDistance(Integer.MAX_VALUE);
        }

        Vertex<String> start_vertex = vehicle.getCurrent_location();
        start_vertex.setDistance(0);

        unvisited.add(start_vertex);
        predecessor.put(start_vertex, null);

        Package package_package = new Package(null, null, -1);

        if (vehicle.get_deliveryPackages().size() == 2) {
            for (Package package_package_temp : vehicle.get_deliveryPackages()) {
                if (package_package_temp.getPriority() > package_package.getPriority()) {
                    package_package = package_package_temp;
                }
            }
        } else

        if (vehicle.get_deliveryPackages().size() == 1) {
            package_package = vehicle.get_deliveryPackages().peek();
        }

    }
}
