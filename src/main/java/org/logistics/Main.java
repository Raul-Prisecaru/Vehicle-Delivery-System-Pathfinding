package org.logistics;

import org.logistics.model.*;
import org.logistics.model.Package;
import org.logistics.view.Display;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws Exception {
        HashSet<Vehicle> list_of_vehicles = new HashSet<>();


        // Creating Graph Environment
        Graph graph = new Graph();

        Dijkstra_deliveryHub dijkstra_deliveryHub = new Dijkstra_deliveryHub(graph);
        Dijkstra_customerLocation dijkstra_customerLocation = new Dijkstra_customerLocation(graph);

        Display displayGraph = new Display(graph);

        // Creating DeliveryHub Nodes
        DeliveryHub<String> deliveryHubA = new DeliveryHub<String>("A");
        DeliveryHub<String> deliveryHubB = new DeliveryHub<String>("B");

        // Creating CustomerLocation Nodes
        CustomerLocation<String> customerLocationC = new CustomerLocation<String>("C");
        CustomerLocation<String> customerLocationD = new CustomerLocation<String>("D");
        CustomerLocation<String> customerLocationE = new CustomerLocation<String>("E");
        CustomerLocation<String> customerLocationF = new CustomerLocation<String>("F");
        CustomerLocation<String> customerLocationG = new CustomerLocation<String>("G");

        // Creating Packages
        Vehicle vehicle1 = new Vehicle(deliveryHubA);
        list_of_vehicles.add(vehicle1);

        Package PriorityphonePackage = new Package("Iphone", customerLocationF, 1);
        Package NonPriorityphonePackage = new Package("Iphone", customerLocationE, 0);
        vehicle1.add_deliveryPackage(PriorityphonePackage);
        vehicle1.add_deliveryPackage(NonPriorityphonePackage);

        deliveryHubA.getPackages().add(PriorityphonePackage);
        deliveryHubB.getPackages().add(NonPriorityphonePackage);

        // Creating Vertexes
        graph.add_deliveryHub(deliveryHubA);
        graph.add_deliveryHub(deliveryHubB);

        graph.add_customerLocation(customerLocationC);
        graph.add_customerLocation(customerLocationD);
        graph.add_customerLocation(customerLocationE);
        graph.add_customerLocation(customerLocationF); // New Vertex
        graph.add_customerLocation(customerLocationG); // New Vertex

        // Creating Edges
        graph.add_directed_edge(deliveryHubA, deliveryHubB, 4); // Distance: 4
        graph.add_directed_edge(deliveryHubA, customerLocationC, 2); // Distance: 2
        graph.add_directed_edge(deliveryHubB, customerLocationC, 1); // Distance: 1
        graph.add_directed_edge(deliveryHubB, customerLocationD, 2); // Distance: 2
        graph.add_directed_edge(deliveryHubB, customerLocationE, 3); // Distance: 3
        graph.add_directed_edge(customerLocationC, customerLocationE, 1); // Distance: 1
        graph.add_directed_edge(customerLocationE, customerLocationD, 5); // Distance: 5

        // New Edges to Connect New Vertexes
        graph.add_directed_edge(customerLocationE, customerLocationF, 3); // Distance: 3
        graph.add_directed_edge(customerLocationF, customerLocationG, 2); // Distance: 2
        graph.add_directed_edge(customerLocationG, deliveryHubA, 4); // Distance: 4

        // Additional Edges to Ensure Reachability
        graph.add_directed_edge(customerLocationD, customerLocationF, 2); // Distance: 2
        graph.add_directed_edge(customerLocationC, customerLocationG, 6); // Distance: 6

        // Create Initial Graph and display
        displayGraph.createGraph();
        displayGraph.displayGUI();

        while (true) {

            // For every vehicle
            for (Vehicle vehicle : list_of_vehicles) {

                // If vehicle has no travel destinations
                if (vehicle.getTravelDestinations().isEmpty()) {

                    // If Vehicle HAS packages
                    if (!vehicle.get_deliveryPackages().isEmpty()) {

                        // Find the shortest route towards customer based on package
                        dijkstra_customerLocation.find_shortest_customer(vehicle);
                    }

                    // If Vehicle has NO packages
                    if (vehicle.get_deliveryPackages().isEmpty()) {

                        // Find the shortest route towards closest deliveryHub
                        dijkstra_deliveryHub.find_shortest_delivery(vehicle);
                    }
                }



            if (!vehicle.getTravelDestinations().isEmpty()) {
                Vertex<String> nextVertex = vehicle.getTravelDestinations().pop();

                // Temp Fix for preventing null error due to unable to find edge of two same vertexes
                // TODO: Fix this issue /\
                if (vehicle.getCurrent_location() == nextVertex) {
                    continue;
                }

                // Find Relevant Edge
                Edge edge_edge = graph.findEdgeAndReturn(vehicle.getCurrent_location(), nextVertex);

                // Increase Congestion Weight by one
                edge_edge.addCongestion_weight();

                // Update Label of the edge to reflect those changes
                displayGraph.updateEdge(edge_edge);

                // Highlight the current position of the Vehicle
                displayGraph.visualise_vehicle(nextVertex, 1);

                // Vehicle Travels to the vertex
                vehicle.travel(nextVertex);

                // Timer
                Thread.sleep(500);

                // Remove the highlight of current position
                displayGraph.visualise_vehicle(nextVertex, 0);

                // Decrease Congestion by one
                edge_edge.removeCongestion_weight();

                // Update Label of the edge to reflect those changes
                displayGraph.updateEdge(edge_edge);


            }

            if (vehicle.getTravelDestinations().isEmpty()) {
                // If vehicle is in a DeliveryHub
                if (vehicle.getCurrent_location() instanceof DeliveryHub) {

                    // Store the deliveryHub
                    DeliveryHub<String> currentDeliveryHub = (DeliveryHub<String>) vehicle.getCurrent_location();

                    // For Every Package in the deliveryHub
                    for (Package package_package : currentDeliveryHub.getPackages()) {
                        // TODO: Check if vehicle is full and break the loop if full


                        // Remove the package from the deliveryHub
                        currentDeliveryHub.removePackages(package_package);

                        // Add package to the Vehicle
                        vehicle.add_deliveryPackage(package_package);
//                        vehicle.get_deliveryPackages().add(package_package);
                    }
                }

                // If vehicle is in a CustomerLocation
                if (vehicle.getCurrent_location() instanceof CustomerLocation) {

                    CustomerLocation<String> customerLocation = (CustomerLocation<String>) vehicle.getCurrent_location();

                    for (Package package_package : vehicle.get_deliveryPackages()) {
                        if (package_package.getDestination() == customerLocation) {
                            customerLocation.addCollectedPackage(package_package);
                            vehicle.remove_deliveryPackage(package_package);
                            break;
                        }
                    }
                }
            }

            }
        }

    }

}