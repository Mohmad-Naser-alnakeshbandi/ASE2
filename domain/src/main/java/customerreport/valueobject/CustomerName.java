package customerreport.valueobject;

import errors.Errors;

import java.util.Objects;

public class CustomerName {
    private String customerName;
    public String getCustomerName() {
        return customerName;
    }
    public CustomerName(String customerName) {
        validateCustomerName(customerName);
        this.customerName = customerName;
    }
    private void validateCustomerName(String customerName) {

        if (customerName.isBlank()) {
            throw new Errors("Customer Name can not be blank!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerName that)) return false;
        return Objects.equals(getCustomerName(), that.getCustomerName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerName());
    }
}
