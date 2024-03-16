import customerreport.valueobject.ReportDate;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class ReportDateTest {

    @Test
    void constructorTest_ValidDates() throws IOException {
        // Arrange
        Date startDate = new Date(121, 0, 1); // January 1, 2021
        Date endDate = new Date(121, 11, 31); // December 31, 2021

        // Act
        ReportDate reportDate = new ReportDate(startDate, endDate);

        // Assert
        assertNotNull(reportDate);
        assertEquals(startDate, reportDate.getStartDate());
        assertEquals(endDate, reportDate.getEndDate());
    }

    @Test
    void constructorTest_NullDates() {
        // Act & Assert
        assertThrows(IOException.class, () -> new ReportDate(null, null));
    }

    @Test
    void constructorTest_EndDateBeforeStartDate() {
        // Arrange
        Date startDate = new Date(121, 1, 1); // February 1, 2021
        Date endDate = new Date(121, 0, 1); // January 1, 2021

        // Act & Assert
        IOException exception = assertThrows(IOException.class, () -> new ReportDate(startDate, endDate));
        assertEquals("Start date must be before end date.", exception.getMessage());
    }
}
