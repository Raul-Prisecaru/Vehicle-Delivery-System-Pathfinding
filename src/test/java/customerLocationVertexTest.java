import org.junit.jupiter.api.Test;
import org.logistics.model.CustomerLocation;
import org.logistics.model.Package;
import org.logistics.model.Vehicle;

import static org.junit.jupiter.api.Assertions.*;


public class customerLocationVertexTest {
    CustomerLocation testCustomerLocationVertex = new CustomerLocation("A");

    /**
     * Method Responsible for ensuring Vertex's Value has been correctly configured
     */
    @Test
    void getCustomerLocationValue() {
        assertEquals("A", testCustomerLocationVertex.getNodeValue(), "Vertex's value was not properly configured");
    }

    /**
     * Method Responsible for ensuring Vertex's default distance has been correctly configured
     */
    @Test
    void getCustomerLocationDistance() {
        assertEquals(Integer.MAX_VALUE, testCustomerLocationVertex.getDistance(), "Vertex's Distance was not properly configured");
    }

    /**
     * Method Responsible for ensuring Vertex's Distance value can be correctly updated
     */
    @Test
    void updateCustomerLocationDistance() {
        testCustomerLocationVertex.setDistance(5);
        assertEquals(5, testCustomerLocationVertex.getDistance(), "Vertex's distance was not properly updated");
    }

    /**
     * Method Responsible for ensuring Vertex does not hold any vehicle at start
     */
    @Test
    void getCustomerLocationStoredVehicle() {
        assertTrue(testCustomerLocationVertex.getStoredVehicles().isEmpty(), "Vertex's storedVehicle should contain nothing at start");
    }

    /**
     * Method Responsible for ensuring Vertex can hold Vehicles
     */
    @Test
    void updateCustomerLocationStoredVehicle() {
        Vehicle testVehicle = new Vehicle(testCustomerLocationVertex);
        testCustomerLocationVertex.addStoredVehicles(testVehicle);

        assertFalse(testCustomerLocationVertex.getStoredVehicles().isEmpty());

        assertInstanceOf(Vehicle.class, testCustomerLocationVertex.getStoredVehicles().getFirst());
    }


    @Test
    void getCustomerLocationPackages() {
        assertEquals(0, testCustomerLocationVertex.getCollectedPackages().size());
    }


    @Test
    void addCustomerLocationPackage() {
        CustomerLocation customerLocation = new CustomerLocation("A");
        testCustomerLocationVertex.addCollectedPackage("TestItem", customerLocation, 1);

        for (Package package_package : testCustomerLocationVertex.getCollectedPackages()) {
            assertEquals("TestItem", package_package.getItem_Name());
            assertEquals(customerLocation, package_package.getDestination());
            assertEquals(1, package_package.getPriority());

        }

    }
}
