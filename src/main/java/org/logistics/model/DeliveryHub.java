package org.logistics.model;

import java.util.ArrayList;
import java.util.HashSet;

public class DeliveryHub extends Vertex {
    HashSet<Package> packages = new HashSet<Package>();

    public DeliveryHub(String value) {
        super(value);
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
        return "DeliveryHub: " + get_node_value();
    }


    public HashSet<Package> getPackages () {
        return packages;
    }

    public void removePackages(Package package_remove) {
        packages.remove(package_remove);

        packages.add(new Package("Phone", new CustomerLocation("E"), 0));
    }
}
