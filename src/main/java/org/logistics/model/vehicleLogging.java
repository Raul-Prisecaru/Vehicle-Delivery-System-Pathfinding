package org.logistics.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class vehicleLogging {

    HashMap<Vehicle, LinkedList<String>> vehicleLogs;

    public vehicleLogging() {
        vehicleLogs = new HashMap<>();
    }

    public void addVehicleLog(Vehicle vehicle, String statusLog)  {
        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.add("Current Location: " + vehicle.getCurrent_location().getNodeValue());
        linkedList.add("Travel Destination: " + vehicle.getTravelDestinations());
        linkedList.add("Package: " + vehicle.get_deliveryPackages());
        linkedList.add("Status:" + statusLog);

        vehicleLogs.put(vehicle, linkedList);
    }


    public LinkedList<String> getVehicleLog(Vehicle vehicle) {

        if (vehicleLogs.get(vehicle) != null) {
            return vehicleLogs.get(vehicle);

        }

        return null;
    }
}
