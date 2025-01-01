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

}
