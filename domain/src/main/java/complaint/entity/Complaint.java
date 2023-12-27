package complaint.entity;

import complaint.valueobject.*;

public class Complaint {

    private final ComplaintDescription description;
    private final CustomerCallNumber callNumber;
    private final CustomerEmail email;
    private final ComplaintID customerID;
    private final CustomerLocation location;
    private final PrinterID printerID;

    private Complaint(Builder builder) {
        this.description = builder.description;
        this.callNumber = builder.callNumber;
        this.email = builder.email;
        this.customerID = builder.customerID;
        this.location = builder.location;
        this.printerID = builder.printerID;
    }

    // Add getters for your fields

    public static class Builder {
        private ComplaintDescription description;
        private CustomerCallNumber callNumber;
        private CustomerEmail email;
        private ComplaintID customerID;
        private CustomerLocation location;
        private PrinterID printerID;

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

        public Complaint build() {
            return new Complaint(this);
        }
    }
}

/*
*
        Complaint complaintInstance = new Complaint.Builder()
        .description( your ComplaintDescription instance )
        .callNumber( your CustomerCallNumber instance )
        .email( your CustomerEmail instance )
        .customerID( your ComplaintID instance )
        .location( your CustomerLocation instance )
        .printerID(your PrinterID instance )
        .build();
*/