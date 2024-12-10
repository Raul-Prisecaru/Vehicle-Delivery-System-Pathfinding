package org.logistics.model;

import java.util.ArrayList;

public class Vertex {
    private String value;
    private ArrayList<Vehicle> storedVehicles = new ArrayList<>();
    private int distance = Integer.MAX_VALUE;

    public Vertex(String value){
        this.value = value;
    }

    public String get_node_value() {
        return this.value;
    }

    public void set_node_value(String new_value) {
        this.value = new_value;
    }

    public int get_distance() {
        return distance;
    }

    public void set_distance(int new_distance) {
        this.distance = new_distance;
    }

    public ArrayList<Vehicle> getStoredVehicles() {
        return this.storedVehicles;
    }

    public void setStoredVehicles(Vehicle vehicle) {
        this.storedVehicles.add(vehicle);
    }
}
