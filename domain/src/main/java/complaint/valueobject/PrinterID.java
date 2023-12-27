package complaint.valueobject;

import errors.Errors;

import java.util.Objects;

public class PrinterID {
    private String printerID;

    public String getPrinterID() {
        return printerID;
    }

    public PrinterID(long initialValue) {
        validatePrinterID(initialValue);
        // Format the ID to start with "P" followed by 3 numbers
        this.printerID = String.format("P%03d", initialValue);
    }

    private void validatePrinterID(long initialValue) {
        // Add validation logic as needed
        if (initialValue < 0) {
            throw new Errors("Invalid printer ID value");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrinterID printerID1)) return false;
        return Objects.equals(printerID, printerID1.printerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(printerID);
    }
}
