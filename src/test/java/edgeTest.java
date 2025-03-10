import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.logistics.model.Edge;
import org.logistics.model.Vertex;


public class edgeTest {
    Vertex testVertexA = new Vertex("A");
    Vertex testVertexB = new Vertex("B");

    Edge testEdge = new Edge(testVertexA, testVertexB, 10);

    @Test
    void getStartNode() {
        assertEquals("A", testEdge.getStart_node().getNodeValue());
    }

    @Test
    void getConnectingNode() {
        assertEquals("B", testEdge.getConnecting_node().getNodeValue());
    }

    @Test
    void getDistanceWeight() {
        assertEquals(10, testEdge.getDistance_weight());
    }

    // TODO: Add timeWeight and congestion Weight once implemented later on

    @Test
    void setDistanceWeight() {
        testEdge.setDistance_weight(5);
        assertEquals(5, testEdge.getDistance_weight());
    }
}
