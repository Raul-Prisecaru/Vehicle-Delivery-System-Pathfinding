package org.logistics;

import org.logistics.model.*;

public class Main {
    public static void main(String[] args) {
        // Creating Graph Environment
        Graph graph = new Graph();
        Vehicle vehicle = new Vehicle();


        // Creating DeliveryHub Nodes
        DeliveryHub deliveryHubA = new DeliveryHub(1);
        DeliveryHub deliveryHubB = new DeliveryHub(3);

        // Creating CustomerLocation Nodes
        CustomerLocation customerLocationC = new CustomerLocation(2);
        CustomerLocation customerLocationD = new CustomerLocation(4);
        CustomerLocation customerLocationE = new CustomerLocation(5);



        // Creating Packages
        // Package newPackage = new Package("Phone", customerLocation1, 5);

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

        graph.print_List();
    }

}