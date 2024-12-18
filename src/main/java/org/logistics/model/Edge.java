package org.logistics.model;


public class Edge {
    // Nodes
    private Vertex start_vertex;
    private Vertex connecting_vertex;

    // Weights
    private int distance_weight;
//    private int traffic_weight;
//    private int time_weight;

    public Edge(Vertex start_vertex, Vertex connecting_vertex, int distance_weight) {
        this.start_vertex = start_vertex;
        this.connecting_vertex = connecting_vertex;
        this.distance_weight = distance_weight;
//        this.traffic_weight = traffic_weight;
//        this.time_weight = time_weight;
    }

    public Vertex getStart_node() {
        return start_vertex;
    }

    public void setStart_vertex(Vertex new_start_vertex) {
        this.start_vertex = new_start_vertex;
    }

    public Vertex getConnecting_node() {
        return connecting_vertex;
    }

    public void setConnecting_vertex(Vertex new_connecting_vertex) {
        this.connecting_vertex = new_connecting_vertex;
    }

//    public int getTime_weight() {
//        return time_weight;
//    }
//
//    public void setTime_weight(int time_weight) {
//        this.time_weight = time_weight;
//    }
//
//    public int getTraffic_weight() {
//        return traffic_weight;
//    }
//
//    public void setTraffic_weight(int traffic_weight) {
//        this.traffic_weight = traffic_weight;
//    }

    public int getDistance_weight() {
        return distance_weight;
    }

    public void setDistance_weight(int distance_weight) {
        this.distance_weight = distance_weight;
    }

//    @Override
//    public String toString() {
//        return "{" + "Start Node: " +  this.start_vertex + ", " + "End Node: " + this.end_vertex + ", " + "distance: " + this.distance_weight + ", " + "traffic: " + this.traffic_weight + ", " + "time: " + this.time_weight + "}";
//    }
}
