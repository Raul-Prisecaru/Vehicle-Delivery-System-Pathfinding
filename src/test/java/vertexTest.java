import org.junit.jupiter.api.Test;

import org.logistics.model.Vehicle;
import org.logistics.model.Vertex;

import static org.junit.jupiter.api.Assertions.*;


public class vertexTest {
    Vertex testVertex = new Vertex("A");

    /**
     * Method Responsible for ensuring Vertex's Value has been correctly configured
     */
    @Test
    void getVertexValue() {
        assertEquals("A", testVertex.get_node_value(), "Vertex's value was not properly configured");
    }

    /**
     * Method Responsible for ensuring Vertex's Value can be correctly updated
     */
    @Test
    void updatingVertexValue() {
        testVertex.set_node_value("B");
        assertEquals("B", testVertex.get_node_value(), "Vertex's value was not properly updated'");
    }

    /**
     * Method Responsible for ensuring Vertex's default distance has been correctly configured
     */
    @Test
    void getVertexDistance() {
        assertEquals(Integer.MAX_VALUE, testVertex.get_distance(), "Vertex's Distance was not properly configured");
    }

    /**
     * Method Responsible for ensuring Vertex's Distance value can be correctly updated
     */
    @Test
    void updateVertexDistance() {
        testVertex.set_distance(5);
        assertEquals(5, testVertex.get_distance(), "Vertex's distance was not properly updated");
    }

    /**
     * Method Responsible for ensuring Vertex does not hold any vehicle at start
     */
    @Test
    void getVertexStoredVehicle() {
        assertTrue(testVertex.getStoredVehicles().isEmpty(), "Vertex's storedVehicle should contain nothing at start");
    }

    /**
     * Method Responsible for ensuring Vertex can hold Vehicles
     */
    @Test
    void updateVertexStoredVehicle() {
        Vehicle testVehicle = new Vehicle(testVertex);
        testVertex.setStoredVehicles(testVehicle);

        assertFalse(testVertex.getStoredVehicles().isEmpty());

        assertInstanceOf(Vehicle.class, testVertex.getStoredVehicles().getFirst());
    }
}
