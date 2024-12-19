package org.logistics;

import org.logistics.model.*;
import org.logistics.model.Package;
import org.logistics.view.Display;

import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        HashSet<Vehicle> list_of_vehicles = new HashSet<>();


        // Creating Graph Environment
        Scanner scanner = new Scanner(System.in);
        Graph graph = new Graph();
        Pathfinding pathfinding = new Pathfinding(graph);
        Display displayGraph = new Display(graph);

        // Creating DeliveryHub Nodes
        DeliveryHub deliveryHubA = new DeliveryHub("A");
        DeliveryHub deliveryHubB = new DeliveryHub("B");

        // Creating CustomerLocation Nodes
        CustomerLocation customerLocationC = new CustomerLocation("C");
        CustomerLocation customerLocationD = new CustomerLocation("D");
        CustomerLocation customerLocationE = new CustomerLocation("E");
        CustomerLocation customerLocationF = new CustomerLocation("F");
        CustomerLocation customerLocationG = new CustomerLocation("G");

        // Creating Packages
        Vehicle vehicle1 = new Vehicle(deliveryHubA);
        list_of_vehicles.add(vehicle1);

        Package phonePackage = new Package("Iphone", customerLocationE, 1);
        vehicle1.add_deliveryPackage(phonePackage);
        deliveryHubA.getPackages().add(phonePackage);

        // Creating Vertexes
        graph.add_vertex(deliveryHubA);
        graph.add_vertex(deliveryHubB);

        graph.add_vertex(customerLocationC);
        graph.add_vertex(customerLocationD);
        graph.add_vertex(customerLocationE);
        graph.add_vertex(customerLocationF); // New Vertex
        graph.add_vertex(customerLocationG); // New Vertex

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

//        while (true) {
//            // travelDestination == empty
//
//            // // Search for a DeliveryHub
//            // // Go to the DeliveryHub
//
//            // travelDestination != empty
//
//            // Loop throught the travel destinations (pathfinding class)
//            for (Vertex travel_vertex : vehicle1.getTravelDestinations().reversed()) {
//                vehicle1.travel(travel_vertex);
//            }
//
//
//
//        }

//        graph.print_List();
//        pathfinding.find_shortest_path(vehicle1);
//        vehicle1.travel();

        // Create Initial Graph and display
        displayGraph.createGraph();
        displayGraph.displayGUI();
        while (true) {
            // call pathfinding method for vehicles to find their destinations
            for (Vehicle vehicle : list_of_vehicles) {
                if (vehicle.get_deliveryPackages().isEmpty()) {
                    pathfinding.find_shortest_customer(customerLocationE, vehicle);
                }

                if (!vehicle.get_deliveryPackages().isEmpty()) {
                    pathfinding.find_shortest_delivery(deliveryHubA, vehicle);

                }
            }

            for (Vertex vertex : vehicle1.getTravelDestinations().reversed()) {
                displayGraph.visualise_vehicle(vertex, 1);
                vehicle1.travel(vertex);
                Thread.sleep(500);
                displayGraph.visualise_vehicle(vertex, 0);
            }

            // If we collecting packages from deliveryHub
//            if (vehicle1.getCurrent_vertex() == deliveryHubA) {
//                // Collect packages from DeliveryHub
//                for (Package package_delivery : deliveryHubA.getPackages()) {
//                    deliveryHubA.removePackages(package_delivery);
//                    vehicle1.get_deliveryPackages().add(package_delivery);
//                }
//            }
//
//            // If we dropping off package to CustomerLocation
//            if (vehicle1.getCurrent_vertex() == vehicle1.get_deliveryPackages().peek().getDestination()) {
//                // Drop the packages off to the Customer Location
//                System.out.println("PACKAGE: " + vehicle1.get_deliveryPackages());
//                vehicle1.get_deliveryPackages().poll().getDestination().getCollected_packages().add(vehicle1.get_deliveryPackages().peek());
//                System.out.println("PACKAGE: " + vehicle1.get_deliveryPackages());
//
//            }

            vehicle1.getTravelDestinations().clear();

            // vehicle travels to those destination

            // Drop off the packages

            // Update the Graph to reflect those changes?

            // pathfinding to the nearest deliveryHub

            // vehicle travels to those destinations



            // Display the options for dynamically modify graph
            // Move this to it's own thread to run alongside simulation??
//            displayGraph.dynamic_options();
        }


    }

}