package org.logistics.model;

import java.util.*;

public class Vehicle {
    private PriorityQueue<Package> deliveryPackages = new PriorityQueue<>(Comparator.comparingInt(Package::getPriority));
    private Stack<Vertex> travelDestination = new Stack<>();
    private Vertex current_vertex;

    public Vehicle(Vertex start_vertex) {
        this.current_vertex = start_vertex;
    }

    public Vertex getCurrent_vertex() {
        return current_vertex;
    }

    public void setCurrent_vertex(Vertex new_location) {
        this.current_vertex = new_location;
    }

    /**
     * Method to retrieve Packages from the Vehicle
     * @return PriorityQueue | Priority Queue with Packages
     */
    public PriorityQueue<Package> get_deliveryPackages() {
        return deliveryPackages;
    }

    /**
     * Method to Add Packages to the Vehicle delivery job. Max is 2 packages at a time
     * @param package_Delivery Package | Package to add to the Deliver Job
     */
    public void add_deliveryPackage(Package package_Delivery){
        if (deliveryPackages.size() >= 2) {
            System.out.println("Vehicle is full, unable to add more packages");
        } else {
            deliveryPackages.add(package_Delivery);
        }
    }

    /**
     * Method responsible for removing packages from the vehicle
     * @param package_delivery Package | Package to be removed from the vehicle
     */
    public void remove_deliveryPackage(Package package_delivery) {
        if (deliveryPackages.contains(package_delivery)) {
            deliveryPackages.remove(package_delivery);
        } else {
            System.out.println("Package: " + package_delivery.getItem_Name() + " Does not exist");
        }
    }

    /**
     * Method responsible for adding vertexes for vehicle to travel to
     * @param vertex Vertex | Vertex for the Vehicle to travel to
     */
    public void addTravelDestination(Vertex vertex) {

    }

    /**
     * Method responsible for ensuring Vehicle travels to it's specified Vertexes
     */
    public void travel() {

    }



}
