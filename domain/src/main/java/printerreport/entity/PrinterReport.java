package printerreport.entity;

import printerreport.valueobject.PrinterID;

public class PrinterReport {

    private final PrinterID printerID;

    // Private constructor to prevent instantiation from outside
    public PrinterReport(Builder builder) {
        this.printerID = builder.printerID;
    }

    public PrinterID getPrinterID() {
        return printerID;
    }

    // Nested Builder class
    public static class Builder {
        private PrinterID printerID;

        // Method to set PrinterID
        public Builder setPrinterID(PrinterID printerID) {
            this.printerID = printerID;
            return this; // Return the builder instance for method chaining
        }

        // Method to build PrinterReport object
        public PrinterReport build() {
            return new PrinterReport(this);
        }
    }

    @Override
    public String toString() {
        return "PrinterReport{" +
                "printerID=" + printerID +
                '}';
    }
}
