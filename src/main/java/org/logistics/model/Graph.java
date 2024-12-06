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

    /**
     * Method responsible for finding shortest path between two paths
     * @param vehicle used to retrieve the package and find the shortest path
     */
    public void findShortestPath(Vehicle vehicle) {

    }

}


