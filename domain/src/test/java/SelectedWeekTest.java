import org.junit.jupiter.api.Test;
import weeklyreport.valueobject.SelectedWeek;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class SelectedWeekTest {

    @Test
    void constructorTest_ValidWeek() throws IOException {
        // Arrange
        int validWeek = 1;

        // Act
        SelectedWeek selectedWeek = new SelectedWeek(validWeek);

        // Assert
        assertNotNull(selectedWeek);
        assertEquals(validWeek, selectedWeek.getSelectedWeek());
    }

    @Test
    void constructorTest_InvalidWeek_LowerBound() {
        // Arrange
        int invalidWeek = 0;

        // Act & Assert
        IOException exception = assertThrows(IOException.class, () -> new SelectedWeek(invalidWeek));
        assertEquals("Invalid week number. Week number should be between 1 and 52.", exception.getMessage());
    }

    @Test
    void constructorTest_InvalidWeek_UpperBound() {
        // Arrange
        int invalidWeek = 53;

        // Act & Assert
        IOException exception = assertThrows(IOException.class, () -> new SelectedWeek(invalidWeek));
        assertEquals("Invalid week number. Week number should be between 1 and 52.", exception.getMessage());
    }

}
