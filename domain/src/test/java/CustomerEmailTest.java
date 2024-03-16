import complaint.valueobject.CustomerEmail;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerEmailTest {

    @Test
    void constructorTest_ValidEmail() throws  IOException {
        // Arrange
        String validEmail = "test@example.com";

        // Act
        CustomerEmail customerEmail = new CustomerEmail(validEmail);

        // Assert
        assertNotNull(customerEmail);
        assertEquals(validEmail, customerEmail.getEmail());
    }

    @Test
    void constructorTest_InvalidEmail() {
        // Arrange
        String invalidEmail = "invalid_email";

        // Act & Assert
        IOException exception = assertThrows(IOException.class, () -> new CustomerEmail(invalidEmail));
        assertEquals("Invalid email format", exception.getMessage());
    }

    @Test
    void constructorTest_NullEmail() {
        // Arrange
        String nullEmail = null;

        // Act & Assert
        IOException exception = assertThrows(IOException.class, () -> new CustomerEmail(nullEmail));
        assertEquals("Invalid email format", exception.getMessage());
    }
}
