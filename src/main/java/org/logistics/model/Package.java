package org.logistics.model;

public class Package {
    private String item_Name;
    private CustomerLocation<String> destination;
    private int priority;

    /**
     * Constructor to setting up a package
     * @param item_Name (String) - item name
     * @param destination (CustomerLocation) - Destination of the package
     * @param priority (int) -
     */
    public Package(String item_Name, CustomerLocation<String> destination, int priority) {
        this.item_Name = item_Name;
        this.destination = destination;
        this.priority = priority;
    }

    /**
     * Method Responsible for returning the item name
     * @return String - Item name
     */
    public String getItem_Name() {
        return this.item_Name;
    }

    /**
     * Method Responsible for returning the customerLocation of the package
     * @return CustomerLocation - Destination of the package
     */
    public CustomerLocation<String> getDestination() {
        return this.destination;
    }

    /**
     * Method Responsible for returning the priority of the package
     * @return int - Priority of the package
     */
    public int getPriority() {
        return this.priority;
    }


}
