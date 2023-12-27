package complaint.valueobject;

import errors.Errors;

import java.util.Objects;

public class CustomerEmail {

    private final String email;

    public CustomerEmail(String email) {
        validateEmail(email);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    private void validateEmail(String email) {
        // Basic email format validation: test@gmail.com
        if (email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new Errors("Invalid email format");
        }
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
