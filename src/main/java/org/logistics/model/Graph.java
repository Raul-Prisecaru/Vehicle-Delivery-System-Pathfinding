package org.logistics.model;

import java.util.*;

public class Graph {

    private HashMap<Vertex, LinkedList<Edge>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    /**
     * Method responsible for adding Vertexes to the Hashmap
     * @param start_vertex Vertex to add to the Hashmap
     * @return None if successful, Error message otherwise
     */
    public void add_vertex(Vertex start_vertex) {

        // Adds to the hashmap if start_node does not exist
        if (adjacencyList.get(start_vertex) == null) {
            adjacencyList.put(start_vertex, new LinkedList<>());
        } else {
            // TODO: Change this to Throw instead Later On
            System.out.println("Node: " + start_vertex.get_node_value() + " Already Exists, Cannot be Added");
        }

    }

    /**
     * Method responsible for creating edges between two vertexes with weights
     * @param start_vertex Starting Vertex
     * @param connecting_node Vertex to be connected to by Starting Vertex
     * @param distance_weight Distance between Start and Connecting Vertexes
     * @return None if successful, Error message otherwise
     */
    public void add_directed_edge(Vertex start_vertex, Vertex connecting_node, int distance_weight) {
        Vertex exist_start = this.findVertex(start_vertex);
        Vertex exist_connecting = this.findVertex(connecting_node);

        if (exist_start == null) {
            System.out.println("Vertex: " + start_vertex.get_node_value() + " Does not exist");
            return;
        }

        if (exist_connecting == null) {
            System.out.println("Vertex: " + connecting_node.get_node_value() + " Does not exist");
            return;

        }
        Edge vertex_edge = new Edge(start_vertex, connecting_node, distance_weight);


        adjacencyList.get(exist_start).add(vertex_edge);


    }

    /**
     * Method responsible for displaying the current adjacencyList
     * @return Output with current vertex and edges information
     */
    public void print_List() {
        for (Vertex vertex : adjacencyList.keySet()) {
            System.out.println("------------");
            System.out.println("Start Node: " + vertex.get_node_value());

            for (Edge edge : adjacencyList.get(vertex)) {
                System.out.println("---");
                System.out.println("Edge Distance: " + edge.getDistance_weight());
                System.out.println("Connecting Node:" + edge.getConnecting_node());
            }
            System.out.println("------------");
        }
    }

    public HashMap<Vertex, LinkedList<Edge>> getAdjacencyList() {
        return adjacencyList;
    }

    public Vertex findVertex(Vertex vertex) {
        // TODO: Improve this to search for both so we don't have to reloop each time
        System.out.println(vertex.get_node_value());
        for (Vertex v : adjacencyList.keySet()) {
            if (Objects.equals(v.get_node_value(), vertex.get_node_value())) {
                return v;
            }
        }
        return null;
    }

//    public Boolean findVertex(Vertex vertex1, Vertex vertex2) {
//        // TODO: Improve this to search for both so we don't have to reloop each time
//        for (Vertex v : adjacencyList.keySet()) {
//            if (v.get_node_value() == vertex1.get_node_value()) {
//                return true;
//            }
//        }
//        return false;
//    }

}


