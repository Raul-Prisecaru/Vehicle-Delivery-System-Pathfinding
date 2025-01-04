import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.logistics.model.*;
import org.logistics.view.directedGraphDisplay;

public class directedGraphDisplayTest {

    @Test
    void testAddNode() {
        Graph graph = new Graph();
        directedGraphDisplay directedGraphDisplay = new directedGraphDisplay(graph);

        directedGraphDisplay.add_deliveryHub(new DeliveryHub<>("V"));
        directedGraphDisplay.add_customerLocation(new CustomerLocation<>("M"));

        assertTrue(directedGraphDisplay.doesNodeExist("V"));
        assertTrue(directedGraphDisplay.doesNodeExist("M"));
        assertEquals(graph.findVertexAndReturn(new Vertex<>("V")).getNodeValue(), new Vertex<>("V").getNodeValue());
        assertEquals(graph.findVertexAndReturn(new Vertex<>("M")).getNodeValue(), new Vertex<>("M").getNodeValue());

    }

    @Test
    void testRemoveNode() {
        Graph graph = new Graph();
        directedGraphDisplay directedGraphDisplay = new directedGraphDisplay(graph);

        directedGraphDisplay.add_deliveryHub(new DeliveryHub<>("V"));
        directedGraphDisplay.add_customerLocation(new CustomerLocation<>("M"));

        directedGraphDisplay.remove_deliveryHub(new DeliveryHub<>("V"));
        directedGraphDisplay.remove_customerLocation(new CustomerLocation<>("M"));

        assertFalse(directedGraphDisplay.doesNodeExist("V"));
        assertFalse(directedGraphDisplay.doesNodeExist("M"));

        assertNull(graph.findVertexAndReturn(new Vertex<>("V")));
        assertNull(graph.findVertexAndReturn(new Vertex<>("M")));

    }


    @Test
    void testAddEdge() {
        Graph graph = new Graph();
        directedGraphDisplay directedGraphDisplay = new directedGraphDisplay(graph);
        directedGraphDisplay.add_customerLocation(new CustomerLocation<>("M"));
        directedGraphDisplay.add_customerLocation(new CustomerLocation<>("N"));

        directedGraphDisplay.add_edge(new Edge(new Vertex<>("M"), new Vertex<>("N"), 2));
        assertTrue(directedGraphDisplay.doesEdgeExist("MN"));


        Edge edge = graph.findEdgeAndReturn(graph.findVertexAndReturn(new Vertex<>("M")), graph.findVertexAndReturn(new Vertex<>("N")));

        assertEquals(edge.getStart_node().getNodeValue(), graph.findVertexAndReturn(new Vertex<>("M")).getNodeValue());
        assertEquals(edge.getConnecting_node().getNodeValue(), graph.findVertexAndReturn(new Vertex<>("N")).getNodeValue());
        assertEquals(edge.getDistance_weight(), 2);

    }

    @Test
    void testModifyEdge() {
        Graph graph = new Graph();
        directedGraphDisplay directedGraphDisplay = new directedGraphDisplay(graph);
        directedGraphDisplay.add_customerLocation(new CustomerLocation<>("M"));
        directedGraphDisplay.add_customerLocation(new CustomerLocation<>("N"));
        directedGraphDisplay.add_customerLocation(new CustomerLocation<>("X"));
        directedGraphDisplay.add_customerLocation(new CustomerLocation<>("Z"));

        directedGraphDisplay.add_edge(new Edge(new Vertex<>("M"), new Vertex<>("N"), 2));
        directedGraphDisplay.modify_edge(new Edge(new Vertex<>("M"), new Vertex<>("N"), 2), new Vertex<>("X"), new Vertex<>("Z"), 5);
        assertTrue(directedGraphDisplay.doesEdgeExist("XZ"));
        assertFalse(directedGraphDisplay.doesEdgeExist("MN"));
        assertNull(graph.findEdgeAndReturn(graph.findVertexAndReturn(new Vertex<>("M")), graph.findVertexAndReturn(new Vertex<>("N"))));


        Edge edge = graph.findEdgeAndReturn(graph.findVertexAndReturn(new Vertex<>("X")), graph.findVertexAndReturn(new Vertex<>("Z")));

        assertEquals(edge.getStart_node().getNodeValue(), graph.findVertexAndReturn(new Vertex<>("X")).getNodeValue());
        assertEquals(edge.getConnecting_node().getNodeValue(), graph.findVertexAndReturn(new Vertex<>("Z")).getNodeValue());
        assertEquals(edge.getDistance_weight(), 5);


    }

    @Test
    void testRemoveEdge() {
        Graph graph = new Graph();
        directedGraphDisplay directedGraphDisplay = new directedGraphDisplay(graph);
        directedGraphDisplay.add_customerLocation(new CustomerLocation<>("M"));
        directedGraphDisplay.add_customerLocation(new CustomerLocation<>("N"));

        directedGraphDisplay.add_edge(new Edge(new Vertex<>("M"), new Vertex<>("N"), 2));
        directedGraphDisplay.remove_edge(new Edge(new Vertex<>("M"), new Vertex<>("N"), 2));
        assertFalse(directedGraphDisplay.doesEdgeExist("MN"));
        assertNull(graph.findEdgeAndReturn(graph.findVertexAndReturn(new Vertex<>("M")), graph.findVertexAndReturn(new Vertex<>("N"))));



    }
}
