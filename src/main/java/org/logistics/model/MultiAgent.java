package org.logistics.model;

public class MultiAgent {
    private Graph graph;

    public MultiAgent(Graph graph) {
        this.graph = graph;
    }

    // TODO: For time being this will do but shorten the method name
    public CustomerLocation<String> findMultipleVehiclesInCustomerLocations() {

        for (CustomerLocation<String> customerLocation : graph.getAllCustomerLocation()) {

            if (customerLocation.getStoredVehicles().size() >= 2) {

                // TODO: Make this return multiple
                return customerLocation;
            }
        }

        return null;
    }

    // TODO: Probably improve description
    /**
     * Method Responsible for allowing the Vehicle to communicate and figure out if
     * one of the packages from the vehicle1 is closer to vehicle2 path and vice versa
     */
    public void vehicleCommunicate() {

    }

}
