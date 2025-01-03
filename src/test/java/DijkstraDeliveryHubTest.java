import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.logistics.model.*;
import org.logistics.model.Vehicle;
import org.logistics.model.algorithms.Dijkstra_deliveryHub;

import java.util.Stack;

public class DijkstraDeliveryHubTest {

    @Test
    void dijkstraDeliveryTest() throws Exception {
        Graph graph = new Graph();
        Dijkstra_deliveryHub dijkstra_deliveryHub = new Dijkstra_deliveryHub();

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
        vehicle1.setCurrent_location(customerLocationF);
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
        assertEquals(customerLocationF, vehicle1.getCurrent_location(), "Vehicle 1 isn't in the correct Starting Position");


        Stack<Vertex<String>> tempStackVehicle = new Stack<>();
        Stack<Vertex<String>> tempStackExpected = new Stack<>();
        tempStackExpected.add(deliveryHubA);
        tempStackExpected.add(customerLocationG);

        dijkstra_deliveryHub.find_shortest_delivery(vehicle1, graph);
        tempStackVehicle = vehicle1.getTravelDestinations();
        boolean isPathSame = tempStackVehicle.equals(tempStackExpected);
        System.out.println(tempStackVehicle);
        System.out.println(isPathSame);

        assertTrue(isPathSame);

    }







}


