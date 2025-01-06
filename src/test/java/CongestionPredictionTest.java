import org.junit.jupiter.api.Test;
import org.logistics.model.Package;
import org.logistics.model.*;
import org.logistics.model.algorithms.Bellman_ford_customerLocation;
import org.logistics.model.algorithms.Dijkstra_customerLocation;

import java.util.HashMap;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CongestionPredictionTest {

    @Test
    void CongestionPrediction() throws Exception {
        Graph graph = new Graph();
        Bellman_ford_customerLocation bellmanFordCustomerLocation = new Bellman_ford_customerLocation();
        Dijkstra_customerLocation dijkstra_customerLocation = new Dijkstra_customerLocation();
        // Creating DeliveryHub Nodes
        DeliveryHub<String> deliveryHubA = new DeliveryHub<String>("A");
        DeliveryHub<String> deliveryHubB = new DeliveryHub<String>("B");

        // Creating CustomerLocation Nodes
        CustomerLocation<String> customerLocationC = new CustomerLocation<String>("C");
        CustomerLocation<String> customerLocationD = new CustomerLocation<String>("D");

        // Creating Packages
        Vehicle vehicle1 = new Vehicle(deliveryHubA);


        CongestionPrediction congestionPrediction = new CongestionPrediction(graph);


        Package PriorityphonePackage = new Package("Iphone", customerLocationD, 1, 1, 1);
        vehicle1.add_deliveryPackage(PriorityphonePackage);
        deliveryHubA.getPackages().add(PriorityphonePackage);

        // Creating Vertexes
        graph.add_deliveryHub(deliveryHubA);
        graph.add_deliveryHub(deliveryHubB);

        graph.add_customerLocation(customerLocationC);
        graph.add_customerLocation(customerLocationD);
        graph.add_directed_edge(deliveryHubA, deliveryHubB, 5); // Distance: 4
        graph.add_directed_edge(deliveryHubA, customerLocationC, 2); // Distance: 2
        graph.add_directed_edge(deliveryHubB, customerLocationD, 1); // Distance: 2
        graph.add_directed_edge(customerLocationC, customerLocationD, 1); // Distance: 2


        Edge edge = graph.findEdgeAndReturn(deliveryHubA, customerLocationC);

        System.out.println("Before Passing Historical Data: ");
        System.out.println("A -> B " + graph.findEdgeAndReturn(deliveryHubA, deliveryHubB).getTime_weight());
        System.out.println("A -> C " + graph.findEdgeAndReturn(deliveryHubA, customerLocationC).getTime_weight());
        System.out.println("B -> D " + graph.findEdgeAndReturn(deliveryHubB, customerLocationD).getTime_weight());
        System.out.println("C -> D " + graph.findEdgeAndReturn(customerLocationC, customerLocationD).getTime_weight());
        dijkstra_customerLocation.find_shortest_customer(vehicle1, graph);
        System.out.println(vehicle1.getTravelDestinations());
        vehicle1.getTravelDestinations().clear();



        System.out.println();
        System.out.println("After Passing Historical Data:");
        edge.getCongestionHistory().put(2, 5);
        edge.getCongestionHistory().put(10, 20);

        HashMap<Integer, Integer> predictionCalculation = congestionPrediction.calculateCongestion(deliveryHubA, customerLocationC);
        System.out.println("Percentage of having congestion levels of 2: " + predictionCalculation.get(2) + "%");
        System.out.println("Percentage of having congestion levels of 10: " + predictionCalculation.get(10) + "%");
        dijkstra_customerLocation.find_shortest_customer(vehicle1, graph);
        System.out.println(vehicle1.getTravelDestinations());

        assertEquals(80, predictionCalculation.get(10));
        assertTrue(vehicle1.getTravelDestinations().contains(deliveryHubB));



    }







}


