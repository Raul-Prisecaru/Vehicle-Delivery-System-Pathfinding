package org.logistics;

import org.logistics.model.*;
import org.logistics.model.Package;
import org.logistics.view.Display;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
        Package phonePackage = new Package("Iphone", customerLocationE, 1);
        vehicle1.add_deliveryPackage(phonePackage);

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
        displayGraph.displayGraph();



    }

}