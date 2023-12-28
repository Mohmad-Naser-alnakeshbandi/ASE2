import complaint.entity.Complaint;
import complaint.valueobject.*;
import customerreport.entity.CustomerReport;
import customerreport.valueobject.CustomerName;
import customerreport.valueobject.ReportDate;

import java.time.Instant;
import java.util.Date;

public class Exp {
    public static void main(String[] args)
    {

        Complaint complaint = new Complaint.Builder()
                .description(new ComplaintDescription("Printer not working", "It does not start at all"))
                .callNumber(new CustomerCallNumber("123-456-7890"))
                .email(new CustomerEmail("customer@example.com"))
                .customerID(new CustomerID("C212"))
                .location(new CustomerLocation("Germany","Karsruhe","Hauptstraße","12B"))
                .printerID(new PrinterID("P456"))
                .complaintID(new ComplaintID())
                .date(Date.from(Instant.now()))
                .build();

        //System.out.println(complaint.toString());

        CustomerReport customerReport = new CustomerReport.Builder()
                .customerID(new customerreport.valueobject.CustomerID("C231"))
                .customerName(new CustomerName("John Doe"))
                .reportDate(new ReportDate("2023-01-01", "2023-02-01"))
                .build();

        System.out.println(customerReport.toString());

    }

}
