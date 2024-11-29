package org.logistics.model;

import java.util.PriorityQueue;

public class Vehicle {
    private PriorityQueue<Package> deliveryPackages;


    public Vehicle() {
        deliveryPackages = new PriorityQueue<>();
    }

    /**
     * Method to retrieve Packages from the Vehicle
     * @return PriorityQueue | Priority Queue with Packages
     */
    public PriorityQueue<Package> get_deliveryPackages() {

    }

    /**
     * Method to Add Packages to the Vehicle delivery job
     * @param package_Delivery Package | Package to add to the Deliver Job
     */
    public void add_deliveryPackage(Package package_Delivery){

    }



}
