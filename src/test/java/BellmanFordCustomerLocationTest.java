import org.junit.jupiter.api.Test;
import org.logistics.model.Package;
import org.logistics.model.*;
import org.logistics.model.algorithms.Bellman_ford_customerLocation;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BellmanFordCustomerLocationTest {

    @Test
    void dijkstraCustomerTest() throws Exception {
        Graph graph = new Graph();
        Bellman_ford_customerLocation bellmanFordCustomerLocation = new Bellman_ford_customerLocation();

        // Creating DeliveryHub Nodes
        DeliveryHub<String> deliveryHubA = new DeliveryHub<String>("A");
        DeliveryHub<String> deliveryHubB = new DeliveryHub<String>("B");

        // Creating CustomerLocation Nodes
        CustomerLocation<String> customerLocationC = new CustomerLocation<String>("C");
        CustomerLocation<String> customerLocationD = new CustomerLocation<String>("D");
        CustomerLocation<String> customerLocationE = new CustomerLocation<String>("E");
        CustomerLocation<String> customerLocationF = new CustomerLocation<String>("F");
        CustomerLocation<String> customerLocationG = new CustomerLocation<String>("G");

        // Creating Packages
        Vehicle vehicle1 = new Vehicle(deliveryHubA);

        Package PriorityphonePackage = new Package("Iphone", customerLocationF, 1, 1, 1);
        Package NonPriorityphonePackage = new Package("Iphone", customerLocationE, 0, 0, 0);
        vehicle1.add_deliveryPackage(PriorityphonePackage);
//        vehicle1.add_deliveryPackage(NonPriorityphonePackage);
        deliveryHubA.getPackages().add(PriorityphonePackage);
        deliveryHubB.getPackages().add(NonPriorityphonePackage);

        // Creating Vertexes
        graph.add_deliveryHub(deliveryHubA);
        graph.add_deliveryHub(deliveryHubB);

        graph.add_customerLocation(customerLocationC);
        graph.add_customerLocation(customerLocationD);
        graph.add_customerLocation(customerLocationE);
        graph.add_customerLocation(customerLocationF); // New Vertex
        graph.add_customerLocation(customerLocationG); // New Vertex

        // Creating Edges
        graph.add_directed_edge(deliveryHubA, deliveryHubB, 4); // Distance: 4
        graph.add_directed_edge(deliveryHubA, customerLocationC, 2); // Distance: 2
        graph.add_directed_edge(deliveryHubB, customerLocationC, 1); // Distance: 1
        graph.add_directed_edge(deliveryHubB, customerLocationD, 2); // Distance: 2
        graph.add_directed_edge(deliveryHubB, customerLocationE, 3); // Distance: 3
        graph.add_directed_edge(customerLocationC, customerLocationE, 2); // Distance: 2
        graph.add_directed_edge(customerLocationE, customerLocationD, 5); // Distance: 5

        // New Edges to Connect New Vertexes
        graph.add_directed_edge(customerLocationE, customerLocationF, 3); // Distance: 3
        graph.add_directed_edge(customerLocationF, customerLocationG, 2); // Distance: 2
        graph.add_directed_edge(customerLocationG, deliveryHubA, 4); // Distance: 4

        // Additional Edges to Ensure Reachability
        graph.add_directed_edge(customerLocationD, customerLocationF, 2); // Distance: 2
        graph.add_directed_edge(customerLocationC, customerLocationG, 6); // Distance: 6

        //Logic Stuff

        // Test for Starting Position
        assertEquals(deliveryHubA, vehicle1.getCurrent_location(), "Vehicle 1 isn't in the correct Starting Position");


        Stack<Vertex<String>> tempStackVehicle = new Stack<>();
        Stack<Vertex<String>> tempStackExpected = new Stack<>();
        tempStackExpected.add(customerLocationF);
        tempStackExpected.add(customerLocationE);
        tempStackExpected.add(customerLocationC);

        bellmanFordCustomerLocation.find_shortest_customer(vehicle1, graph);
        tempStackVehicle = vehicle1.getTravelDestinations();
        boolean isPathSame = tempStackVehicle.equals(tempStackExpected);
        System.out.println(tempStackVehicle);
        System.out.println(tempStackExpected);
        System.out.println(isPathSame);

        assertTrue(isPathSame);

    }







}


