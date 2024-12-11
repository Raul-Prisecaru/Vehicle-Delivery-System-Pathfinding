import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.logistics.model.Edge;
import org.logistics.model.Graph;
import org.logistics.model.Vertex;


public class graphTest {
    Graph graphTest = new Graph();

    @Test
    void getAdjacencyList() {

    }

    @Test
    void addVertex() {
        Vertex testVertex = new Vertex("A");

        graphTest.add_vertex(testVertex);

        assertTrue(graphTest.getAdjacencyList().containsKey(testVertex));

    }

    @Test
    void addDirectedEdge() {
        Vertex testStartVertex = new Vertex("A");
        Vertex testEndVertex = new Vertex("B");
        Edge testEdge = new Edge(testStartVertex, testEndVertex, 10);

        graphTest.add_vertex(testStartVertex);
        graphTest.add_vertex(testEndVertex);
        graphTest.add_directed_edge(testStartVertex, testEndVertex, 10);

        Edge adjacentEdge = graphTest.getAdjacencyList().get(testStartVertex).getFirst();

        assertEquals(testStartVertex, adjacentEdge.getStart_node());
        assertEquals(testEndVertex, adjacentEdge.getConnecting_node());
        assertEquals(10, adjacentEdge.getDistance_weight());
    }



}
