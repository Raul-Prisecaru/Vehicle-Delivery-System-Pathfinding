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

    /**
     * Method to retrieve the Name of the Package
     * @return Package name
     */
    public String getItem_Name() {
        return this.item_Name;
    }

    /**
     * Method to retrieve the destination of the Package
     * @return CustomerLocation | Destination
     */
    public CustomerLocation getDestination() {
        return this.destination;
    }

    /**
     * Method to retrieve the priority of the Package
     * @return int | Priority
     */
    public int getPriority() {
        return this.priority;
    }


}
