package org.logistics.model.algorithms;

import org.logistics.model.*;
import org.logistics.model.Package;

import java.util.*;

public class Dijkstra_customerLocation {
    CongestionPrediction congestionPrediction;
      /**
      * Method responsible for using dijkstra's algorithm to find the quickest route to customerLocation Based on Vehicles packages
      * @param vehicle (Vehicle) - vehicle to find the quickest route for based on packages
      */
    public void find_shortest_customer(Vehicle vehicle, Graph graph) {
        HashMap<Vertex<String>, Vertex<String>> predecessor = new HashMap<>();
        congestionPrediction = new CongestionPrediction(graph);

        for (Vertex<String> vertex : graph.getAllDeliveryHub()) {
            vertex.setDistance(Integer.MAX_VALUE);
        }

        for (Vertex<String> vertex : graph.getAllCustomerLocation()) {
            vertex.setDistance(Integer.MAX_VALUE);
        }

        PriorityQueue<Vertex<String>> unvisited = new PriorityQueue<>(Comparator.comparingInt(Vertex::getDistance));
        Queue<Vertex<String>> visited = new LinkedList<>();

        Vertex<String> start_vertex = vehicle.getCurrent_location();
        start_vertex.setDistance(0);

        unvisited.add(start_vertex);
        predecessor.put(start_vertex, null);

        Package package_package = new Package(null, null, -1, 0, 0);

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
            Vertex<String> current = unvisited.poll();
            if (!visited.contains(current)) {

                for (Edge edge : graph.getEdges(current)) {
                    int totalDistance = current.getDistance() + getEdgeEstimateTimeWeight(edge);

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

    private int getEdgeEstimateTimeWeight(Edge edge) {
        HashMap<Integer, Integer> predictionCalculations = congestionPrediction.calculateCongestion(edge.getStart_node(), edge.getConnecting_node());
        int congestionLevel = Integer.MIN_VALUE;
        int percentagePrediction = Integer.MIN_VALUE;

        for (Integer integer : predictionCalculations.keySet()) {
            if (predictionCalculations.get(integer) > percentagePrediction) {
                congestionLevel = integer;
                percentagePrediction = predictionCalculations.get(integer);
            }
        }

        if (percentagePrediction == Integer.MIN_VALUE) {
            return edge.getCongestion_weight() + edge.getDistance_weight();
        }
        return congestionLevel + edge.getDistance_weight();
    }
}
