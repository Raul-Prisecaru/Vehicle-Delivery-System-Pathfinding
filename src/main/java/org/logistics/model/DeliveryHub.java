package org.logistics.model;

import java.util.ArrayList;
import java.util.HashSet;

public class DeliveryHub extends Vertex {
    HashSet<Package> packages = new HashSet<Package>();

    public DeliveryHub(String value) {
        super(value);
    }

    public String getNodeValue() {
        return super.getNodeValue();
    }

    public int getDistance() {
        return super.getDistance();
    }

    public void setDistance(int new_distance) {
        super.setDistance(new_distance);
    }

    public ArrayList<Vehicle> getStoredVehicles() {
        return super.getStoredVehicles();
    }

    public void addStoredVehicles(Vehicle vehicle) {
        super.addStoredVehicles(vehicle);
    }

    @Override
    public String toString() {
        return "DeliveryHub: " + getNodeValue();
    }


    public HashSet<Package> getPackages () {
        return packages;
    }

    public void addPackage(String itemName, CustomerLocation deliveryLocation, int priority) {
        this.packages.add(new Package(itemName, deliveryLocation, priority));
    }

    public void removePackages(Package package_remove) {
        this.packages.remove(package_remove);

        packages.add(new Package("Phone", new CustomerLocation("E"), 0));
    }
}
