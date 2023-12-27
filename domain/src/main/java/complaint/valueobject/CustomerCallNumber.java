package complaint.valueobject;

import errors.Errors;

import java.util.Objects;

public class CustomerCallNumber
{
    private final String callNumber;

    public CustomerCallNumber(String callNumber) {
        validateCallNumber(callNumber);
        this.callNumber = callNumber;
    }

    public String getCallNumber() {
        return callNumber;
    }

    private void validateCallNumber(String callNumber) {
        //+(123) - 456-78-90
        if (callNumber == null || !callNumber.matches("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")) {
            throw new Errors("Invalid customer call number for DACH area");
        }
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
