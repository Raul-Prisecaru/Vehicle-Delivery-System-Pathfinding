package org.logistics.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class vehicleLogging {

    HashMap<Vehicle, LinkedList<String>> vehicleLogs;

    public vehicleLogging() {
        vehicleLogs = new HashMap<>();
    }

    /**
     * Method responsible for adding Vehicle log to the HashSet.
     * It follows this format:
     * Current Location -> Travel Destinations -> Packages -> Status
     * @param vehicle (Vehicle) - Vehicle to add logs to
     * @param statusLog (String) - Status Message
     */
    public void addVehicleLog(Vehicle vehicle, String statusLog)  {
        LinkedList<String> linkedList = new LinkedList<String>();

        if (vehicleLogs.get(vehicle) == null) {
            vehicleLogs.put(vehicle, new LinkedList<>());
        }

        linkedList.add("Current Location: " + vehicle.getCurrent_location().getNodeValue());
        linkedList.add("Travel Destination: " + vehicle.getTravelDestinations());
        linkedList.add("Package: " + vehicle.get_deliveryPackages());
        linkedList.add("Status:" + statusLog);
        vehicleLogs.put(vehicle, linkedList);


    }

    /**
     * Method Responsible for returning the specified vehicle from hashset
     * @param vehicle (Vehicle) - vehicle to get logs for
     * @return linkedlist of (Current Location -> Travel Destinations -> Packages -> Status)
     */
    public LinkedList<String> getVehicleLog(Vehicle vehicle) {

        if (vehicleLogs.get(vehicle) != null) {
            return vehicleLogs.get(vehicle);

        }

        return null;
    }
}
