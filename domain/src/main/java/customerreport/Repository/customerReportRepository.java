package customerreport.Repository;

import customerreport.valueobject.CustomerName;

import java.io.IOException;

public interface customerReportRepository {
    void createCustomerReport(CustomerName customerName) throws IOException;
}
