package persistence.customerReport;

import customerreport.CustomerReportService;
import customerreport.Repository.customerReportRepository;
import customerreport.entity.CustomerReport;

import java.io.IOException;

public class CustomerReportRepositoryBridge implements customerReportRepository {


    public void createCustomerReport(CustomerReport customerReport) {
        CustomerReportService customerReportService = new CustomerReportService();
        customerReportService.getCustomerCompliantImplementation(customerReport);
    }

    @Override
    public void saveCustomerReport(CustomerReport customerReport, String filePath) throws IOException {
        CustomerReportService customerReportService = new CustomerReportService();
        customerReportService.saveCustomerCompliantImplementation(customerReport, filePath);
    }
}
