import complaint.valueobject.CustomerLocation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class CustomerLocationMockTest {

    @Test
    void testCustomerLocation() {
        // Create a mock CustomerLocation object
        CustomerLocation mockedLocation = mock(CustomerLocation.class);

        // Define the behavior of the mock object
        when(mockedLocation.getCountry()).thenReturn("Country");
        when(mockedLocation.getState()).thenReturn("State");
        when(mockedLocation.getCity()).thenReturn("City");
        when(mockedLocation.getStreet()).thenReturn("Street");
        when(mockedLocation.getNumber()).thenReturn("123");

        // Use the mock object in your test
        assertEquals("Country", mockedLocation.getCountry());
        assertEquals("State", mockedLocation.getState());
        assertEquals("City", mockedLocation.getCity());
        assertEquals("Street", mockedLocation.getStreet());
        assertEquals("123", mockedLocation.getNumber());
    }
}
