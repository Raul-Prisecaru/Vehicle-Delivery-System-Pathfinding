package org.logistics;

import org.logistics.model.*;
import org.logistics.model.Package;

public class Main {
    public static void main(String[] args) {
        // Creating Graph Environment
        Graph graph = new Graph();
        Pathfinding pathfinding = new Pathfinding(graph);

        // TODO: Perhaps move these to add_vertex() method and create it there?
        // Creating DeliveryHub Nodes
        DeliveryHub deliveryHubA = new DeliveryHub("A");
        DeliveryHub deliveryHubB = new DeliveryHub("B");

        // Creating CustomerLocation Nodes
        CustomerLocation customerLocationC = new CustomerLocation("C");
        CustomerLocation customerLocationD = new CustomerLocation("D");
        CustomerLocation customerLocationE = new CustomerLocation("E");

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

        // Creating Edges
        graph.add_directed_edge(deliveryHubA, deliveryHubB, 4);
        graph.add_directed_edge(deliveryHubA, customerLocationC, 2);
        graph.add_directed_edge(deliveryHubB, customerLocationC, 1);
        graph.add_directed_edge(deliveryHubB, customerLocationD, 2);
        graph.add_directed_edge(deliveryHubB, customerLocationE, 3);
        graph.add_directed_edge(customerLocationC, customerLocationE, 1);
        graph.add_directed_edge(customerLocationE, customerLocationD, 5);

//        graph.print_List();
        pathfinding.find_shortest_path(vehicle1);
        vehicle1.travel();
    }

}