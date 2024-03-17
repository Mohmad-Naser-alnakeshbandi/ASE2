import common.CustomerID;
import common.PrinterID;
import complaint.entity.Complaint;
import complaint.valueobject.*;

import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        System.out.println("HI");


            Complaint complaint = new Complaint.Builder()
                    .name(new CustomerName("Mohamad", "Naser"))
                    .description(new ComplaintDescription("The printer does start", "We do no know what the reason is"))
                    .callNumber(new CustomerCallNumber("+(123)-456-678"))
                    .email(new CustomerEmail("mmohamad@gmail.com"))
                    .customerID(new CustomerID("C123"))
                    .location(new CustomerLocation("Germany", "BW", "Crailsheim", "Haupstraße", "A212"))
                    .printerID(new PrinterID("P120"))
                    .complaintID(new ComplaintID())
                    .complaintDate(new ComplaintDate())
                    .complaintState(ComplaintState.RECEIVE)
                    .build();

        System.out.println(complaint.toString());
    }
}
