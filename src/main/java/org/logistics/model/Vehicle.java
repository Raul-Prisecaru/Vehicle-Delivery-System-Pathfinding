package org.logistics.model;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Vehicle {
    private PriorityQueue<Package> deliveryPackages = new PriorityQueue<>(Comparator.comparingInt(Package::getPriority));

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



}
