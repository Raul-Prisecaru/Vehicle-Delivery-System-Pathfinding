package org.logistics.model;

import java.util.ArrayList;

public class Vertex {
    private String value;
    private ArrayList<Vehicle> storedVehicles = new ArrayList<>();
    private int distance = Integer.MAX_VALUE;

    public Vertex(String value){
        this.value = value;
    }

    public String getNodeValue() {
        return this.value;
    }

    public void setNodeValue(String new_value) {
        this.value = new_value;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int new_distance) {
        this.distance = new_distance;
    }

    public ArrayList<Vehicle> getStoredVehicles() {
        return this.storedVehicles;
    }

    public void addStoredVehicles(Vehicle vehicle) {
        this.storedVehicles.add(vehicle);
    }
}
