package org.logistics.model;

import java.util.ArrayList;
import java.util.HashSet;

public class CustomerLocation<T> extends Vertex<T> {
    HashSet<Package> collected_packages = new HashSet<Package>();

    /**
     * Constructor responsible for setting the CustomerLocation with a Vertex Value
     * @param value (T) - Value of the Vertex
     */
    public CustomerLocation(T value) {
        super(value);
    }

    /**
     * Method responsible for returning all the packages the CustomerLocation contains
     * @return HashSet<Package> - Packages that the CustomerLocation Contains
     */
    public HashSet<Package> getCollectedPackages() {
        return collected_packages;
    }

    /**
     * Method Responsible for adding a package to the CustomerLocation
     * @param itemName (String) - Item name
     * @param deliveryLocation (CustomerLocation) - DeliveryLocation
     * @param priority (int) - Priority of the Package
     */
    public void addCollectedPackage(String itemName, CustomerLocation<String> deliveryLocation, int priority) {
        this.collected_packages.add(new Package(itemName, deliveryLocation, priority));
    }

    /**
     * Method Responsible for returning the Vertex value of the CustomerLocation
     * @return T - CustomerLocation Value
     */
    public T getNodeValue() {
        return super.getNodeValue();
    }

    /**
     * Method Responsible for returning the distance of the CustomerLocation
     * @return int - Distance of the Vertex
     */
    public int getDistance() {
        return super.getDistance();
    }

    /**
     * Method Responsible for overriding the distance of the CustomerLocation
     * @param new_distance (int) - new distance of the Vertex
     */
    public void setDistance(int new_distance) {
        super.setDistance(new_distance);
    }

    /**
     * Method Responsible for returning the vehicles currently at the CustomerLocation
     * @return ArrayList<Vehicle> - ArrayList containing vehicles
     */
    public ArrayList<Vehicle> getStoredVehicles() {
        return super.getStoredVehicles();
    }

    /**
     * Method Responsible for adding vehicles to the ArrayList
     * @param vehicle (Vehicle) - Vehicle to add to the ArrayList
     */
    public void addStoredVehicles(Vehicle vehicle) {
        super.addStoredVehicles(vehicle);
    }

    /**
     * Method Responsible for overriding the format of the output
     * @return "CustomerLocation: " + {Vertex Value}
     */
    @Override
    public String toString() {
        return "CustomerLocation: " + getNodeValue();
    }


}
