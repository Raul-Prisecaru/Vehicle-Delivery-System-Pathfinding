package org.logistics.model;

import java.util.*;

public class Vehicle {
    private PriorityQueue<Package> deliveryPackages = new PriorityQueue<>(Comparator.comparingInt(Package::getPriority));
    private Stack<Vertex<String>> travelDestination = new Stack<>();
    private Vertex<String > current_location;

    public Vehicle(Vertex<String> start_vertex) {
        this.current_location = start_vertex;
    }

    /**
     * Method Responsible for getting the current position of the vehicle
     * @return Vertex - current vertex of the vehicle
     */
    public Vertex<String> getCurrent_location() {
        return current_location;
    }

    /**
     * Method Responsible for setting the current position of the Vehicle
     * @param new_location
     */
    public void setCurrent_location(Vertex<String> new_location) {
        this.current_location = new_location;
    }

    /**
     * Method Responsible for returning PriorityQueue of Packages
     * @return PriorityQueue - PriorityQueue of Packages
     */
    public PriorityQueue<Package> get_deliveryPackages() {
        return deliveryPackages;
    }

    /**
     * Method Responsible for adding packages to the Vehicle
     * @param package_Delivery (Package) - Package to add to the vehicle
     */
    public void add_deliveryPackage(Package package_Delivery) throws Exception {
        if (deliveryPackages.size() >= 2) {
            throw new Exception("Vehicle is full, unable to add more packages");
        } else {
            deliveryPackages.add(package_Delivery);
        }
    }

    /**
     * Method Responsible for removing packages from the vehicle
     * @param package_delivery (Package) - Package to be removed from the vehicle
     */
    public void remove_deliveryPackage(Package package_delivery) {
        if (deliveryPackages.contains(package_delivery)) {
            deliveryPackages.remove(package_delivery);
        } else {
            System.out.println("Package: " + package_delivery.getItem_Name() + " Does not exist");
        }
    }

    /**
     * Method Responsible for adding Vertexes to vehicle destination
     * @param vertex (Vertex) - Vertex to add to vehicle's destination
     */
    public void addTravelDestination(Vertex<String> vertex) {
        travelDestination.add(vertex);
    }

    /**
     * Method Responsible for returning Vehicle's travelDestination
     * @return Stack - Travel Destination
     */
    public Stack<Vertex<String>> getTravelDestinations() {
        return this.travelDestination;
    }

    /**
     * Method responsible for ensuring Vehicle travels to it's specified Vertexes
     * @param travel_vertex (Vertex) - Vertex for vehicle to travel to
     */
    public void travel(Vertex<String> travel_vertex) {
            travel_vertex.addStoredVehicles(this);
            this.setCurrent_location(travel_vertex);
            ;
    }

    /**
     * Method Responsible for finding the number of travel steps remaining to a specified Location
     * @param specifiedLocation (Vertex) - vertex to find number of steps of
     */
    public int findTravelSteps(Vertex<String> specifiedLocation) {
        int count = 0;

        for (Vertex<String> vertex : getTravelDestinations()) {
            if (vertex != specifiedLocation) {
                count++;
            }

            if (vertex == specifiedLocation) {
                count++;
                return count;
            }
        }
        return -1;
    }



}
