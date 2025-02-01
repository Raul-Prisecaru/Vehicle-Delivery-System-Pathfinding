import org.junit.jupiter.api.Test;
import org.logistics.model.*;
import org.logistics.model.Package;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;



public class MultiAgentFeatureTest {

    @Test
    void findMultipleVehiclesInCustomerLocations() throws Exception {
        Graph graph = new Graph();


        // Creating CustomerLocation Nodes
        CustomerLocation<String> customerLocationA = new CustomerLocation<>("A");
        CustomerLocation<String> customerLocationB = new CustomerLocation<>("B");
        CustomerLocation<String> customerLocationC = new CustomerLocation<>("C");
        CustomerLocation<String> customerLocationD = new CustomerLocation<>("D");
        CustomerLocation<String> customerLocationE = new CustomerLocation<>("E");

        // Creating Packages
        Vehicle vehicle1 = new Vehicle(customerLocationC);
        Vehicle vehicle2 = new Vehicle(customerLocationC);

        graph.add_customerLocation(customerLocationA);
        graph.add_customerLocation(customerLocationB);
        graph.add_customerLocation(customerLocationC);
        graph.add_customerLocation(customerLocationD);
        graph.add_customerLocation(customerLocationE);

        // Creating Edges
        graph.add_directed_edge(customerLocationA, customerLocationB, 5);
        graph.add_directed_edge(customerLocationB, customerLocationE, 5);
        graph.add_directed_edge(customerLocationE, customerLocationD, 5);
        graph.add_directed_edge(customerLocationD, customerLocationA, 5);

        graph.add_directed_edge(customerLocationC, customerLocationA,5);
        graph.add_directed_edge(customerLocationC, customerLocationB,5);
        graph.add_directed_edge(customerLocationC, customerLocationD,5);
        graph.add_directed_edge(customerLocationC, customerLocationE,5);

        customerLocationC.addStoredVehicles(vehicle1);
        customerLocationC.addStoredVehicles(vehicle2);

        MultiAgent multiAgent = new MultiAgent(graph);

        HashSet<CustomerLocation<String>> foundCustomerLocation = multiAgent.findMultipleVehiclesInCustomerLocations();
        assertTrue(foundCustomerLocation.contains(customerLocationC));

    }

    @Test
    void vehicleCommunicate() throws Exception {
        Graph graph = new Graph();


        // Creating CustomerLocation Nodes
        CustomerLocation<String> customerLocationA = new CustomerLocation<>("A");
        CustomerLocation<String> customerLocationB = new CustomerLocation<>("B");
        CustomerLocation<String> customerLocationC = new CustomerLocation<>("C");
        CustomerLocation<String> customerLocationD = new CustomerLocation<>("D");
        CustomerLocation<String> customerLocationE = new CustomerLocation<>("E");

        // Creating Packages
        Vehicle vehicle1 = new Vehicle(customerLocationC);
        Vehicle vehicle2 = new Vehicle(customerLocationC);
        Package vehicle2Package = new Package("TestItem", customerLocationE, 5, 1, 1);

        vehicle2.add_deliveryPackage(vehicle2Package);

        vehicle1.addTravelDestination(customerLocationD);
        vehicle1.addTravelDestination(customerLocationE);

        vehicle2.addTravelDestination(customerLocationA);
        vehicle2.addTravelDestination(customerLocationB);
        vehicle2.addTravelDestination(customerLocationE);

        graph.add_customerLocation(customerLocationA);
        graph.add_customerLocation(customerLocationB);
        graph.add_customerLocation(customerLocationC);
        graph.add_customerLocation(customerLocationD);
        graph.add_customerLocation(customerLocationE);

        // Creating Edges
        graph.add_directed_edge(customerLocationA, customerLocationB, 5);
        graph.add_directed_edge(customerLocationB, customerLocationE, 5);
        graph.add_directed_edge(customerLocationE, customerLocationD, 5);
        graph.add_directed_edge(customerLocationD, customerLocationA, 5);

        graph.add_directed_edge(customerLocationC, customerLocationA,5);
        graph.add_directed_edge(customerLocationC, customerLocationB,5);
        graph.add_directed_edge(customerLocationC, customerLocationD,5);
        graph.add_directed_edge(customerLocationC, customerLocationE,5);
        customerLocationC.addStoredVehicles(vehicle1);
        customerLocationC.addStoredVehicles(vehicle2);

        System.out.println("-- Before --");
        System.out.println("Current Packages of Vehicles:");
        System.out.println("vehicle" + vehicle1);
        for (Package package_package : vehicle1.get_deliveryPackages()) {
            System.out.println("Item Name: " + package_package.getItem_Name());
            System.out.println("Destination: " + package_package.getDestination());
        }

        System.out.println("vehicle" + vehicle2);
        for (Package package_package : vehicle2.get_deliveryPackages()) {
            System.out.println("Item Name: " + package_package.getItem_Name());
            System.out.println("Destination: " + package_package.getDestination());
        }

        MultiAgent multiAgent = new MultiAgent(graph);
        HashSet<CustomerLocation<String>> foundCustomerLocation = multiAgent.findMultipleVehiclesInCustomerLocations();
        multiAgent.vehicleCommunicate(foundCustomerLocation);

        System.out.println("-- after --");
        System.out.println(vehicle1);
        for (Package package_package : vehicle1.get_deliveryPackages()) {
            System.out.println("Item Name: " + package_package.getItem_Name());
            System.out.println("Destination: " + package_package.getDestination());
        }

        System.out.println(vehicle2);
        for (Package package_package : vehicle2.get_deliveryPackages()) {
            System.out.println("Item Name: " + package_package.getItem_Name());
            System.out.println("Destination: " + package_package.getDestination());
        }

        for (Package package_package : vehicle1.get_deliveryPackages()) {
                assertEquals(package_package, vehicle2Package);
            }
        }
    }
