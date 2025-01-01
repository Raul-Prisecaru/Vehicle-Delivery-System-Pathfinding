package org.logistics.model;

import java.util.ArrayList;


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
    public void vehicleCommunicate(CustomerLocation<String> customerLocation) throws Exception {
        ArrayList<Vehicle> vehicleList = new ArrayList<>();

        if (customerLocation.getStoredVehicles().size() < 2) {
            return;
        }
        for (Vehicle vehicle : customerLocation.getStoredVehicles()) {
            vehicleList.add(vehicle);
        }

        // TODO: Optimise this so it can handle more than 3 vehicles
        Vehicle vehicle1 = vehicleList.getFirst();
        Vehicle vehicle2 = vehicleList.getLast();

        if (vehicle1.get_deliveryPackages().size() < 2) {
            for (Package package_package : vehicle2.get_deliveryPackages()) {
                int vehicle1Steps = vehicle1.findTravelSteps(package_package.getDestination());
                int vehicle2Steps = vehicle2.findTravelSteps(package_package.getDestination());

                if (vehicle1Steps != -1 && vehicle2Steps != -1) {
                    if (vehicle1Steps < vehicle2Steps) {
                        vehicle2.remove_deliveryPackage(package_package);
                        vehicle1.add_deliveryPackage(package_package);
                    }
                }
            }
        }

        if (vehicle2.get_deliveryPackages().size() < 2) {
            for (Package package_package : vehicle1.get_deliveryPackages()) {
                int vehicle1Steps = vehicle1.findTravelSteps(package_package.getDestination());
                int vehicle2Steps = vehicle2.findTravelSteps(package_package.getDestination());

                if (vehicle1Steps != -1 && vehicle2Steps != -1) {
                    if (vehicle2Steps < vehicle1Steps) {
                        vehicle1.remove_deliveryPackage(package_package);
                        vehicle2.add_deliveryPackage(package_package);
                    }
                }
            }
        }
    }

}
