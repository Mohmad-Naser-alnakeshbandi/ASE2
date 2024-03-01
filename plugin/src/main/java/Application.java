import gui.complaint.GUIComplaint;
import persistence.complaint.ComplaintRepositoryBridge;
import persistence.customerReport.CustomerReportRepositoryBridge;
import persistence.printerReport.PrinterReportRepositoryBridge;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ComplaintRepositoryBridge complaintRepositoryBridge = new ComplaintRepositoryBridge();
                CustomerReportRepositoryBridge customerReportRepositoryBridge = new CustomerReportRepositoryBridge();
                PrinterReportRepositoryBridge printerReportRepositoryBridge = new PrinterReportRepositoryBridge();
                new GUIComplaint(complaintRepositoryBridge, customerReportRepositoryBridge, printerReportRepositoryBridge).init();
            }
        });


    }
}



