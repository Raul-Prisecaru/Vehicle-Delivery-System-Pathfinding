package org.logistics.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class DeliveryHub<T> extends Vertex<T> {
    HashSet<Package> packages = new HashSet<Package>();
    Random random = new Random();

    /**
     * Constructor Responsible for setting the value of the DeliveryHub Vertex
     * @param value (String) - Value of the DeliveryHub Vertex
     */
    public DeliveryHub(T value) {
        super(value);
    }

    /**
     * Method Responsible for returning the Vertex value of DeliveryHub
     * @return T - DeliveryHub vertex value
     */
    public T getNodeValue() {
        return super.getNodeValue();
    }

    /**
     * Method Responsible for returning the distance of the DeliveryHub
     * @return int - Distance of the deliveryHub Vertex
     */
    public int getDistance() {
        return super.getDistance();
    }

    /**
     * Method Responsible for overriding the distance value of the DeliveryHub
     * @param new_distance (int) - new distance
     */
    public void setDistance(int new_distance) {
        super.setDistance(new_distance);
    }

    /**
     * Method Responsible for returning the ArrayList containing all the vehicle at the DeliveryHub
     * @return ArrayList<Vehicle> - ArrayList of Vehicles at the DeliveryHub
     */
    public ArrayList<Vehicle> getStoredVehicles() {
        return super.getStoredVehicles();
    }

    /**
     * Method Responsible for adding vehicle to the ArrayList
     * @param vehicle (Vehicle) - Vehicle to add to the ArrayList
     */
    public void addStoredVehicles(Vehicle vehicle) {
        super.addStoredVehicles(vehicle);
    }

    /**
     * Method Responsible for overriding the output when DeliveryHub is called
     * @return "DeliveryHub: " + {Vertex Value}
     */
    @Override
    public String toString() {
        return "DeliveryHub: " + getNodeValue();
    }

    /**
     * Method Responsible for returning HashSet of packages in the DeliveryHub
     * @return HashSet<Package> - HashSet of all the packages the DeliveryHub contains
     */
    public HashSet<Package> getPackages () {
        return packages;
    }

    /**
     * Method Responsible automatically generating new packages
     * @param graph (Graph) - graph to get customerLocation from
     */
    public void generatePackage(Graph graph) {
        String[] itemNames = {"Fan", "Phone", "Microphone", "Laptop", "Headphone", "Mouse", "Console"};
        ArrayList<CustomerLocation<String>> customerLocationList = graph.getAllCustomerLocation();

        int randomStringInt = random.nextInt(itemNames.length);

        int randomCustomerLocationInt = random.nextInt(graph.getAllCustomerLocation().size());

        this.packages.add(new Package(itemNames[randomStringInt], customerLocationList.get(randomCustomerLocationInt), random.nextInt(10), random.nextInt(10), random.nextInt(10)));
    }

    /**
     * Method Responsible for automatically generating x amount of new packages
     * @param graph (Graph) - graph to get customerLocations from
     * @param total (int) - total number of new packages to be generated
     */
    public void generatePackage(Graph graph ,int total) {
        String[] itemNames = {"Fan", "Phone", "Microphone", "Laptop", "Headphone", "Mouse", "Console"};
        ArrayList<CustomerLocation<String>> customerLocationList = graph.getAllCustomerLocation();

        for (int i = 0; i < total; i++) {

            int randomStringInt = random.nextInt(itemNames.length);

            int randomCustomerLocationInt = random.nextInt(customerLocationList.size());

            this.packages.add(new Package(itemNames[randomStringInt], customerLocationList.get(randomCustomerLocationInt), random.nextInt(10), random.nextInt(10), random.nextInt(10)));
        }
    }

    public void generatePackage(Graph graph ,int total, DeliveryHub<String> deliveryHub) {
        String[] itemNames = {"Fan", "Phone", "Microphone", "Laptop", "Headphone", "Mouse", "Console"};
        ArrayList<CustomerLocation<String>> customerLocationList = graph.getAllCustomerLocation();

        for (int i = 0; i < total; i++) {

            int randomStringInt = random.nextInt(itemNames.length);

            int randomCustomerLocationInt = random.nextInt(customerLocationList.size());

            this.packages.add(new Package(itemNames[randomStringInt], customerLocationList.get(randomCustomerLocationInt), random.nextInt(10), random.nextInt(10), random.nextInt(10), deliveryHub));
        }
    }


    /**
     * Method Responsible for removing package from the DeliveryHub
     * @param package_remove (Package) - Package to be removed from HashSet
     */
    public void removePackages(Package package_remove) {
        this.packages.remove(package_remove);
    }
}
