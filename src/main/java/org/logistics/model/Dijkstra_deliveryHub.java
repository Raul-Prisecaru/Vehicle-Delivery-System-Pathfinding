package org.logistics.model;

import java.util.*;

public class Dijkstra_deliveryHub {



    /**
     * Method responsible for using dijkstra's algorithm to find the quickest route to deliveryHub
     * @Param vehicle (Vehicle) - vehicle to find the quickest route for
     * @Return None
     */
    public void find_shortest_delivery(Vehicle vehicle, Graph graph) {

        System.out.println("INSIDE DELIVERYHUB ALGORTITHM");
        System.out.println(graph.getAdjacencyList());

        for (Vertex<String> vertex : graph.getAdjacencyList().keySet()) {
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
//                System.out.println("I AM IN DELIVERYHUB");

//                System.out.println("GRAPH: " + graph.getAdjacencyList());
//                if (graph.getAdjacencyList().get(current) == null) {
//                    System.out.println("I have skipped: " + current.getNodeValue());
//                    System.out.println("Edge of vertex: " + graph.getAdjacencyList().get(current));
//                    continue;
//                }

//                if (graph.getEdges(new Vertex<>("AB")) != null) {
//                    System.out.println("AB HAS BEEN FOUND I REPEAT");
//                    System.out.println(graph.getEdges(new Vertex<>("AB")).peek().getConnecting_node().getNodeValue());
//                } else if (graph.getEdges(new Vertex<>("AB")) == null) {
//                    System.out.println("I REPEAT CURRENT IS NULL::: " + current);
//                }

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
