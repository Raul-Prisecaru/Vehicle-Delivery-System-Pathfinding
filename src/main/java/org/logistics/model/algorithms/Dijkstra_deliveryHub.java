package org.logistics.model.algorithms;

import org.logistics.model.*;
import org.logistics.model.Package;

import java.util.*;

public class Dijkstra_deliveryHub {

    private Graph graph;

    public Dijkstra_deliveryHub(Graph graph) {
        this.graph = graph;
    }

    HashSet<Package> packageHashSet = new HashSet<>();

    public void retrievePackagesFromDeliveryHub()  {

        for (DeliveryHub<String> deliveryHub : graph.getAllDeliveryHub()) {
            for (org.logistics.model.Package package_package : deliveryHub.getPackages()) {
                if (package_package.getDeliveryHub() == null) {
                    package_package.setDeliveryHub(deliveryHub);

                }

                packageHashSet.add(package_package);

            }
        }
    }


    /**
     * Method responsible for using dijkstra's algorithm to find the quickest route to deliveryHub
     * @Param vehicle (Vehicle) - vehicle to find the quickest route for
     * @Return None
     */
    public void find_shortest_delivery(Vehicle vehicle, Graph graph) {

        for (Vertex<String> vertex : graph.getAllDeliveryHub()) {
            vertex.setDistance(Integer.MAX_VALUE);
        }

        for (Vertex<String> vertex : graph.getAllCustomerLocation()) {
            vertex.setDistance(Integer.MAX_VALUE);
        }

        HashMap<Vertex<String>, Vertex<String>> predecessor = new HashMap<>();

        PriorityQueue<Vertex<String>> unvisited = new PriorityQueue<>(Comparator.comparingInt(Vertex<String>::getDistance));
        Queue<Vertex<String>> visited = new LinkedList<>();

        Vertex<String> start_vertex = vehicle.getCurrent_location();
        start_vertex.setDistance(0);

        unvisited.add(start_vertex);
        predecessor.put(start_vertex, null);

        while (!unvisited.isEmpty()) {
            Vertex<String> current = unvisited.poll();
            if (!visited.contains(current)) {

                for (Edge edge : graph.getEdges(current)) {
                    if (Objects.equals(current.getNodeValue(), "AB")) {
                        System.out.println("IT WORKED");
                    }


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
