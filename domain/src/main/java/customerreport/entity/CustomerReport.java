package customerreport.entity;
import common.CustomerID;
import customerreport.valueobject.ReportDate;

public class CustomerReport {

    private final CustomerID customerID;
    private final ReportDate reportDate;

    public CustomerReport(CustomerReport.Builder builder) {
        this.customerID = builder.customerID;
        this.reportDate = builder.reportDate;

    }

    public CustomerID getCustomerID() {
        return customerID;
    }

    public ReportDate getReportDate() {
        return reportDate;
    }

    public static class Builder {

        private CustomerID customerID;
        private ReportDate reportDate;


        public Builder() {
        }

        public CustomerReport.Builder customerID(CustomerID customerID) {
            this.customerID = customerID;
            return this;
        }


        public CustomerReport.Builder reportDate(ReportDate reportDate) {
            this.reportDate = reportDate;
            return this;
        }

        public CustomerReport build() {
            return new CustomerReport(this);
        }
    }

    @Override
    public String toString() {
        return "CustomerID: " + customerID.getCustomerID() + "\n"
                + "Report start Date: " + reportDate.getStartDate() + "\n"
                + "Report end Date: " + reportDate.getEndDate();
    }
}
