package printerreport.Repository;

import printerreport.entity.PrinterReport;

import java.io.IOException;

public interface PrinterReportRepository {
    void createPrinterReport(PrinterReport printerReport) throws IOException;
    void savePrinterReport(PrinterReport printerReport, String filePath) throws IOException;
}
