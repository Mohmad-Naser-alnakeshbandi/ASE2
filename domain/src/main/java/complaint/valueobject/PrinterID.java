package complaint.valueobject;
import errors.Errors;
import java.util.Objects;

public class PrinterID {
    private String printerID;

    public String getPrinterID() {
        return printerID;
    }
    public PrinterID(String printerID) {
        validatePrinterID(printerID);
        this.printerID = printerID;
    }

    private void validatePrinterID(String printerID) {
        // Ensure the printer ID follows the format "P" followed by 3 numbers
        if (printerID.matches("P\\d{2}")) {
            throw new Errors("Invalid printer ID format");
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
