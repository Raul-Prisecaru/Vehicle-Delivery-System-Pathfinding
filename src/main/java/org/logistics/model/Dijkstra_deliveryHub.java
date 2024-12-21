package org.logistics.model;

import java.util.*;

public class Dijkstra_deliveryHub {
    private Graph graph;
    private HashSet<DeliveryHub<String>> deliveryHubList = new HashSet<>();
    private HashMap<Vertex<String>, LinkedList<Edge>> adjacencyList = new HashMap<>();

    /**
     * Constructor Responsible for setting the graph, adjacencyList, and deliveryHubList
     * @param graph
     */
    public Dijkstra_deliveryHub(Graph graph) {
        this.graph = graph;
        this.adjacencyList = graph.getAdjacencyList();
        this.deliveryHubList = graph.getDeliveryHubList();
    }

    /**
     * Method Responsible for updating the adjacencyList to ensure any dynamically added Vertexes or edges are added
     */
    public void updateAdjacencyList() {
        this.adjacencyList = graph.getAdjacencyList();
    }

    /**
     * Method Responsible for updating the DeliveryHub to ensure any dynamically added DeliveryHubs are added
     */
    public void updateDeliveryHub() {
        this.deliveryHubList = graph.getDeliveryHubList();
    }


    /**
     * Method responsible for using dijkstra's algorithm to find the quickest route to deliveryHub
     * @Param vehicle (Vehicle) - vehicle to find the quickest route for
     * @Return None
     */
    public void find_shortest_delivery(Vehicle vehicle) {
        updateAdjacencyList();
        updateDeliveryHub();

        for (Vertex<String> vertex : adjacencyList.keySet()) {
            vertex.setDistance(Integer.MAX_VALUE);
        }
        HashMap<Vertex, Vertex> predecessor = new HashMap<>();

        PriorityQueue<Vertex> unvisited = new PriorityQueue<>(Comparator.comparingInt(Vertex::getDistance));
        Queue<Vertex> visited = new LinkedList<>();

        Vertex start_vertex = vehicle.getCurrent_location();
        start_vertex.setDistance(0);

        unvisited.add(start_vertex);
        predecessor.put(start_vertex, null);

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

        DeliveryHub deliveryHub_shortest = null;
        int minDistance = Integer.MAX_VALUE;

        for (DeliveryHub deliveryHub : deliveryHubList) {
            if (deliveryHub.getDistance() < minDistance) {
                minDistance = deliveryHub.getDistance();
                deliveryHub_shortest = deliveryHub;
            }
        }


        Vertex endVertex = deliveryHub_shortest;
        vehicle.addTravelDestination(endVertex);
        for (Vertex vertex : predecessor.keySet()) {
            if (predecessor.get(endVertex) != null) {
                vehicle.addTravelDestination(predecessor.get(endVertex));
                endVertex = predecessor.get(endVertex);
            }

            if (endVertex == deliveryHub_shortest) {
                break;
            }
        }
    }


}
