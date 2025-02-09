package org.logistics.controller;

import org.logistics.model.*;
import org.logistics.model.Package;
import org.logistics.model.algorithms.Bellman_ford_customerLocation;
import org.logistics.model.algorithms.Bellman_ford_deliveryLocation;
import org.logistics.model.algorithms.Dijkstra_customerLocation;
import org.logistics.model.algorithms.Dijkstra_deliveryHub;
import org.logistics.view.directedGraphDisplay;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import java.util.Iterator;

public class directedGraphSimulation {

    /**
     * 1 - Dijkstra Pathfinding
     * 2 - Bellman-ford
     */

    public void runSimulation() throws Exception {

        // Initializing Graph
        Graph graph = new Graph();


        // Initializing bellman-ford
        Bellman_ford_customerLocation bellmanFordCustomerLocation = new Bellman_ford_customerLocation();
        Bellman_ford_deliveryLocation bellmanFordDeliveryLocation = new Bellman_ford_deliveryLocation();

        // Initializing GUI
        directedGraphDisplay directedGraphDisplayGraph = new directedGraphDisplay(graph);

        // Initializing DeliveryHub Vertexes
        DeliveryHub<String> deliveryHubA = new DeliveryHub<String>("A");
        DeliveryHub<String> deliveryHubB = new DeliveryHub<String>("B");

        // Initializing CustomerLocation Vertexes
        CustomerLocation<String> customerLocationC = new CustomerLocation<String>("C");
        CustomerLocation<String> customerLocationD = new CustomerLocation<String>("D");
        CustomerLocation<String> customerLocationE = new CustomerLocation<String>("E");
        CustomerLocation<String> customerLocationF = new CustomerLocation<String>("F");
        CustomerLocation<String> customerLocationG = new CustomerLocation<String>("G");

        // Initializing dijkstra Algorithm
        Dijkstra_customerLocation dijkstra_customerLocation = new Dijkstra_customerLocation();
        Dijkstra_deliveryHub dijkstra_deliveryHub = new Dijkstra_deliveryHub(graph);

        // Creating Vehicle
        graph.createVehicle(deliveryHubA);
        graph.createVehicle(deliveryHubA);
//        graph.createVehicle(deliveryHubB);
        for (Vehicle vehicle : graph.getVehicleList()) {
            vehicle.add_deliveryPackage(new Package("TestItem", customerLocationG, 4, 5, 2));
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
        deliveryHubA.generatePackage(graph, 2, deliveryHubA);
        deliveryHubB.generatePackage(graph, 2, deliveryHubB);

        // Creating GUI
        directedGraphDisplayGraph.createGraph();
        directedGraphDisplayGraph.displayGUI();

        // Initializing multiAgent
        MultiAgent multiAgent = new MultiAgent(graph);


        while (true) {
            logs(graph);
            List<Vehicle> vehicles = new ArrayList<>(graph.getVehicleList());
            for (Vehicle vehicle : vehicles) {
                for (Vehicle vehicle1 : vehicles) {
                    directedGraphDisplayGraph.visualise_vertex(vehicle1.getCurrent_location(), 1);

                }
                TimeUnit.MILLISECONDS.sleep(500);


                if (vehicle.getTravelDestinations().isEmpty()) {
                    if (!vehicle.get_deliveryPackages().isEmpty()) {
                        if (graph.getPathfindingOption() == 1) {
                            System.out.println("Using Dijkstra to find customerLocation");
                            dijkstra_customerLocation.find_shortest_customer(vehicle, graph);
                        }

                        if (graph.getPathfindingOption() == 2) {
                            System.out.println("Using bellman-ford to find customerLocation");
                            bellmanFordCustomerLocation.find_shortest_customer(vehicle, graph);
                        }

                        updateEdgePath(graph, directedGraphDisplayGraph, vehicle);
                        TimeUnit.MILLISECONDS.sleep(500);
                    }


                    if (vehicle.get_deliveryPackages().isEmpty()) {

                        if (graph.getPathfindingOption() == 1) {
                            System.out.println("Using Dijkstra to find deliveryHub");
                            dijkstra_deliveryHub.find_shortest_delivery(vehicle, graph);
                        }

                        if (graph.getPathfindingOption() == 2) {
                            System.out.println("Using bellman-ford to find deliveryHub");
                            bellmanFordDeliveryLocation.find_shortest_delivery(vehicle, graph);
                        }

                        updateEdgePath(graph, directedGraphDisplayGraph, vehicle);
                        TimeUnit.MILLISECONDS.sleep(500);
                    }

                }


                HashSet<CustomerLocation<String>> multipleVehicleCustomerLocation = multiAgent.findMultipleVehiclesInCustomerLocations();
                if (multipleVehicleCustomerLocation != null) {
                    multiAgent.vehicleCommunicate(multipleVehicleCustomerLocation);
                }

                if (!vehicle.getTravelDestinations().isEmpty()) {
                    Vertex<String> nextPosition = vehicle.getTravelDestinations().pop();

                    Edge edge = graph.findEdgeAndReturn(vehicle.getCurrent_location(), nextPosition);



                    if (nextPosition == null || edge == null) {
                        continue;
                    }

                    deHighlightVisitedEdge(directedGraphDisplayGraph, edge, vehicle);
                    vehicle.travel(nextPosition);

                    TimeUnit.MILLISECONDS.sleep(500);
                }


                if (vehicle.getCurrent_location() instanceof CustomerLocation<String>) {
                    Package check_package = checkIfPackageIsForCustomerLocation(vehicle);

                    while (check_package != null) {
                        customerDeliverPackage((CustomerLocation<String>) vehicle.getCurrent_location(), check_package, vehicle);
                        System.out.println("Vehicle has dropped packages to " + check_package.getDestination());
                        check_package = checkIfPackageIsForCustomerLocation(vehicle);

                    }
                }


                if (vehicle.getCurrent_location() instanceof DeliveryHub<String> && vehicle.get_deliveryPackages().size() < 2) {
                    DeliveryHub<String> currentDeliveryHub = (DeliveryHub<String>) vehicle.getCurrent_location();
                    vehiclePickupPackages(vehicle, currentDeliveryHub);
                    System.out.println("Vehicle has picked up package from " + currentDeliveryHub);
                    generatePackagesEveryDeliveryHub(graph);

                }

            }
            TimeUnit.MILLISECONDS.sleep(500);


        }


    }



    /**
     * Method Responsible fer checking if any of the package in the vehicle is for the CustomerLocation
     * @param vehicle (Vehicle) - vehicle to get packages from and check with it's current location
     * @return package for customerLocation else null
     */
    private static Package checkIfPackageIsForCustomerLocation(Vehicle vehicle) {
        for (Package package_package : vehicle.get_deliveryPackages()) {
            if (package_package.getDestination() == vehicle.getCurrent_location()) {
                return package_package;
            }
        }
        return null;

    }

    /**
     * Method Responsible for package to be picked up from DeliveryHub
     * @param vehicle (Vehicle) - vehicle to add the package to
     * @param currentDeliveryHub (DeliveryHub) - package to search and remove from
     * @throws Exception - throws Exception if vehicle is full
     */
    private static void vehiclePickupPackages(Vehicle vehicle, DeliveryHub<String> currentDeliveryHub) throws Exception {

        if (!currentDeliveryHub.getPackages().isEmpty() && vehicle.get_deliveryPackages().size() < 2) {
            Iterator<Package> packageIterator = currentDeliveryHub.getPackages().iterator();
            // Retrieve the current package
            Package package_package = packageIterator.next();

            // Add package to the Vehicle
            vehicle.add_deliveryPackage(package_package);


            // Remove the package from the deliveryHub safely
            packageIterator.remove();
        }
    }

    /**
     * Method Responsible for package to be delivered to the CustomerLocation
     * @param customerLocation (CustomerLocation) - customerLocation to add the package to
     * @param package_package (Package) - Packager to add to customerLocation and remove from Vehicle
     * @param vehicle (Vehicle) - which vehicle to remove package from
     */
    private static void customerDeliverPackage(CustomerLocation<String> customerLocation, Package package_package, Vehicle vehicle) {
        customerLocation.addCollectedPackage(package_package);
        vehicle.remove_deliveryPackage(package_package);

    }

    /**
     * Method Responsible for removing the specified edge highlight alongside with it's congestionWeight
     * @param directedGraphDisplayGraph (Display) - GUI to update the highlight and edge label from
     * @param edge_edge (Edge) - Edge to remove highlight and congestion weight from
     * @param vehicle (Vehicle) - Used to remove highlight from vehicle's current position
     */
    private static void deHighlightVisitedEdge(directedGraphDisplay directedGraphDisplayGraph, Edge edge_edge, Vehicle vehicle) {
        directedGraphDisplayGraph.visualise_edge(edge_edge, 0);
        directedGraphDisplayGraph.visualise_vertex(vehicle.getCurrent_location(), 0);
        edge_edge.removeCongestion_weight();
        directedGraphDisplayGraph.updateEdge(edge_edge);
    }

    /**
     * Method Responsible for highlighting and adding congestion
     * to the every edge in vehicle's travelDestinations
     * @param graph (Graph) - Used to find the relevant Edge in AdjacencyList
     * @param directedGraphDisplayGraph (Display) - Used to reflect and highlight the path in the GUI
     * @param vehicle (Vehicle) - Vehicle to find and highlight the path for
     */
    private static void updateEdgePath(Graph graph, directedGraphDisplay directedGraphDisplayGraph, Vehicle vehicle) {
        List<Vertex<String>> travelDestinations = new ArrayList<>(vehicle.getTravelDestinations());

        for (int i = 0; i < travelDestinations.size() - 1; i++) {
            Vertex<String> currentVertex = travelDestinations.get(i);
            Vertex<String> nextVertex = travelDestinations.get(i + 1);

            Edge edge = graph.findEdgeAndReturn(nextVertex, currentVertex);
            if (edge != null) {
                directedGraphDisplayGraph.visualise_edge(edge, 1);
                edge.addCongestion_weight();
                directedGraphDisplayGraph.updateEdge(edge);
            }
        }

        Vertex<String> firstDestination = travelDestinations.getLast();
        Edge edge = graph.findEdgeAndReturn(vehicle.getCurrent_location(), firstDestination);

        if (edge != null) {
            directedGraphDisplayGraph.visualise_edge(edge, 1);
            edge.addCongestion_weight();
            directedGraphDisplayGraph.updateEdge(edge);
        }
    }

    /**
     * Method Responsible for generating logs.
     * This will generate logs for all vehicles, and DeliveryHubs & CustomerLocations
     * @param graph (Graph) - graph to get the status of everything from
     */
    private static void logs(Graph graph) {
        System.out.println("--- Logs ---");
        for (Vehicle vehicle : graph.getVehicleList()) {
            System.out.println("--- Vehicle Information ---");
            System.out.println("Vehicle: " + vehicle);
            System.out.println("-- Vehicle Current Position: " + vehicle.getCurrent_location());
            System.out.println("-- Vehicle Destinations: " + vehicle.getTravelDestinations());

            for (Package package_package : vehicle.get_deliveryPackages()) {
                System.out.println("Vehicle Package: " + package_package.getItem_Name());
                System.out.println("-- Package Destination: " + package_package.getDestination());
                System.out.println("-- Package Priority: " + package_package.getPriority());
            }
        }
        System.out.println();
        System.out.println("--- DeliveryHubs Information ---");
        for (DeliveryHub<String> deliveryHub : graph.getAllDeliveryHub()) {
            System.out.println("DeliveryHub: " + deliveryHub.getNodeValue());

            for (Package package_package : deliveryHub.getPackages()) {
                System.out.println("Package: " + package_package.getItem_Name());
                System.out.println("-- Package Destination: " + package_package.getDestination());
                System.out.println("-- Package Priority: " + package_package.getPriority());
            }
        }
        System.out.println();
        System.out.println("--- CustomerLocation Information ---");
        for (CustomerLocation<String> customerLocation : graph.getAllCustomerLocation()) {
            System.out.println("CustomerLocation: " + customerLocation.getNodeValue());

            for (Package package_package : customerLocation.getCollectedPackages()) {
                System.out.println("Package: " + package_package.getItem_Name());
                System.out.println("-- Package Destination: " + package_package.getDestination());
                System.out.println("-- Package Priority: " + package_package.getPriority());
            }
        }

    }

    private void generatePackagesEveryDeliveryHub(Graph graph) {
        for (DeliveryHub<String> deliveryHub : graph.getAllDeliveryHub()) {
            deliveryHub.generatePackage(graph, 2);
        }
    }




}
