import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.logistics.model.CustomerLocation;
import org.logistics.model.Package;


public class packageTest {
    CustomerLocation customerLocationTest = new CustomerLocation("B");
    Package testPackage = new Package("TestItem", customerLocationTest, 1, 0, 0);

    @Test
    void getItemName() {
        assertEquals("TestItem", testPackage.getItem_Name());
    }

    @Test
    void getDestination() {
        assertEquals(customerLocationTest, testPackage.getDestination());
    }

    @Test
    void getPriority() {
        assertEquals(1, testPackage.getPriority());
    }


}
