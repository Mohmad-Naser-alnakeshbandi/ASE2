package complaint.entity;
import common.CustomerID;
import common.PrinterID;
import complaint.valueobject.*;

public class Complaint {

    private final CustomerName customerName;
    private final ComplaintDescription description;
    private final CustomerCallNumber callNumber;
    private final CustomerEmail email;
    private final CustomerID customerID;
    private final CustomerLocation location;
    private final PrinterID printerID;
    private final ComplaintID complaintID;
    private final ComplaintDate complaintDate;

    private final ComplaintState complaintState;

    private Complaint(Builder builder) {
        this.customerName = builder.customerName;
        this.description = builder.description;
        this.callNumber = builder.callNumber;
        this.email = builder.email;
        this.customerID = builder.customerID;
        this.location = builder.location;
        this.printerID = builder.printerID;
        this.complaintID = builder.complaintID;
        this.complaintDate = builder.complaintDate;
        this.complaintState= builder.complaintState;
    }
    public CustomerName getName() {
        return customerName;
    }
    public ComplaintDescription getDescription() {
        return description;
    }

    public CustomerCallNumber getCallNumber() {
        return callNumber;
    }

    public CustomerEmail getEmail() {
        return email;
    }

    public CustomerID getCustomerID() {
        return customerID;
    }

    public CustomerLocation getLocation() {
        return location;
    }

    public PrinterID getPrinterID() {
        return printerID;
    }

    public ComplaintID getComplaintID() {
        return complaintID;
    }

    public ComplaintDate getComplaintDate() {
        return complaintDate;
    }

    public ComplaintState getComplaintState() {
        return complaintState;
    }


    public static class Builder {

        private CustomerName customerName;
        private ComplaintDescription description;
        private CustomerCallNumber callNumber;
        private CustomerEmail email;
        private CustomerID customerID;
        private CustomerLocation location;
        private PrinterID printerID;
        private ComplaintID complaintID;
        private ComplaintDate complaintDate;
        private  ComplaintState complaintState;

        public Builder() {
            // Default constructor
        }
        public Builder name(CustomerName customerName) {
            this.customerName = customerName;
            return this;
        }
        public Builder description(ComplaintDescription description) {
            this.description = description;
            return this;
        }

        public Builder callNumber(CustomerCallNumber callNumber) {
            this.callNumber = callNumber;
            return this;
        }

        public Builder email(CustomerEmail email) {
            this.email = email;
            return this;
        }

        public Builder customerID(CustomerID customerID) {
            this.customerID = customerID;
            return this;
        }

        public Builder location(CustomerLocation location) {
            this.location = location;
            return this;
        }

        public Builder printerID(PrinterID printerID) {
            this.printerID = printerID;
            return this;
        }

        public Builder complaintID(ComplaintID complaintID) {
            this.complaintID = complaintID;
            return this;
        }

        public Builder complaintDate(ComplaintDate complaintDate) {
            this.complaintDate = complaintDate;
            return this;
        }

        public Builder complaintState(ComplaintState complaintState) {
            this.complaintState = complaintState;
            return this;
        }

        public Complaint build() {
            return new Complaint(this);
        }
    }

    @Override
    public String toString() {
        return  "The First name: " + customerName.getFirstName() + ", Last name: " + customerName.getLastName()
                + "description: " + description.getTitle() +": " + description.getBody() + "\n"
                + "callNumber: "+ callNumber.getCallNumber() + "\n"
                + "email: "+ email.getEmail() + "\n"
                + "customerID: "+ customerID.getCustomerID() + "\n"
                + "location: "+location.getCountry() +"-"+location.getState()+"-"+location.getCity()+"-"+location.getStreet()+"-"+location.getNumber() + "\n"
                + "printerID: "+ printerID.getPrinterID()+ "\n"
                + "complaintID: "+ complaintID.getComplaintId()+"\n"
                + "date: "+ complaintDate.getCurrentDate() + "\n"
                + "state: "+ complaintState;
    }
}
