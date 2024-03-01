package printerreport.Repository;

import customerreport.entity.CustomerReport;
import printerreport.entity.PrinterReport;
import printerreport.valueobject.PrinterID;

import java.io.IOException;

public interface PrinterReportRepository {
    void createPrinterReport(PrinterReport printerReport) throws IOException;
    void savePrinterReport(PrinterReport printerReport, String filePath) throws IOException;
}
