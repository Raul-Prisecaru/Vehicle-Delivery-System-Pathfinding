package org.logistics;

import org.logistics.model.*;
import org.logistics.model.Package;
import org.logistics.view.Display;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import java.util.Iterator;

public class Main {


    public static void printVehicleLogs(Vehicle vehicle) {
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
    }

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
//        graph.createVehicle(deliveryHubB);
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



        while (true) {
            for (Vehicle vehicle : graph.getVehicleList()) {
                for (Vehicle vehicle1 : graph.getVehicleList()) {
                    displayGraph.visualise_vertex(vehicle1.getCurrent_location(), 1);

                }
                TimeUnit.SECONDS.sleep(1);


                if (vehicle.getTravelDestinations().isEmpty()) {
                    if (!vehicle.get_deliveryPackages().isEmpty()) {
                        dijkstra_customerLocation.find_shortest_customer(vehicle);
                        updateEdgePath(graph, displayGraph, vehicle);
                        TimeUnit.SECONDS.sleep(1);
                    }


                    if (vehicle.get_deliveryPackages().isEmpty()) {
                        dijkstra_deliveryHub.find_shortest_delivery(vehicle);
                        updateEdgePath(graph, displayGraph, vehicle);
                        TimeUnit.SECONDS.sleep(1);
                    }

                }


                if (!vehicle.getTravelDestinations().isEmpty()) {
                    Vertex<String> nextPosition = vehicle.getTravelDestinations().pop();
                    Edge edge = graph.findEdgeAndReturn(vehicle.getCurrent_location(), nextPosition);
                    deHighlightVisitedEdge(displayGraph, edge, nextPosition, vehicle);
                    vehicle.travel(nextPosition);
                    TimeUnit.SECONDS.sleep(1);
                }


                if (vehicle.getTravelDestinations().isEmpty()) {
                    if (vehicle.getCurrent_location() instanceof CustomerLocation<String>) {
                        Package check_package = checkIfPackageCustomerLocation(vehicle);

                        if (check_package != null) {
                            customerDeliverPackage((CustomerLocation<String>) vehicle.getCurrent_location(), check_package);
                        }
                    }


                    if (vehicle.getCurrent_location() instanceof DeliveryHub<String> && vehicle.get_deliveryPackages().size() < 2) {
                        DeliveryHub<String> currentDeliveryHub = (DeliveryHub<String>) vehicle.getCurrent_location();
                        vehiclePickupPackages(vehicle, currentDeliveryHub);
                        currentDeliveryHub.generatePackage(graph, 2);

                    }

                }




            }


        }


    }

    private static Package checkIfPackageCustomerLocation(Vehicle vehicle) {
        for (Package package_package : vehicle.get_deliveryPackages()) {
            if (package_package.getDestination() == vehicle.getCurrent_location()) {
                return package_package;
            }
        }
        return null;

    }


    private static void vehiclePickupPackages(Vehicle vehicle, DeliveryHub<String> currentDeliveryHub) throws Exception {

        Iterator<Package> packageIterator = currentDeliveryHub.getPackages().iterator();

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

    private static void customerDeliverPackage(CustomerLocation<String> customerLocation, Package package_package) {
        customerLocation.addCollectedPackage(package_package);
    }

    private static void deHighlightVisitedEdge(Display displayGraph, Edge edge_edge, Vertex<String> nextVertex, Vehicle vehicle) {
        displayGraph.visualise_edge(edge_edge, 0);
        displayGraph.visualise_vertex(vehicle.getCurrent_location(), 0);
        edge_edge.removeCongestion_weight();
        displayGraph.updateEdge(edge_edge);
    }

    private static void updateEdgePath(Graph graph, Display displayGraph, Vehicle vehicle) {
        List<Vertex<String>> travelDestinations = new ArrayList<>(vehicle.getTravelDestinations());

        for (int i = 0; i < travelDestinations.size() - 1; i++) {
            Vertex<String> currentVertex = travelDestinations.get(i);
            Vertex<String> nextVertex = travelDestinations.get(i + 1);

            Edge edge = graph.findEdgeAndReturn(nextVertex, currentVertex);
            if (edge != null) {
                displayGraph.visualise_edge(edge, 1);
                edge.addCongestion_weight();
                displayGraph.updateEdge(edge);
            }
        }

        Vertex<String> firstDestination = travelDestinations.getLast();
        Edge edge = graph.findEdgeAndReturn(vehicle.getCurrent_location(), firstDestination);

        if (edge != null) {
            displayGraph.visualise_edge(edge, 1);
            edge.addCongestion_weight();
            displayGraph.updateEdge(edge);
        }
    }


}