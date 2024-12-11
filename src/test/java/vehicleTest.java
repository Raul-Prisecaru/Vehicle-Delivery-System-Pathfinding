import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.logistics.model.CustomerLocation;
import org.logistics.model.Package;
import org.logistics.model.Vehicle;
import org.logistics.model.Vertex;



public class vehicleTest {
    Vertex testVertexA = new Vertex("A");
    Vehicle testVehicle = new Vehicle(testVertexA);

    @Test
    void getCurrentLocation() {
        assertEquals("A", testVehicle.getCurrent_vertex().get_node_value());
    }

    @Test
    void updateCurrentLocation() {
        testVehicle.setCurrent_vertex(new Vertex("B"));

        assertEquals("B", testVehicle.getCurrent_vertex().get_node_value());
    }

    @Test
    void getDeliveryPackages() {
        assertTrue(testVehicle.get_deliveryPackages().isEmpty());
    }

    @Test
    void updateDeliveryPackages() {
        testVehicle.add_deliveryPackage(new Package("Phone", new CustomerLocation("B"), 2));

        assertFalse(testVehicle.get_deliveryPackages().isEmpty());

        assertInstanceOf(Package.class, testVehicle.get_deliveryPackages().peek());
    }

    @Test
    void getTravelDestination() {
        assertTrue(testVehicle.getTravelDestinations().isEmpty());
    }

    @Test
    void updateTravelDestination() {
        testVehicle.addTravelDestination(new Vertex("B"));

        assertFalse(testVehicle.getTravelDestinations().isEmpty());

        assertInstanceOf(Vertex.class, testVehicle.getTravelDestinations().peek());

    }

    @Test
    void travelDestination() {
        Vertex testVertexB = new Vertex("B");
        testVehicle.addTravelDestination(testVertexB);

        testVehicle.travel();
        assertEquals("B", testVehicle.getCurrent_vertex().get_node_value());
        assertTrue(testVertexA.getStoredVehicles().isEmpty());
        assertFalse(testVertexB.getStoredVehicles().isEmpty());

    }

}
