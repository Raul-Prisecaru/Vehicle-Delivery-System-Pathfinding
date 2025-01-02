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
        int iteration = (graph.getAllDeliveryHub().size() + graph.getAllCustomerLocation().size()) - 1;


        for (Vertex<String> vertex : adjacencyList.keySet()) {
            vertex.setDistance(Integer.MAX_VALUE);
        }



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

        while(iteration != 0) {
            Vertex<String> start_vertex = vehicle.getCurrent_location();

            unvisited.add(start_vertex);
            predecessor.put(start_vertex, null);

            while (!unvisited.isEmpty()) {
                Vertex<String> current = unvisited.poll();

                if (!visited.contains(current) && current.getDistance() != Integer.MAX_VALUE) {
                    for (Edge edge : adjacencyList.get(current)) {
                        int totalDistance = current.getDistance() + edge.getTime_weight();

                        if (totalDistance < edge.getConnecting_node().getDistance()) {
                            edge.getConnecting_node().setDistance(totalDistance);
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
            iteration--;
            visited.clear();
            predecessor.clear();

        }

        Vertex<String> endVertex = package_package.getDestination();
        vehicle.addTravelDestination(endVertex);
        for (Vertex<String> vertex : predecessor.keySet()) {
            if (predecessor.get(endVertex) != null && predecessor.get(endVertex) != vehicle.getCurrent_location()) {
                vehicle.addTravelDestination(predecessor.get(endVertex));
                endVertex = predecessor.get(endVertex);
            }

            if (endVertex == package_package.getDestination()) {
                break;
            }
        }
    }

}

