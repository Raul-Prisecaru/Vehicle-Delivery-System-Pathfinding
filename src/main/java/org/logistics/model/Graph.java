package org.logistics.model;

import java.util.*;

public class Graph {

    private HashMap<Vertex, LinkedList<Edge>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    /**
     * Method to add Vertex into the Adjacency List
     * @param start_Vertex Starting Node to attempt at inserting into the Adjacency List
     * @return Returns None if successful else returns an error message
     */
    public void add_Vertex(Vertex start_vertex) {

        // Adds to the hashmap if start_node does not exist
        if (adjacencyList.get(start_vertex) == null) {
            adjacencyList.put(start_vertex, new LinkedList<>());
        } else {
            // TODO: Change this to Throw instead Later On
            System.out.println("Node: " + start_vertex.get_node_value() + " Already Exists, Cannot be Added");
        }

    }

    /**
     * Method to Create Edges between Nodes for Adjacency List
     * @param edge Used to add an edge to the starting node
     * @return Returns None if successful else returns an error message
     */
    public void add_Edge(Vertex start_vertex, Vertex connecting_node, int distance_weight) {
        Edge vertex1_edge = new Edge(start_vertex, connecting_node, distance_weight);

         if (adjacencyList.get(vertex1_edge.getStart_node()) != null) {
            adjacencyList.get(vertex1_edge.getStart_node()).add(vertex1_edge);
        } else {
             // TODO: Change this to Throw instead Later on
             System.out.println("Node:" + start_vertex.get_node_value() + " Does Not Exist");
         }
    }

    /**
     * Method to display the adjacency List
     * @return Returns a message with all the Key Pair values in the adjacency List
     */
    public void print_List() {
        for (Vertex vertex : adjacencyList.keySet()) {
            System.out.println("Start Node: " + vertex.get_node_value());

            for (Edge edge : adjacencyList.get(vertex)) {
                System.out.println("Edge Distance: " + edge.getDistance_weight());
                System.out.println("Connecting Node:" + edge.getConnecting_node());
        }
    }

    /**
     * Method responsible for finding shortest path between two paths
     * @param vehicle used to retrieve the package and find the shortest path
     */
    public void findShortestPath(Vehicle vehicle) {
        Queue<Vertex> visited = new LinkedList<>();
        PriorityQueue<Vertex> unvisited = new PriorityQueue<>(Comparator.comparingInt(Vertex::get_node_value));


    }

}


