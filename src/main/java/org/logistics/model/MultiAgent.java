package org.logistics.model;

import java.util.ArrayList;
import java.util.HashSet;


public class MultiAgent {
    private Graph graph;

    public MultiAgent(Graph graph) {
        this.graph = graph;
    }

    public HashSet<CustomerLocation<String>> findMultipleVehiclesInCustomerLocations() {
        HashSet<CustomerLocation<String>> multiCustomerLocations = new HashSet<>();
        for (CustomerLocation<String> customerLocation : graph.getAllCustomerLocation()) {

            if (customerLocation.getStoredVehicles().size() >= 2) {
                multiCustomerLocations.add(customerLocation);
            }
        }

        return multiCustomerLocations;
    }

    /**
     * Method Responsible for allowing the Vehicle to communicate and figure out if
     * one of the packages from the vehicle1 is closer to vehicle2 path and vice versa
     */
    public void vehicleCommunicate(HashSet<CustomerLocation<String>> customerLocationHashSet) throws Exception {
        ArrayList<Vehicle> vehicleList = new ArrayList<>();

        for (CustomerLocation<String> customerLocation : customerLocationHashSet) {
            vehicleList.clear();

            if (customerLocation.getStoredVehicles().size() < 2) {
                return;
            }

            for (Vehicle vehicle : customerLocation.getStoredVehicles()) {
                vehicleList.add(vehicle);
            }

            for (int i = 0; i < vehicleList.size(); i++) {
                Vehicle vehicle1 = vehicleList.get(i);

                for (int j = i + 1; j < vehicleList.size(); j++) {
                    Vehicle vehicle2 = vehicleList.get(j);
                }
            }
        }
    }

    private void optimisePackages(Vehicle vehicle1, Vehicle vehicle2) throws Exception {
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

}
