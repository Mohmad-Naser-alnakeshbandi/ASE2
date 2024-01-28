package complaint.valueobject;
import java.io.IOException;
import java.util.Objects;

public class CustomerName {
    private final String firstName;
    private final String LastName;

    public CustomerName(String firstName, String lastName) throws IOException {
        validate(firstName,lastName);
        this.firstName = firstName;
        this.LastName = lastName;
    }
    private void validate( String firstName, String lastName) throws IOException {
        validateFirstName(firstName);
        validateLastName(lastName);
    }
    private void validateFirstName(String firstName) throws IOException {
        if (firstName.isBlank()) {
            throw new IOException("FirstName cannot be empty");
        }
    }
    private void validateLastName(String lastName) throws IOException {
        if (lastName.isBlank()) {
            throw  new IOException("Last Name cannot be empty");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return LastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerName customerName)) return false;
        return Objects.equals(getFirstName(), customerName.getFirstName()) && Objects.equals(getLastName(), customerName.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName());
    }
}
