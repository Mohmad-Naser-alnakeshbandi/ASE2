import complaint.valueobject.ComplaintDescription;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ComplaintDescriptionTest {

    @Test
    void constructorTest() throws IOException {
        // Arrange
        String title = "Valid Title";
        String body = "Valid Body";

        // Act
        ComplaintDescription complaint = new ComplaintDescription(title, body);

        // Assert
        assertNotNull(complaint);
        assertEquals(title, complaint.getTitle());
        assertEquals(body, complaint.getBody());
    }

    @Test
    void validateTitle_ThrowsExceptionWhenTitleIsTooShort() {
        // Arrange
        String shortTitle = "Short"; // Less than 10 characters

        // Act & Assert
        IOException exception = assertThrows(IOException.class, () -> new ComplaintDescription(shortTitle, "Valid Body"));
        assertEquals("Title should have at least 10 characters", exception.getMessage());
    }

    @Test
    void validateDescription_ThrowsExceptionWhenBodyIsTooShort() {
        // Arrange
        String shortBody = "Short"; // Less than 10 characters

        // Act & Assert
        IOException exception = assertThrows(IOException.class, () -> new ComplaintDescription("Valid Title", shortBody));
        assertEquals("Description should have at least 10 characters", exception.getMessage());
    }
}
