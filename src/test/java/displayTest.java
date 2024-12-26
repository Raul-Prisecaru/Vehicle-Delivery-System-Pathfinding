import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.logistics.model.*;
import org.logistics.view.Display;

public class displayTest {

    @Test
    void testAddNode() {
        Graph graph = new Graph();
        Display display = new Display(graph);

        display.add_deliveryHub(new DeliveryHub<>("V"));
        display.add_customerLocation(new CustomerLocation<>("M"));

        assertTrue(display.doesNodeExist("V"));
        assertTrue(display.doesNodeExist("M"));
        assertEquals(graph.findVertexAndReturn(new Vertex<>("V")).getNodeValue(), new Vertex<>("V").getNodeValue());
        assertEquals(graph.findVertexAndReturn(new Vertex<>("M")).getNodeValue(), new Vertex<>("M").getNodeValue());

    }

    @Test
    void testRemoveNode() {
        Graph graph = new Graph();
        Display display = new Display(graph);

        display.add_deliveryHub(new DeliveryHub<>("V"));
        display.add_customerLocation(new CustomerLocation<>("M"));

        display.remove_deliveryHub(new DeliveryHub<>("V"));
        display.remove_customerLocation(new CustomerLocation<>("M"));

        assertFalse(display.doesNodeExist("V"));
        assertFalse(display.doesNodeExist("M"));

        assertNull(graph.findVertexAndReturn(new Vertex<>("V")));
        assertNull(graph.findVertexAndReturn(new Vertex<>("M")));

    }


    @Test
    void testAddEdge() {
        Graph graph = new Graph();
        Display display = new Display(graph);
        display.add_customerLocation(new CustomerLocation<>("M"));
        display.add_customerLocation(new CustomerLocation<>("N"));

        display.add_edge(new Edge(new Vertex<>("M"), new Vertex<>("N"), 2));
        assertTrue(display.doesEdgeExist("MN"));


        Edge edge = graph.findEdgeAndReturn(graph.findVertexAndReturn(new Vertex<>("M")), graph.findVertexAndReturn(new Vertex<>("N")));

        assertEquals(edge.getStart_node().getNodeValue(), graph.findVertexAndReturn(new Vertex<>("M")).getNodeValue());
        assertEquals(edge.getConnecting_node().getNodeValue(), graph.findVertexAndReturn(new Vertex<>("N")).getNodeValue());
        assertEquals(edge.getDistance_weight(), 2);

    }

    @Test
    void testModifyEdge() {
        Graph graph = new Graph();
        Display display = new Display(graph);
        display.add_customerLocation(new CustomerLocation<>("M"));
        display.add_customerLocation(new CustomerLocation<>("N"));
        display.add_customerLocation(new CustomerLocation<>("X"));
        display.add_customerLocation(new CustomerLocation<>("Z"));

        display.add_edge(new Edge(new Vertex<>("M"), new Vertex<>("N"), 2));
        display.modify_edge(new Edge(new Vertex<>("M"), new Vertex<>("N"), 2), new Vertex<>("X"), new Vertex<>("Z"), 5);
        assertTrue(display.doesEdgeExist("XZ"));
        assertFalse(display.doesEdgeExist("MN"));
        assertNull(graph.findEdgeAndReturn(graph.findVertexAndReturn(new Vertex<>("M")), graph.findVertexAndReturn(new Vertex<>("N"))));


        Edge edge = graph.findEdgeAndReturn(graph.findVertexAndReturn(new Vertex<>("X")), graph.findVertexAndReturn(new Vertex<>("Z")));

        assertEquals(edge.getStart_node().getNodeValue(), graph.findVertexAndReturn(new Vertex<>("X")).getNodeValue());
        assertEquals(edge.getConnecting_node().getNodeValue(), graph.findVertexAndReturn(new Vertex<>("Z")).getNodeValue());
        assertEquals(edge.getDistance_weight(), 2);


    }

    @Test
    void testRemoveEdge() {
        Graph graph = new Graph();
        Display display = new Display(graph);
        display.add_customerLocation(new CustomerLocation<>("M"));
        display.add_customerLocation(new CustomerLocation<>("N"));

        display.add_edge(new Edge(new Vertex<>("M"), new Vertex<>("N"), 2));
        display.remove_edge(new Edge(new Vertex<>("M"), new Vertex<>("N"), 2));
        assertFalse(display.doesEdgeExist("MN"));
        assertNull(graph.findEdgeAndReturn(graph.findVertexAndReturn(new Vertex<>("M")), graph.findVertexAndReturn(new Vertex<>("N"))));



    }
}
