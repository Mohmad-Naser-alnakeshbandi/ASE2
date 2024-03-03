import gui.complaint.GUIComplaint;
import persistence.complaint.ComplaintRepositoryBridge;
import persistence.customerReport.CustomerReportRepositoryBridge;
import persistence.printerReport.PrinterReportRepositoryBridge;
import persistence.weeklyReport.WeeklyReportRepositoryBridge;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ComplaintRepositoryBridge complaintRepositoryBridge = new ComplaintRepositoryBridge();
                CustomerReportRepositoryBridge customerReportRepositoryBridge = new CustomerReportRepositoryBridge();
                PrinterReportRepositoryBridge printerReportRepositoryBridge = new PrinterReportRepositoryBridge();
                WeeklyReportRepositoryBridge weeklyReportRepositoryBridge = new WeeklyReportRepositoryBridge();
                new GUIComplaint(complaintRepositoryBridge, customerReportRepositoryBridge, printerReportRepositoryBridge, weeklyReportRepositoryBridge).init();
            }
        });


    }
}



