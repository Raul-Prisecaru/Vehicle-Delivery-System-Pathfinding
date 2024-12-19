package org.logistics.model;

import java.util.ArrayList;
import java.util.HashSet;

public class CustomerLocation extends Vertex {
    HashSet<Package> collected_packages = new HashSet<Package>();

    public CustomerLocation(String value) {
        super(value);
    }

    public HashSet<Package> getCollected_packages() {
        return collected_packages;
    }

    public String get_node_value() {
        return super.get_node_value();
    }

    public void set_node_value(String new_value) {
        super.set_node_value(new_value);
    }

    public int get_distance() {
        return super.get_distance();
    }

    public void set_distance(int new_distance) {
        super.set_distance(new_distance);
    }

    public ArrayList<Vehicle> getStoredVehicles() {
        return super.getStoredVehicles();
    }

    public void setStoredVehicles(Vehicle vehicle) {
        super.setStoredVehicles(vehicle);
    }

    @Override
    public String toString() {
        return "CustomerLocation: " + get_node_value();
    }


}
