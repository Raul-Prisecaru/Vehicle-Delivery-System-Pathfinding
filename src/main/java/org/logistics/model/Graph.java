package org.logistics.model;

import java.util.HashMap;
import java.util.LinkedList;

public class Graph {

    private HashMap<Node, LinkedList<Node>> adjacencyList;

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
            // TODO: Change this to Throw instead
            System.out.println("Node: " + start_Node.get_node_value() + " Already Exists, Cannot be Added");
        }

    }

    /**
     * Method to Create Edges between Nodes for Adjacency List
     * @param start_Node Starting Node to attempt to connect the edge to
     * @param end_Node Ending Node to attempt to connect the edge to
     * @return Returns None if successful else returns an error message
     */
    public void add_Edge(Node start_Node, Node end_Node) {
        if (adjacencyList.get(start_Node) != null) {
            adjacencyList.get(start_Node).add(end_Node);
        } else {
            // TODO: Change this to Throw instead Later on
            System.out.println("Node: " + start_Node.get_node_value() + " Does not exist.");

        }}

    public void print_List() {
        for (Node i : adjacencyList.keySet()) {
            System.out.println("key: " + i + " value: "+ adjacencyList.get(i));
        }
    }

}


