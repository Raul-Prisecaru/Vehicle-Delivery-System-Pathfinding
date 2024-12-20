package org.logistics.model;

import java.util.ArrayList;

public class Vertex<T> {
    private T value;
    private ArrayList<Vehicle> storedVehicles = new ArrayList<>();
    private int distance = Integer.MAX_VALUE;

    public Vertex(T value){
        this.value = value;
    }

    public T getNodeValue() {
        return this.value;
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
