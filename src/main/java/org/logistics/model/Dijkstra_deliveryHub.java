package org.logistics.model;

import java.util.*;

public class Dijkstra_deliveryHub {
    private HashSet<DeliveryHub> deliveryHubList = new HashSet<>();
    private HashMap<Vertex, LinkedList<Edge>> adjacencyList = new HashMap<>();

    public Dijkstra_deliveryHub(Graph graph) {
        this.adjacencyList = graph.getAdjacencyList();
        this.deliveryHubList = graph.getDeliveryHubList();
    }


    /**
     * Method responsible for using dijkstra's algorithm to find the quickest route to deliveryHub
     * @Param vehicle (Vehicle) - vehicle to find the quickest route for
     * @Return None
     */
    public void find_shortest_delivery(Vehicle vehicle) {
        HashMap<Vertex, Vertex> predecessor = new HashMap<>();

        PriorityQueue<Vertex> unvisited = new PriorityQueue<>(Comparator.comparingInt(Vertex::get_distance));
        Queue<Vertex> visited = new LinkedList<>();

        Vertex start_vertex = vehicle.getCurrent_location();
        start_vertex.set_distance(0);

        unvisited.add(start_vertex);
        predecessor.put(start_vertex, null);

        while (!unvisited.isEmpty()) {
            Vertex current = unvisited.poll();
            if (!visited.contains(current)) {
                for (Edge edge : adjacencyList.get(current)) {
                    int totalDistance = current.get_distance() + edge.getDistance_weight();
                    edge.getConnecting_node().set_distance(Integer.MAX_VALUE);

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

        DeliveryHub deliveryHub_shortest = null;
        int minDistance = Integer.MAX_VALUE;

        for (DeliveryHub deliveryHub : deliveryHubList) {
            if (deliveryHub.get_distance() < minDistance) {
                minDistance = deliveryHub.get_distance();
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
