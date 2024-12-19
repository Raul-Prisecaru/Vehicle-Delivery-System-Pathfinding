import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.logistics.model.*;
import org.logistics.model.Package;

import java.util.HashMap;


public class pathfindingTest {
    Graph testGraph = new Graph();
    Pathfinding testPathfinding = new Pathfinding(testGraph);

    @Test
    void testPathFinding() {
        Vertex testVertexA = new Vertex("A");
        Vertex testVertexB = new Vertex("B");
        Vertex testVertexC = new Vertex("C");
        CustomerLocation testVertexD = new CustomerLocation("D");

        Package testPackage = new Package("ItemName", testVertexD, 1);
        Vehicle testVehicle = new Vehicle(testVertexA);
        testVehicle.add_deliveryPackage(testPackage);

        testGraph.add_vertex(testVertexA);
        testGraph.add_vertex(testVertexB);
        testGraph.add_vertex(testVertexC);
        testGraph.add_vertex(testVertexD);

        testGraph.add_directed_edge(testVertexA, testVertexB, 4);
        testGraph.add_directed_edge(testVertexA, testVertexC, 3);

        testGraph.add_directed_edge(testVertexB, testVertexD, 1);
        testGraph.add_directed_edge(testVertexC, testVertexD, 4);

//        HashMap<Vertex, Integer> result = testPathfinding.find_shortest_path(testVehicle);


//        assertTrue(result.containsKey(testVertexD));
//        assertTrue(result.containsValue(5));
    }
}
