package org.logistics.model;

import java.util.*;

public class Dijkstra_customerLocation {
    private Graph graph;
    private HashMap<Vertex<String>, LinkedList<Edge>> adjacencyList = new HashMap<>();

    /**
     * Constructor is responsible for setting the Graph and adjacencyList
     * @param graph - Graph Environment
     */
    public Dijkstra_customerLocation(Graph graph) {
        this.graph = graph;
        this.adjacencyList = graph.getAdjacencyList();
    }

    /**
     * Method Responsible for updating the adjacencyList to ensure any new dynamically added Vertexes or edges are added
     */
    // TODO: Ensure this actually updates the adjacencyList upon adding a new Vertex
    public void updateAdjacencyList() {
        this.adjacencyList = graph.getAdjacencyList();
    }

      /**
      * Method responsible for using dijkstra's algorithm to find the quickest route to customerLocation Based on Vehicles packages
      * @param vehicle (Vehicle) - vehicle to find the quickest route for based on packages
      */
    public void find_shortest_customer(Vehicle vehicle) {
        updateAdjacencyList();
        HashMap<Vertex<String>, Vertex<String>> predecessor = new HashMap<>();

        for (Vertex<String> vertex : adjacencyList.keySet()) {
            vertex.setDistance(Integer.MAX_VALUE);
        }

        PriorityQueue<Vertex<String>> unvisited = new PriorityQueue<>(Comparator.comparingInt(Vertex::getDistance));
        Queue<Vertex<String>> visited = new LinkedList<>();

        Vertex<String> start_vertex = vehicle.getCurrent_location();
        start_vertex.setDistance(0);

        unvisited.add(start_vertex);
        predecessor.put(start_vertex, null);

        Package package_package = new Package(null, null, -1);

        if (vehicle.get_deliveryPackages().size() == 2) {
            // Compare the two packages
            for (Package package_package_temp : vehicle.get_deliveryPackages()) {
                if (package_package_temp.getPriority() > package_package.getPriority()) {
                    package_package = package_package_temp;
                }
            }
        }

        if (vehicle.get_deliveryPackages().size() == 1) {
            // set the package_package from null to what the vehicle has
            package_package = vehicle.get_deliveryPackages().peek();
        }


        while (!unvisited.isEmpty()) {
            Vertex current = unvisited.poll();
            if (!visited.contains(current)) {
                for (Edge edge : adjacencyList.get(current)) {
                    int totalDistance = current.getDistance() + edge.getDistance_weight();

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
        Vertex endVertex = package_package.getDestination();
        vehicle.addTravelDestination(endVertex);
        for (Vertex vertex : predecessor.keySet()) {
            if (predecessor.get(endVertex) != null) {
                vehicle.addTravelDestination(predecessor.get(endVertex));
                endVertex = predecessor.get(endVertex);
            }

            if (endVertex == package_package.getDestination()) {
                break;
            }
        }
    }
}
