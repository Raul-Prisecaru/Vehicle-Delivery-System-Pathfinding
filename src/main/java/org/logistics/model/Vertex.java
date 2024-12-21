package org.logistics.model;

import java.util.ArrayList;

public class Vertex<T> {
    private T value;
    private ArrayList<Vehicle> storedVehicles = new ArrayList<>();
    private int distance = Integer.MAX_VALUE;

    public Vertex(T value){
        this.value = value;
    }

    /**
     * Method Responsible for returning the Vertex Value
     * @return T - Vertex Value
     */
    public T getNodeValue() {
        return this.value;
    }

    /**
     * Method Responsible for returning the vertex distance
     * @return int - distance of the vertex
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Method Responsible for overriding the vertex distance
     * @param new_distance (int) - new distance of the vertex
     */
    public void setDistance(int new_distance) {
        this.distance = new_distance;
    }

    /**
     * Method Responsible for returning the ArrayList containing vehicle at the Vertex
     * @return ArrayList<Vehicle> - ArrayList of vehicle at the Vertex
     */
    public ArrayList<Vehicle> getStoredVehicles() {
        return this.storedVehicles;
    }

    /**
     * Method Responsible for adding vehicle to the arrayList of the vertex
     * @param vehicle (Vehicle) - Vehicle to add to the Vertex
     */
    public void addStoredVehicles(Vehicle vehicle) {
        this.storedVehicles.add(vehicle);
    }
}
