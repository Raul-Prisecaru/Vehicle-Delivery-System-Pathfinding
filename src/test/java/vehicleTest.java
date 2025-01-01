import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.logistics.model.CustomerLocation;
import org.logistics.model.Package;
import org.logistics.model.Vehicle;
import org.logistics.model.Vertex;



public class vehicleTest {
    Vertex<String> testVertexA = new Vertex<String>("A");
    Vehicle testVehicle = new Vehicle(testVertexA);

    @Test
    public void getCurrentLocation() {
        assertEquals("A", testVehicle.getCurrent_location().getNodeValue());
    }

    @Test
    public void updateCurrentLocation() {
        testVehicle.setCurrent_location(new Vertex<String>("B"));

        assertEquals("B", testVehicle.getCurrent_location().getNodeValue());
    }

    @Test
    public void getDeliveryPackages() {
        assertTrue(testVehicle.get_deliveryPackages().isEmpty());
    }

    @Test
    public void updateDeliveryPackages() throws Exception {
        testVehicle.add_deliveryPackage(new Package("testItem", new CustomerLocation("B"), 2));

        assertFalse(testVehicle.get_deliveryPackages().isEmpty());

        assertInstanceOf(Package.class, testVehicle.get_deliveryPackages().peek());
    }

    @Test
    public void MaxCapacity() throws Exception {
        testVehicle.add_deliveryPackage(new Package("testItem", new CustomerLocation<String>("B"), 2));
        testVehicle.add_deliveryPackage(new Package("testItem", new CustomerLocation<String>("B"), 2));

        // Assert that adding a third package throws an exception
        assertThrows(Exception.class,
                () -> testVehicle.add_deliveryPackage(new Package("testItem", new CustomerLocation<String>("B"), 2))
        );
    }


    @Test
    public void getTravelDestination() {
        assertTrue(testVehicle.getTravelDestinations().isEmpty());
    }

    @Test
    public void updateTravelDestination() {
        testVehicle.addTravelDestination(new Vertex<String>("B"));

        assertFalse(testVehicle.getTravelDestinations().isEmpty());

        assertInstanceOf(Vertex.class, testVehicle.getTravelDestinations().peek());

    }

    @Test
    public void travelDestination() {
        Vertex<String> testVertexB = new Vertex<String>("B");
        Vertex<String> testVertexC = new Vertex<String>("C");
        testVehicle.addTravelDestination(testVertexB);
        testVehicle.addTravelDestination(testVertexC);

        for (Vertex<String> vertex : testVehicle.getTravelDestinations().reversed()) {
            testVehicle.travel(vertex);
            assertEquals(testVehicle.getCurrent_location(), vertex);
        }


        assertEquals("B", testVehicle.getCurrent_location().getNodeValue());
        assertTrue(testVertexA.getStoredVehicles().isEmpty());
        assertFalse(testVertexB.getStoredVehicles().isEmpty());

    }

    @Test
    void findTravelSteps() {
        CustomerLocation<String> customerLocationA = new CustomerLocation<>("A");
        CustomerLocation<String> customerLocationB = new CustomerLocation<>("B");
        CustomerLocation<String> customerLocationC = new CustomerLocation<>("C");
        CustomerLocation<String> customerLocationD = new CustomerLocation<>("D");
        CustomerLocation<String> customerLocationE = new CustomerLocation<>("E");

        Vehicle testVehicleSteps = new Vehicle(customerLocationA);

        testVehicleSteps.addTravelDestination(customerLocationB);
        testVehicleSteps.addTravelDestination(customerLocationC);
        testVehicleSteps.addTravelDestination(customerLocationD);
        testVehicleSteps.addTravelDestination(customerLocationE);

        int customerLocationSteps = testVehicleSteps.findTravelSteps(customerLocationE);
        int invalidCustomerLocationSteps = testVehicleSteps.findTravelSteps(new CustomerLocation<>("G"));
        assertEquals(4, customerLocationSteps);
        assertEquals(-1, invalidCustomerLocationSteps);
    }

}
