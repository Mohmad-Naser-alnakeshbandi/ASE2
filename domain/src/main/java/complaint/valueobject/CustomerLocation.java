package complaint.valueobject;

import errors.Errors;

import java.util.Objects;

public class CustomerLocation {

    private final String country;
    private final String city;
    private final String street;
    private final String number;

    public CustomerLocation(String country, String city, String street, String number) {

        validate(country,city,street,number);
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
    }


    private void validate(String country, String city, String street, String number) {
        validateCountry(country);
        validateCity(city);
        validateStreet(street);
        validateNumber(number);
    }
    public String getCountry() {
        return country;
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

    private void validateCountry(String country) {
        if (country.isBlank()) {
            throw new Errors("Country cannot be empty");
        }
    }

    private void validateCity(String city) {
        if (city.isBlank()) {
            throw new Errors("City cannot be empty");
        }
    }

    private void validateStreet(String street) {
        if (street.isBlank()) {
            throw new Errors("Street cannot be empty");
        }
    }

    private void validateNumber(String number) {
        if (!number.isBlank()) {
            throw new Errors("Location number cannot be empty");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerLocation customerLocation)) return false;
        return Objects.equals(country, customerLocation.country) &&
                Objects.equals(city, customerLocation.city) &&
                Objects.equals(street, customerLocation.street) &&
                Objects.equals(number, customerLocation.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, street, number);
    }
}
