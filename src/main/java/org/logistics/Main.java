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

        Dijkstra_deliveryHub dijkstra_deliveryHub = new Dijkstra_deliveryHub(graph);
        Dijkstra_customerLocation dijkstra_customerLocation = new Dijkstra_customerLocation(graph);

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

        Package PriorityphonePackage = new Package("Iphone", customerLocationF, 1);
        Package NonPriorityphonePackage = new Package("Iphone", customerLocationE, 0);
        vehicle1.add_deliveryPackage(PriorityphonePackage);
        vehicle1.add_deliveryPackage(NonPriorityphonePackage);
        deliveryHubA.getPackages().add(PriorityphonePackage);
        deliveryHubB.getPackages().add(NonPriorityphonePackage);

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

        // Create Initial Graph and display
        displayGraph.createGraph();
        displayGraph.displayGUI();

        while (true) {
            // call pathfinding method for vehicles to find their destinations
            for (Vehicle vehicle : list_of_vehicles) {
                if (!vehicle.get_deliveryPackages().isEmpty()) {
                    dijkstra_customerLocation.find_shortest_customer(vehicle);
                }

                if (vehicle.get_deliveryPackages().isEmpty()) {
                    dijkstra_deliveryHub.find_shortest_delivery(vehicle);
                }
            }

            for (Vertex vertex : vehicle1.getTravelDestinations().reversed()) {
                System.out.println("--- Vehicle Status ---  ");
                System.out.println("Vehicle Location: " +  vehicle1.getCurrent_location().get_node_value());

                System.out.println("--- Package Status ---  ");
                for (Package package_package : vehicle1.get_deliveryPackages()) {
                    System.out.println(package_package.getItem_Name());
                    System.out.println(package_package.getDestination());

                }

                displayGraph.visualise_vehicle(vertex, 1);
                vehicle1.travel(vertex);
                Thread.sleep(1500);
                displayGraph.visualise_vehicle(vertex, 0);



            }

            for (Vehicle vehicle : list_of_vehicles) {
                if (vehicle.getCurrent_location() instanceof DeliveryHub) {
                    DeliveryHub currentDeliveryHub = (DeliveryHub) vehicle.getCurrent_location();
                    for (Package package_package : currentDeliveryHub.getPackages()) {
                        currentDeliveryHub.removePackages(package_package);
                        vehicle.get_deliveryPackages().add(package_package);
                        System.out.println("I have picked up a package from DeliveryHub:" + vehicle.getCurrent_location().get_node_value() );
                    }
                }

                if (vehicle.getCurrent_location() instanceof CustomerLocation) {
                    vehicle1.get_deliveryPackages().clear();
                    System.out.println("I have dropped up a package from CustomerLocation:" + vehicle.getCurrent_location().get_node_value()) ;

                }
            }
            vehicle1.getTravelDestinations().clear();

        }


    }

}