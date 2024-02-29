package customerreport.Repository;

import customerreport.entity.CustomerReport;
import customerreport.valueobject.CustomerID;
import customerreport.valueobject.ReportDate;

import java.io.IOException;

public interface customerReportRepository {
    void createCustomerReport(CustomerReport customerReport) throws IOException;
}
