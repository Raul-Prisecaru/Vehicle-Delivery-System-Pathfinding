package org.logistics;

import org.logistics.model.CustomerLocation;
import org.logistics.model.DeliveryHub;
import org.logistics.model.Edge;
import org.logistics.model.Graph;

public class Main {
    public static void main(String[] args) {
        // Creating Graph Environment
        Graph graph = new Graph();

        // Creating DeliveryHub Nodes
        DeliveryHub deliveryHub1 = new DeliveryHub(1);
        DeliveryHub deliveryHub2 = new DeliveryHub(2);

        // Creating CustomerLocation Nodes
        CustomerLocation customerLocation1 = new CustomerLocation(1);
        CustomerLocation customerLocation2 = new CustomerLocation(2);

        // Creating Edges
        Edge edge1 = new Edge(deliveryHub1, customerLocation1, 4, 2, 5);
        Edge edge2 = new Edge(deliveryHub1, deliveryHub2, 5, 3, 9);

        Edge edge3 = new Edge(deliveryHub2, customerLocation2, 5, 2, 5);
        Edge edge4 = new Edge(deliveryHub2, deliveryHub1, 2, 6, 1);

        // Adding Vertexs to the Hashmap
        graph.add_Vertex(deliveryHub1);
        graph.add_Vertex(deliveryHub2);

        graph.add_Vertex(customerLocation1);
        graph.add_Vertex(customerLocation2);

        // Adding Edges to the hashmap
        graph.add_Edge(edge1);
        graph.add_Edge(edge2);
        graph.add_Edge(edge3);
        graph.add_Edge(edge4);

        graph.print_List();
    }

}