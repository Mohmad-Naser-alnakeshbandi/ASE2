package common;

import java.io.IOException;
import java.util.Objects;

public class CustomerID {
    private final String customerID;

    public CustomerID(String customerID)  throws IOException {
        validateCustomerID(customerID);
        this.customerID = customerID;
    }

    public String getCustomerID() {
        return customerID;
    }

    private void validateCustomerID(String customerID)  throws IOException {
        // Ensure the Customer ID follows the format "C" followed by 3 numbers
        if (!customerID.matches("C\\d{3}")) {
            throw new  IOException("Invalid Customer ID");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerID that)) return false;
        return Objects.equals(getCustomerID(), that.getCustomerID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerID());
    }

    @Override
    public String toString() {
        return customerID;
    }
}


