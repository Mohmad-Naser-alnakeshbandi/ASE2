package persistence.customerReport;

import customerreport.CustomerReportService;
import customerreport.Repository.customerReportRepository;
import customerreport.entity.CustomerReport;

public class CustomerReportRepositoryBridge implements customerReportRepository {


    public void createCustomerReport(CustomerReport customerReport) {
        CustomerReportService customerReportService = new CustomerReportService();
        customerReportService.getCustomerCompliantImplementation(customerReport);
    }
}
