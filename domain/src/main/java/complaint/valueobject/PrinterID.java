package complaint.valueobject;

import errors.Errors;

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
        if (printerID.isBlank()) {
            throw new Errors(" Printer ID cannot be empty");
        }
    }
}
