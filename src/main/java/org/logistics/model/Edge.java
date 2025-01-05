package org.logistics.model;


import java.util.HashMap;

public class Edge {
    // Nodes
    private Vertex<String> start_vertex;
    private Vertex<String> connecting_vertex;
    private HashMap<Integer, Integer> congestionHistory = new HashMap<>();

    // Weights
    private int distance_weight;
    private int congestion_weight;
    private int time_weight;

    /**
     * Constructor to set the values of an edge
     * @param start_vertex (Vertex) - Starting vertex to connect from
     * @param connecting_vertex (Vertex) - Connecting Vertex to connect to
     * @param distance_weight (int) - Distance weight between Starting and Connecting vertexes
     */
    public Edge(Vertex<String> start_vertex, Vertex<String> connecting_vertex, int distance_weight) {
        this.start_vertex = start_vertex;
        this.connecting_vertex = connecting_vertex;
        this.distance_weight = distance_weight;

        this.time_weight = this.congestion_weight + this.distance_weight;
        this.congestionHistory.put(time_weight, this.congestionHistory.get(time_weight) + 1);
    }

    /**
     * Method Responsible for returning the starting vertex of the edge
     * @return Vertex - Starting Vertex of an Edge
     */
    public Vertex<String> getStart_node() {
        return start_vertex;
    }

    /**
     * Method Responsible for overriding the starting vertex of an edge
     * @param new_start_vertex - new start vertex
     */
    public void setStart_vertex(Vertex<String> new_start_vertex) {
        this.start_vertex = new_start_vertex;
    }

    /**
     * Method Responsible for returning the connecting vertex of the edge
     * @return Vertex - Connecting vertex of the edge
     */
    public Vertex<String> getConnecting_node() {
        return connecting_vertex;
    }

    /**
     * Method Responsible for overriding the connecting vertex of an edge
     * @param new_connecting_vertex - new connecting vertex
     */
    public void setConnecting_vertex(Vertex<String> new_connecting_vertex) {
        this.connecting_vertex = new_connecting_vertex;
    }

    public int getTime_weight() {
        return time_weight;
    }

//    public void setTime_weight(int time_weight) {
//        this.time_weight = time_weight;
//    }

    public int getCongestion_weight() {
        return congestion_weight;
    }

    public void addCongestion_weight() {
        this.congestion_weight++;
        this.time_weight = this.congestion_weight + distance_weight;

        this.congestionHistory.put(time_weight, congestionHistory.get(time_weight) + 1);
    }

    public void removeCongestion_weight() {
        this.congestion_weight--;
        this.time_weight = this.congestion_weight + distance_weight;

        this.congestionHistory.put(time_weight, congestionHistory.get(time_weight) + 1);

    }

    /**
     * Method responsible for returning the distance weight of the edge
     * @return int - distance weight
     */
    public int getDistance_weight() {
        return distance_weight;
    }

    /**
     * Method responsible for overriding the distance weight of the edge
     * @param distance_weight - new distance weight
     */
    public void setDistance_weight(int distance_weight) {
        this.distance_weight = distance_weight;
    }

    public HashMap<Integer, Integer> getCongestionHistory() {
        return congestionHistory;
    }

//    @Override
//    public String toString() {
//        return "{" + "Start Node: " +  this.start_vertex + ", " + "End Node: " + this.end_vertex + ", " + "distance: " + this.distance_weight + ", " + "traffic: " + this.traffic_weight + ", " + "time: " + this.time_weight + "}";
//    }
}
