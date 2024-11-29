package org.logistics.model;

import java.util.PriorityQueue;

public class Vehicle {
    private PriorityQueue<Package> deliveryPackages;


    public Vehicle() {
        deliveryPackages = new PriorityQueue<>();
    }


}
