package complaint.valueobject;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class ComplaintID {
    private final AtomicLong customerIDValue;

    public ComplaintID(long initialValue) {

        this.customerIDValue = new AtomicLong(initialValue);
    }

    public long getCustomerIDValue() {
        return customerIDValue.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplaintID customerID)) return false;
        return customerIDValue.get() == customerID.customerIDValue.get();
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerIDValue);
    }
}

