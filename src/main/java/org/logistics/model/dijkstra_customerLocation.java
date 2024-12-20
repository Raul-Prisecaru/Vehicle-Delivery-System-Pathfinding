package org.logistics.model;

import java.util.*;

public class dijkstra_customerLocation {





      /**
      * Method responsible for using dijkstra's algorithm to find the quickest route to customerLocation
      * @Param vehicle (Vehicle) - vehicle to find the quickest route for based on packages
      * @Return None
      */
    public void find_shortest_customer(Vehicle vehicle) {
        HashMap<Vertex, Vertex> predecessor = new HashMap<>();

        PriorityQueue<Vertex> unvisited = new PriorityQueue<>(Comparator.comparingInt(Vertex::get_distance));
        Queue<Vertex> visited = new LinkedList<>();

        Vertex start_vertex = vehicle.getCurrent_location();
        start_vertex.set_distance(0);

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
        }


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
