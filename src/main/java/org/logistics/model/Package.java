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
     * Method to configure the name of the Package
     * @param newItem_Name String | New Name for the Package
     * @return None
     */
    public void setItem_Name(String newItem_Name) {
        this.item_Name = newItem_Name;
    }

    /**
     * Method to retrieve the destination of the Package
     * @return CustomerLocation | Destination
     */
    public CustomerLocation getDestination() {
        return this.destination;
    }

    /**
     * Method to configure the destination of the package
     * @param new_Destionation CustomerLocation | Destination to delivery the package
     * @return None
     */
    public void setDestination(CustomerLocation new_Destionation) {
        this.destination = new_Destionation;
    }

    /**
     * Method to retrieve the priority of the Package
     * @return int | Priority
     */
    public int getPriority() {
        return this.priority;
    }

    /**
     * Method to configure the priority of the package
     * @param priority int | Destination to delivery the package
     * @return None
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }


}
