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
        Edge vertex_edge = new Edge(start_vertex, connecting_node, distance_weight);

         if (adjacencyList.get(vertex_edge.getStart_node()) != null) {
            adjacencyList.get(vertex_edge.getStart_node()).add(vertex_edge);
        } else {
             // TODO: Change this to Throw instead Later on
             System.out.println("Node:" + start_vertex.get_node_value() + " Does Not Exist");
         }
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

    /**
     * Method responsible for finding the shortest distance between two vertexes
     * @param vehicle Vehicle that should find the shortest path
     */
    public void find_shortest_path(Vertex start_vertex) {
        start_vertex.set_distance(0);

        PriorityQueue<Vertex> unvisited = new PriorityQueue<>(Comparator.comparingInt(Vertex::get_distance));
        Queue<Vertex> visited = new LinkedList<>();

        while (!unvisited.isEmpty()) {

            Vertex current = unvisited.poll();

            if (!visited.contains(current)) {
                for (Edge edge : adjacencyList.get(current)) {
                    int totalDistance = current.get_distance() + edge.getDistance_weight();

                    if (totalDistance < edge.getConnecting_node().get_distance()) {
                        edge.getConnecting_node().set_distance(totalDistance);
                        unvisited.remove(edge.getConnecting_node());
                        unvisited.add(edge.getConnecting_node());
                    }

                    unvisited.remove(current);
                    visited.add(current);
                }

            }
        }
        for (Vertex vertex : visited) {
            System.out.println("Vertex: " + vertex.get_node_value());
            System.out.println("Distance: " + vertex.get_distance());
        }

    }
}


