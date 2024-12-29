package org.logistics;

import org.logistics.model.*;
import org.logistics.model.Package;
import org.logistics.view.Display;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws Exception {

        // Initializing Graph
        Graph graph = new Graph();

        // Initializing vehicleLogging

        // Initializing dijkstra Algorithm
        Dijkstra_deliveryHub dijkstra_deliveryHub = new Dijkstra_deliveryHub(graph);
        Dijkstra_customerLocation dijkstra_customerLocation = new Dijkstra_customerLocation(graph);

        // Initializing GUI
        Display displayGraph = new Display(graph);

        // Initializing DeliveryHub Vertexes
        DeliveryHub<String> deliveryHubA = new DeliveryHub<String>("A");
        DeliveryHub<String> deliveryHubB = new DeliveryHub<String>("B");

        // Initializing CustomerLocation Vertexes
        CustomerLocation<String> customerLocationC = new CustomerLocation<String>("C");
        CustomerLocation<String> customerLocationD = new CustomerLocation<String>("D");
        CustomerLocation<String> customerLocationE = new CustomerLocation<String>("E");
        CustomerLocation<String> customerLocationF = new CustomerLocation<String>("F");
        CustomerLocation<String> customerLocationG = new CustomerLocation<String>("G");

        // Creating Vehicle
        graph.createVehicle(deliveryHubA);

        for (Vehicle vehicle : graph.getVehicleList()) {
            vehicle.add_deliveryPackage(new Package("TestItem", customerLocationF, 4));
        }

        // Adding DeliveryHub to the Graph
        graph.add_deliveryHub(deliveryHubA);
        graph.add_deliveryHub(deliveryHubB);

        // Adding CustomerLocation to the Graph
        graph.add_customerLocation(customerLocationC);
        graph.add_customerLocation(customerLocationD);
        graph.add_customerLocation(customerLocationE);
        graph.add_customerLocation(customerLocationF);
        graph.add_customerLocation(customerLocationG);

        // Adding Weighted Edges connections to the Graph
        graph.add_directed_edge(deliveryHubA, deliveryHubB, 4);
        graph.add_directed_edge(deliveryHubA, customerLocationC, 2);

        graph.add_directed_edge(deliveryHubB, customerLocationC, 1);
        graph.add_directed_edge(deliveryHubB, customerLocationD, 2);
        graph.add_directed_edge(deliveryHubB, customerLocationE, 3);

        graph.add_directed_edge(customerLocationC, customerLocationE, 1);
        graph.add_directed_edge(customerLocationC, customerLocationG, 6);
        graph.add_directed_edge(customerLocationD, customerLocationF, 2);

        graph.add_directed_edge(customerLocationE, customerLocationD, 5);
        graph.add_directed_edge(customerLocationE, customerLocationF, 3);
        graph.add_directed_edge(customerLocationF, customerLocationG, 2);

        graph.add_directed_edge(customerLocationG, deliveryHubA, 4);

        // Generating Packages for the DeliveryHubs
        deliveryHubA.generatePackage(graph, 2);
        deliveryHubB.generatePackage(graph, 2);

        // Creating GUI
        displayGraph.createGraph();
        displayGraph.displayGUI();


        // Simulation Logic
        while (true) {

            // For every vehicle
            for (Vehicle vehicle : graph.getVehicleList()) {
                System.out.println("--- Logs ---");
                System.out.println("Vehicle: " + vehicle);
                System.out.println("---");
                System.out.println("Current Location: " + vehicle.getCurrent_location().getNodeValue());
                for (Package package_package : vehicle.get_deliveryPackages()) {
                    System.out.println("---");
                    System.out.println("Package Name: " + package_package.getItem_Name());
                    System.out.println("Package Location: " + package_package.getDestination());
                    System.out.println("Package Priority: " + package_package.getPriority());
                }

                System.out.println("Travel Destinations: " + vehicle.getTravelDestinations());

                // If vehicle has no travel destinations
                if (vehicle.getTravelDestinations().isEmpty()) {

                    // If Vehicle HAS packages
                    if (!vehicle.get_deliveryPackages().isEmpty()) {


                        // Find the shortest route towards customer based on package
                        dijkstra_customerLocation.find_shortest_customer(vehicle);

                        for (Vertex<String> vertex : vehicle.getTravelDestinations()) {
                            for (Vertex<String> vertex2 : vehicle.getTravelDestinations()) {
                                if (graph.findEdgeAndReturn(vertex, vertex2) != null) {
                                    displayGraph.visualise_edge(new Edge(vertex, vertex2, 0), 1);
                                }
                            }
                        }


                    }

                    // If Vehicle has NO packages
                    if (vehicle.get_deliveryPackages().isEmpty()) {

                        // Find the shortest route towards closest deliveryHub
                        dijkstra_deliveryHub.find_shortest_delivery(vehicle);

                        for (Vertex<String> vertex : vehicle.getTravelDestinations()) {
                            for (Vertex<String> vertex2 : vehicle.getTravelDestinations()) {
                                if (graph.findEdgeAndReturn(vertex, vertex2) != null) {
                                    displayGraph.visualise_edge(new Edge(vertex, vertex2, 0), 1);
                                }
                            }
                        }
                    }
                }

            // If Vehicle has travel Destinations
            if (!vehicle.getTravelDestinations().isEmpty()) {


                // Highlight the current position of the Vehicle
                Vertex<String> nextVertex = vehicle.getTravelDestinations().pop();


                if (nextVertex == vehicle.getCurrent_location()) {
                    continue;
                }

                // Find Relevant Edge
                Edge edge_edge = graph.findEdgeAndReturn(vehicle.getCurrent_location(), nextVertex);

                // Increase Congestion Weight by one
                edge_edge.addCongestion_weight();

                // Update Label of the edge to reflect those changes
                displayGraph.updateEdge(edge_edge);

                // Highlight the current position of the Vehicle
                displayGraph.visualise_vertex(nextVertex, 1);

                // Vehicle Travels to the vertex
                vehicle.travel(nextVertex);

                // Timer
                Thread.sleep(1500 );

                // Remove the highlight of current position
                displayGraph.visualise_vertex(nextVertex, 0);

                // Decrease Congestion by one
                edge_edge.removeCongestion_weight();

                // Update Label of the edge to reflect those changes
                displayGraph.updateEdge(edge_edge);

            }

            // if Vehicle Travel Destination is empty
            if (vehicle.getTravelDestinations().isEmpty()) {

                // If vehicle is in a DeliveryHub
                if (vehicle.getCurrent_location() instanceof DeliveryHub) {

                    // Store the deliveryHub
                    DeliveryHub<String> currentDeliveryHub = (DeliveryHub<String>) vehicle.getCurrent_location();

                    // Iterator to safely delete packages from deliveryHub (Avoids ConcurrentModification Exception)
                    Iterator<Package> packageIterator = currentDeliveryHub.getPackages().iterator();

                    // If there is a package
                    while (packageIterator.hasNext()) {

                        // If Vehicle has less than two packages OR deliveryHub isn't empty
                        if (vehicle.get_deliveryPackages().size() < 2 || !currentDeliveryHub.getPackages().isEmpty()) {
                            // Retrieve the current package
                            Package package_package = packageIterator.next();

                            // Add package to the Vehicle
                            vehicle.add_deliveryPackage(package_package);


                            // Remove the package from the deliveryHub safely
                            packageIterator.remove();

                        }

                    }
                    currentDeliveryHub.generatePackage(graph);

                }

                // If vehicle is in a CustomerLocation
                if (vehicle.getCurrent_location() instanceof CustomerLocation) {

                    CustomerLocation<String> customerLocation = (CustomerLocation<String>) vehicle.getCurrent_location();

                    // For every package in the vehicle
                    for (Package package_package : vehicle.get_deliveryPackages()) {

                        // If package destination is the same as the current position
                        if (package_package.getDestination() == customerLocation) {

                            // Give package to the Customer
                            customerLocation.addCollectedPackage(package_package);

                            // Remove package from the vehicle
                            vehicle.remove_deliveryPackage(package_package);

                            // Break the loop
                            break;
                        }
                    }
                }
            }

            }
        }

    }

}