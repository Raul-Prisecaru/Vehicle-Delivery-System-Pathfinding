package org.logistics.model;


public class Vertex {
    private int value;
    private int distance = Integer.MAX_VALUE;

    public Vertex(int value){
        this.value = value;
    }

    public int get_node_value() {
        return this.value;
    }

    public void set_node_value(int new_value) {
        this.value = new_value;
    }

    public int get_distance() {
        return distance;
    }

    public void set_distance(int new_distance) {
        this.distance = new_distance;
    }

    
}
