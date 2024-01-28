package complaint.entity;

import complaint.valueobject.*;

import java.util.Date; // Import Date class

public class Complaint {

    private final CustomerName customerName;
    private final ComplaintDescription description;
    private final CustomerCallNumber callNumber;
    private final CustomerEmail email;
    private final CustomerID customerID;
    private final CustomerLocation location;
    private final PrinterID printerID;
    private final ComplaintID complaintID;
    private final Date date; // Add date field

    private Complaint(Builder builder) {
        this.customerName = builder.customerName;
        this.description = builder.description;
        this.callNumber = builder.callNumber;
        this.email = builder.email;
        this.customerID = builder.customerID;
        this.location = builder.location;
        this.printerID = builder.printerID;
        this.complaintID = builder.complaintID;
        this.date = builder.date; // Set date
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

    public static class Builder {

        private CustomerName customerName;
        private ComplaintDescription description;
        private CustomerCallNumber callNumber;
        private CustomerEmail email;
        private CustomerID customerID;
        private CustomerLocation location;
        private PrinterID printerID;
        private ComplaintID complaintID;
        private Date date; // Add date field

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

        public Builder date(Date date) {
            this.date = date;
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
                + "date:"+ date.toString();
    }
}