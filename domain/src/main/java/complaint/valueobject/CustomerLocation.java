package complaint.valueobject;
import java.io.IOException;
import java.util.Objects;

public class CustomerLocation {

    private final String country;
    private final String state;
    private final String city;
    private final String street;
    private final String number;

    public CustomerLocation(String country, String state, String city, String street, String number) throws IOException {
        validate(country,state,city,street,number);
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.number = number;
    }


    private void validate(String country, String state,String city, String street, String number) throws IOException {
        validateCountry(country);
        validateState(state);
        validateCity(city);
        validateStreet(street);
        validateNumber(number);
    }
    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    private void validateCountry(String country) throws IOException {
        if (country.isBlank()) {
            throw new IOException("Country cannot be empty");
        }
    }

    private void validateCity(String city) throws IOException {
        if (city.isBlank()) {
            throw new IOException("City cannot be empty");
        }
    }

    private void validateStreet(String street) throws IOException {
        if (street.isBlank()) {
            throw new IOException("Street cannot be empty");
        }
    }

    private void validateState( String state) throws IOException {
        if (state.isBlank()) {
            throw new IOException("State cannot be empty");
        }
    }
    private void validateNumber(String number) throws IOException {
        if (number.isBlank()) {
            throw new IOException("Location number cannot be empty");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerLocation customerLocation)) return false;
        return Objects.equals(country, customerLocation.country) &&
                Objects.equals(state, customerLocation.state) &&
                Objects.equals(city, customerLocation.city) &&
                Objects.equals(street, customerLocation.street) &&
                Objects.equals(number, customerLocation.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, state,city, street, number);
    }
}
