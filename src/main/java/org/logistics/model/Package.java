package org.logistics.model;

public class Package {
    private String item_Name;
    private CustomerLocation destination;

    public Package(String item_Name, CustomerLocation destination) {
        this.item_Name = item_Name;
        this.destination = destination;
    }


    public String getItem_Name() {
        return this.item_Name;
    }

    public CustomerLocation getDestination() {
        return this.destination;
    }

}
