import org.junit.jupiter.api.Test;
import org.logistics.model.*;
import org.logistics.model.Package;

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

        MultiAgent multiAgent = new MultiAgent(graph);

        CustomerLocation<String> foundCustomerLocation = multiAgent.findMultipleVehiclesInCustomerLocations();
        assertEquals(customerLocationC, foundCustomerLocation);

    }


    void vehicleCommunicate() {

    }
}
