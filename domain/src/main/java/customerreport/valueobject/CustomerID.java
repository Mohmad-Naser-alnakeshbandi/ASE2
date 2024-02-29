package customerreport.valueobject;

import errors.Errors;

import java.io.IOException;
import java.util.Objects;

public class CustomerID {
    private String customerID;

    public CustomerID(String customerID)  throws IOException {
        validateCustomerID(customerID);
        this.customerID = customerID;
    }

    public String getCustomerID() {
        return customerID;
    }

    private void validateCustomerID(String customerID)  throws IOException {
        // Ensure the printer ID follows the format "P" followed by 3 numbers
        if (!customerID.matches("C\\d{3}")) {
            throw new  IOException("Invalid Customer ID");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof complaint.valueobject.CustomerID that)) return false;
        return Objects.equals(customerID, that.getCustomerID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID);
    }
}


