package org.logistics.model;


public class Vehicle {
    // TODO: Convert Queue to a Priority Queue Later On
    private Package delivery_Packages;
    private


    public Vehicle(Package package_delivery) {
        delivery_Packages = new Queue<Package>();

        delivery_Packages.add(package_delivery);
    }
}
