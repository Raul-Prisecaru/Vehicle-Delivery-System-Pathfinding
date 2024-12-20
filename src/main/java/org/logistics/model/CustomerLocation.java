package org.logistics.model;

import java.util.ArrayList;
import java.util.HashSet;

public class CustomerLocation extends Vertex {
    HashSet<Package> collected_packages = new HashSet<Package>();

    /**
     * Constructor responsible for setting the CustomerLocation with a Vertex Value
     * @param value (String) - Value of the Vertex
     */
    public CustomerLocation(String value) {
        super(value);
    }

    /**
     * Method responsible for acting as getter to return the current packages that the CustomerLocation Contains
     * @return HashSet<Package> - Packages that the CustomerLocation Contains
     */
    public HashSet<Package> getCollectedPackages() {
        return collected_packages;
    }

    /**
     * Method Responsible for returning the Vertex value of the CustomerLocation
     * @return String - Vertex Value
     */
    public String getNodeValue() {
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
