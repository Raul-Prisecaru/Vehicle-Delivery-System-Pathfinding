package org.logistics.model.algorithms;

import org.logistics.model.*;
import org.logistics.model.Package;

import java.util.*;

public class Dijkstra_deliveryHub {

    private Graph graph;
    CongestionPrediction congestionPrediction;

    public Dijkstra_deliveryHub(Graph graph) {
        this.graph = graph;
    }

    ArrayList<Package> packageArrayList = new ArrayList<>();

    public void retrieveAndSortPackagesFromDeliveryHub()  {
        HashSet<Package> tempPackageHashSet = new HashSet<>();
        for (DeliveryHub<String> deliveryHub : graph.getAllDeliveryHub()) {
            for (org.logistics.model.Package package_package : deliveryHub.getPackages()) {
                if (package_package.getDeliveryHub() == null) {
                    package_package.setDeliveryHub(deliveryHub);

                }

                tempPackageHashSet.add(package_package);
            }
        }

        // Sorting Algorithm
        List<Package> packageList = new ArrayList<>(tempPackageHashSet);
        for (int i = 0; i < packageList.size(); i++) {
            for (int j = 0; j < packageList.size() - i - 1; j++) {
                if (packageList.get(j).getPriority() < packageList.get(j + 1).getPriority()) {
                    Package temp = packageList.get(j);
                    packageList.set(j, packageList.get(j + 1));
                    packageList.set(j + 1, temp);
                }
            }
        }

        for (Package package_package : packageList) {
            packageArrayList.add(package_package);
        }
    }

    public DeliveryHub<String> findPriorityDeliveryHub() {
        HashMap<DeliveryHub<String>, Integer> priorityDelivery = new HashMap<>();

        for (DeliveryHub<String> deliveryHub : graph.getAllDeliveryHub()) {
            priorityDelivery.put(deliveryHub, 0);
        }

        for (Package package_package : packageArrayList) {
            priorityDelivery.put(package_package.getDeliveryHub(), priorityDelivery.get(package_package.getDeliveryHub()) + package_package.getPriority());
        }

        DeliveryHub<String> highestDelivery = null;
        int min_value = Integer.MIN_VALUE;


        for (DeliveryHub<String> deliveryHub : priorityDelivery.keySet()) {
            if (priorityDelivery.get(deliveryHub) > min_value) {
                highestDelivery = deliveryHub;
                min_value = priorityDelivery.get(deliveryHub);
            }
        }

        return highestDelivery;
    }



    /**
     * Method responsible for using dijkstra's algorithm to find the quickest route to deliveryHub
     * @Param vehicle (Vehicle) - vehicle to find the quickest route for
     * @Return None
     */
    public void find_shortest_delivery(Vehicle vehicle, Graph graph) {
        packageArrayList.clear();
        congestionPrediction = new CongestionPrediction(graph);


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

        retrieveAndSortPackagesFromDeliveryHub();
        DeliveryHub<String> deliveryHub_shortest = findPriorityDeliveryHub();




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
        return congestionLevel + edge.getDistance_weight();
    }


}
