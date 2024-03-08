package customerreport.Repository;
import customerreport.entity.CustomerReport;
import java.io.IOException;

public interface customerReportRepository {
    void createCustomerReport(CustomerReport customerReport) throws IOException;
    void saveCustomerReport(CustomerReport customerReport, String filePath) throws IOException;
}
