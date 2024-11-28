package org.logistics.model;


public class Edge {
    // Nodes
    private Node start_node;
    private Node end_node;

    // Weights
    private int distance_weight;
    private int traffic_weight;
    private int time_weight;

    public Edge(Node start_node, Node end_node, int distance_weight, int traffic_weight, int time_weight) {
        this.start_node = start_node;
        this.end_node = end_node;
        this.distance_weight = distance_weight;
        this.traffic_weight = traffic_weight;
        this.time_weight = time_weight;
    }

    public Node getStart_node() {
        return start_node;
    }

    public Node getEnd_node() {
        return end_node;
    }

    public int getTime_weight() {
        return time_weight;
    }

    public void setTime_weight(int time_weight) {
        this.time_weight = time_weight;
    }

    public int getTraffic_weight() {
        return traffic_weight;
    }

    public void setTraffic_weight(int traffic_weight) {
        this.traffic_weight = traffic_weight;
    }

    public int getDistance_weight() {
        return distance_weight;
    }

    public void setDistance_weight(int distance_weight) {
        this.distance_weight = distance_weight;
    }






}
