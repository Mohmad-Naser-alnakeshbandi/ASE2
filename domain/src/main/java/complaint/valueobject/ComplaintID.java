package complaint.valueobject;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class ComplaintID {
    private final AtomicLong customerIDValue;

    public ComplaintID(long initialValue) {
        this.customerIDValue = new AtomicLong(initialValue);
    }

    public String getCustomerIDValue() {
        // Format the ID to start with "C" followed by 3 numbers
        return String.format("C%03d", customerIDValue.get());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplaintID)) return false;
        return customerIDValue.get() == ((ComplaintID) o).customerIDValue.get();
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerIDValue);
    }
}
