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
    public void getCurrentLocation() {
        assertEquals("A", testVehicle.getCurrent_vertex().get_node_value());
    }

    @Test
    public void updateCurrentLocation() {
        testVehicle.setCurrent_vertex(new Vertex("B"));

        assertEquals("B", testVehicle.getCurrent_vertex().get_node_value());
    }

    @Test
    public void getDeliveryPackages() {
        assertTrue(testVehicle.get_deliveryPackages().isEmpty());
    }

    @Test
    public void updateDeliveryPackages() {
        testVehicle.add_deliveryPackage(new Package("testItem", new CustomerLocation("B"), 2));

        assertFalse(testVehicle.get_deliveryPackages().isEmpty());

        assertInstanceOf(Package.class, testVehicle.get_deliveryPackages().peek());
    }

    @Test
    public void MaxCapacity() {
        testVehicle.add_deliveryPackage(new Package("testItem", new CustomerLocation("B"), 2));
        testVehicle.add_deliveryPackage(new Package("testItem", new CustomerLocation("B"), 2));
        testVehicle.add_deliveryPackage(new Package("testItem", new CustomerLocation("B"), 2));

        // TODO: Update this to check for a throw error
        assertNotEquals("3", testVehicle.get_deliveryPackages().size());
    }

    @Test
    public void getTravelDestination() {
        assertTrue(testVehicle.getTravelDestinations().isEmpty());
    }

    @Test
    public void updateTravelDestination() {
        testVehicle.addTravelDestination(new Vertex("B"));

        assertFalse(testVehicle.getTravelDestinations().isEmpty());

        assertInstanceOf(Vertex.class, testVehicle.getTravelDestinations().peek());

    }

    @Test
    public void travelDestination() {
        Vertex testVertexB = new Vertex("B");
        testVehicle.addTravelDestination(testVertexB);

        testVehicle.travel();
        assertEquals("B", testVehicle.getCurrent_vertex().get_node_value());
        assertTrue(testVertexA.getStoredVehicles().isEmpty());
        assertFalse(testVertexB.getStoredVehicles().isEmpty());

    }

}
