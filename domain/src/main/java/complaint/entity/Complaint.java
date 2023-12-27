package complaint.entity;

import complaint.valueobject.*;

import java.util.Date; // Import Date class

public class Complaint {

    private final ComplaintDescription description;
    private final CustomerCallNumber callNumber;
    private final CustomerEmail email;
    private final ComplaintID customerID;
    private final CustomerLocation location;
    private final PrinterID printerID;
    private final ComplaintID complaintID;
    private final Date date; // Add date field

    private Complaint(Builder builder) {
        this.description = builder.description;
        this.callNumber = builder.callNumber;
        this.email = builder.email;
        this.customerID = builder.customerID;
        this.location = builder.location;
        this.printerID = builder.printerID;
        this.complaintID = builder.complaintID;
        this.date = builder.date; // Set date
    }

    // Add getters for your fields

    public static class Builder {

        private ComplaintDescription description;
        private CustomerCallNumber callNumber;
        private CustomerEmail email;
        private ComplaintID customerID;
        private CustomerLocation location;
        private PrinterID printerID;
        private ComplaintID complaintID;
        private Date date; // Add date field

        public Builder() {
            // Default constructor
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

        public Builder customerID(ComplaintID customerID) {
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
}


/*
 Complaint complaint = new Complaint.Builder()
                .description(description)
                .callNumber(callNumber)
                .email(email)
                .customerID(customerID)
                .location(location)
                .printerID(printerID)
                .complaintID(complaintID)
                .date(date)
                .build();
* */