package untested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.logistics.model.CustomerLocation;
import org.logistics.model.Package;


public class packageTest {
    CustomerLocation customerLocationTest = new CustomerLocation("B");
    Package testPackage = new Package("TestItem", customerLocationTest, 1);


    void getItemName() {
        assertEquals("TestItem", testPackage.getItem_Name());
    }

    void getDestination() {
        assertEquals(customerLocationTest, testPackage.getDestination());
    }

    void getPriority() {
        assertEquals(1, testPackage.getPriority());
    }


}
