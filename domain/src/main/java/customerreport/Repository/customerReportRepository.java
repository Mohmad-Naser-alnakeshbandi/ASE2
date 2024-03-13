package customerreport.Repository;
import customerreport.entity.CustomerReport;
import java.io.IOException;
import java.text.ParseException;

public interface customerReportRepository {
    void createCustomerReport(CustomerReport customerReport) throws IOException, ParseException;
    void saveCustomerReport(CustomerReport customerReport, String filePath) throws IOException;
}
