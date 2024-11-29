package org.logistics.model;

public class Package {
    private String item_Name;
    private CustomerLocation destination;
    private int priority;


    public Package(String item_Name, CustomerLocation destination, int priority) {
        this.item_Name = item_Name;
        this.destination = destination;
        this.priority = priority;
    }

    public String getItem_Name() {
        return this.item_Name;
    }

    public void setItem_Name(String newItem_Name) {
        this.item_Name = newItem_Name;
    }

    public CustomerLocation getDestination() {
        return this.destination;
    }

    public void setDestination(CustomerLocation new_Destionation) {
        this.destination = new_Destionation;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


}
