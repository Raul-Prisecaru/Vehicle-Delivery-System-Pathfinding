import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.logistics.model.*;


public class graphTest {
    Graph graphTest = new Graph();

    @Test
    void getAdjacencyList() {

    }

    @Test
    void addVertex() throws Exception {
        DeliveryHub<String> testVertex = new DeliveryHub<>("A");

        graphTest.add_deliveryHub(testVertex);

        assertTrue(graphTest.getAdjacencyList().containsKey(testVertex));

    }

    @Test
    void addDirectedEdge() throws Exception {
        DeliveryHub<String> testStartVertex = new DeliveryHub<>("A");
        CustomerLocation<String> testEndVertex = new CustomerLocation<>("B");
        Edge testEdge = new Edge(testStartVertex, testEndVertex, 10);

        graphTest.add_deliveryHub(testStartVertex);
        graphTest.add_customerLocation(testEndVertex);
        graphTest.add_directed_edge(testStartVertex, testEndVertex, 10);

        Edge adjacentEdge = graphTest.getAdjacencyList().get(testStartVertex).getFirst();

        assertEquals(testStartVertex, adjacentEdge.getStart_node());
        assertEquals(testEndVertex, adjacentEdge.getConnecting_node());
        assertEquals(10, adjacentEdge.getDistance_weight());
    }



}
