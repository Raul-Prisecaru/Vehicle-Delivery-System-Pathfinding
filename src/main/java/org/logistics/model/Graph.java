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
     * @param edge Used to add an edge to the starting node
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
        for (Node node : adjacencyList.keySet()) {
            System.out.println("Start Node: " + node + " Edge: "+ adjacencyList.get(node));
        }
    }

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


