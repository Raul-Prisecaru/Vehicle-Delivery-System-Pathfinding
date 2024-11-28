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

        if (adjacencyList.get(start_Node) == null) {
            adjacencyList.put(start_Node, new LinkedList<>());
        } else {
            // TODO: Change this to Throw instead
            System.out.println("Node: " + start_Node.get_node_value() + " Already Exists, Cannot be Added");
        }

    }

    public void add_Edge(){

    }

    public void print_List() {

    }

}
