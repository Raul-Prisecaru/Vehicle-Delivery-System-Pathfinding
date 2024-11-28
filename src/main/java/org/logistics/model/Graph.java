package org.logistics.model;

import java.util.HashMap;
import java.util.LinkedList;

public class Graph {

    private HashMap<Node, LinkedList<Edge>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    /**
     * Method to add Vertex into the Adjacency List
     * @param start_Node Starting Node to attempt at inserting into the Adjacency List
     * @return Returns None if successful else returns an error message
     */
    public void add_Vertex(Node start_Node) {

        // Adds to the hashmap if start_node does not exist
        if (adjacencyList.get(start_Node) == null) {
            adjacencyList.put(start_Node, new LinkedList<>());
        } else {
            // TODO: Change this to Throw instead Later On
            System.out.println("Node: " + start_Node.get_node_value() + " Already Exists, Cannot be Added");
        }

    }

    /**
     * Method to Create Edges between Nodes for Adjacency List
     * @param start_Node Starting Node to attempt to connect the edge to
     * @param end_Node Ending Node to attempt to connect the edge to
     * @return Returns None if successful else returns an error message
     */
    public void add_Edge(Edge edge) {
        if (adjacencyList.get(edge.getStart_node()) != null) {
            adjacencyList.get(edge.getStart_node()).add(edge);
        } else {
            // TODO: Change this to Throw instead Later on
            System.out.println(edge.getStart_node() + " Does not exist.");
            System.out.println("You might want to look into creating the Node First");
            
        }
    }
    /**
     * Method to display the adjacency List
     * @return Returns a message with all the Key Pair values in the adjacency List
     */
    public void print_List() {
        for (Node i : adjacencyList.keySet()) {
            System.out.println("key: " + i + " value: "+ adjacencyList.get(i));
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        DeliveryHub deliveryHub1 = new DeliveryHub(1);
        DeliveryHub deliveryHub2 = new DeliveryHub(2);

        CustomerLocation customerLocation1 = new CustomerLocation(1);
        CustomerLocation customerLocation2 = new CustomerLocation(2);

        graph.add_Vertex(deliveryHub1);
        graph.add_Vertex(deliveryHub2);

        graph.add_Vertex(customerLocation1);
        graph.add_Vertex(customerLocation2);

        // graph.add_Edge(deliveryHub1, customerLocation1);
        // graph.add_Edge(deliveryHub1, deliveryHub2);

        // graph.add_Edge(deliveryHub2, customerLocation2);
        // graph.add_Edge(deliveryHub2, deliveryHub1);
        graph.print_List();
    }

}


