package org.logistics.model.algorithms;

import org.logistics.model.Package;
import org.logistics.model.*;

import java.util.*;

public class Bellman_ford_deliveryLocation {

    /**
     * Method Responsible for finding the shortest path to the highest priority package in the vehicle
     * @param vehicle - Vehicle to find shortest path for
     */
    public void find_shortest_delivery(Vehicle vehicle, Graph graph) {
        HashMap<Vertex<String>, Vertex<String>> predecessor = new HashMap<>();
        PriorityQueue<Vertex<String>> unvisited = new PriorityQueue<>(Comparator.comparingInt(Vertex::getDistance));
        Queue<Vertex<String>> visited = new LinkedList<>();
        int iteration = (graph.getAllDeliveryHub().size() + graph.getAllCustomerLocation().size()) - 1;


        for (Vertex<String> vertex : graph.getAllDeliveryHub()) {
            vertex.setDistance(Integer.MAX_VALUE);
        }

        for (Vertex<String> vertex : graph.getAllCustomerLocation()) {
            vertex.setDistance(Integer.MAX_VALUE);
        }



        Vertex<String> start_vertex = vehicle.getCurrent_location();
        start_vertex.setDistance(0);

        while(iteration != 0) {


            unvisited.add(start_vertex);
            predecessor.put(start_vertex, null);

            while (!unvisited.isEmpty()) {
                Vertex<String> current = unvisited.poll();

                if (!visited.contains(current) && current.getDistance() != Integer.MAX_VALUE) {
                    for (Edge edge : graph.getEdges(current)) {
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
        }

        DeliveryHub<String> deliveryHub_shortest = null;
        int minDistance = Integer.MAX_VALUE;

        for (DeliveryHub<String> deliveryHub : graph.getAllDeliveryHub()) {
            if (deliveryHub.getDistance() < minDistance) {
                minDistance = deliveryHub.getDistance();
                deliveryHub_shortest = deliveryHub;
            }
        }


        Vertex<String> endVertex = deliveryHub_shortest;
        vehicle.addTravelDestination(endVertex);
        for (Vertex<String> vertex : predecessor.keySet()) {
            if (predecessor.get(endVertex) != null && predecessor.get(endVertex) != vehicle.getCurrent_location()) {
                vehicle.addTravelDestination(predecessor.get(endVertex));
                endVertex = predecessor.get(endVertex);
            }

            if (endVertex == deliveryHub_shortest) {
                break;
            }
        }
    }

}

