package customerreport.entity;

import customerreport.valueobject.CustomerID;
import customerreport.valueobject.CustomerName;
import customerreport.valueobject.ReportDate;

public class CustomerReport {

    private final CustomerID customerID;
    private final CustomerName customerName;
    private final ReportDate reportDate;

    private CustomerReport(CustomerReport.Builder builder) {
        this.customerID = builder.customerID;
        this.customerName = builder.customerName;
        this.reportDate = builder.reportDate;

    }

    public static class Builder {

        private CustomerID customerID;
        private CustomerName customerName;
        private ReportDate reportDate;


        public Builder() {
        }

        public CustomerReport.Builder customerID(CustomerID customerID) {
            this.customerID = customerID;
            return this;
        }

        public CustomerReport.Builder customerName(CustomerName customerName) {
            this.customerName = customerName;
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
                + "CustomerName: " + customerName.getCustomerName() + "\n"
                + "Report start Date: " + reportDate.getStartDate() + "\n"
                + "Report ende Date: " + reportDate.getEndDate();
    }
}
