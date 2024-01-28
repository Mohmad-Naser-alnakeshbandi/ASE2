package complaint.valueobject;
import java.io.IOException;
import java.util.Objects;

public class CustomerEmail {

    private final String email;

    public String getEmail() {
        return email;
    }

    public CustomerEmail(String email) throws IOException {
        validateEmail(email);
        this.email = email;
    }

    private void validateEmail(String email) throws IOException {
        // Basic email format validation: test@gmail.com
        if (email == null || !email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IOException("Invalid email format");
        }
    }

    @Override
    public String toString() {
        return getEmail();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerEmail customerEmail)) return false;
        return Objects.equals(email, customerEmail.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
