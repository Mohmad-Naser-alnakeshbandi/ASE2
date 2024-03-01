package persistence.printerReport;
import printerreport.PrinterReportService;
import printerreport.Repository.PrinterReportRepository;
import printerreport.entity.PrinterReport;

import java.io.IOException;

public class PrinterReportRepositoryBridge implements PrinterReportRepository {

    @Override
    public void createPrinterReport(PrinterReport printerReport) throws IOException {
        PrinterReportService printerReportServiceService  = new PrinterReportService();
        printerReportServiceService.getPrinterCompliantImplementation(printerReport);
    }

    @Override
    public void savePrinterReport(PrinterReport printerReport, String filePath) throws IOException {
        PrinterReportService printerReportServiceService  = new PrinterReportService();
    //    printerReportServiceService.s(customerReport, filePath);
    }
}
