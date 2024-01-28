package complaint.valueobject;


import java.io.IOException;
import java.util.Objects;

public class CustomerCallNumber
{
    private final String callNumber;

    public CustomerCallNumber(String callNumber)  throws IOException {
        validateCallNumber(callNumber);
        this.callNumber = callNumber;
    }

    public String getCallNumber() {
        return callNumber;
    }

    private void validateCallNumber(String callNumber) throws IOException {
        //+(123) - 456-78-90
        if (callNumber == null || !callNumber.matches("^[+]*[(]?[0-9]{1,4}[)]?[-\\s./0-9]*$")) {
            throw new IOException("Invalid customer call number for DACH area");
        }
    }

    @Override
    public String toString() {
        return getCallNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerCallNumber customerCallNumber)) return false;
        return Objects.equals(callNumber, customerCallNumber.callNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(callNumber);
    }
}
