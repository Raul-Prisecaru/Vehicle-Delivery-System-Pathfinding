package org.logistics.model;

import java.util.HashMap;
import java.util.LinkedList;

public class bellman_ford_customerLocation {
    private Graph graph;
    private HashMap<Vertex<String>, LinkedList<Edge>> adjacencyList;

    /**
     * Constructor
     * @param graph - Graph Environment
     */
    public bellman_ford_customerLocation(Graph graph) {
        this.graph = graph;
        this.adjacencyList = graph.getAdjacencyList();
    }

    /**
     * Method Responsible for updating the adjacencyList
     */
    public void update_adjacencyList() {
        this.adjacencyList = graph.getAdjacencyList();
    }

    /**
     * Method Responsible for finding the shortest path to the highest priority package in the vehicle
     * @param vehicle - Vehicle to find shortest path for
     */
    public void find_shortest_customer(Vehicle vehicle) {

    }
}
