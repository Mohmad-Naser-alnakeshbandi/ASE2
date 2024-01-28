package errors;

import javax.swing.*;

public class Errors extends RuntimeException {
    private String errorMessage;

    public Errors() {
        super();
    }

    public Errors(String message) {
        super(message);
        this.errorMessage = message;
    }
}