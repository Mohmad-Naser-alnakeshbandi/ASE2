import gui.complaint.GUIComplaint;
import persistence.complaint.ComplaintRepositoryBridge;
import persistence.customerReport.CustomerReportRepositoryBridge;
import persistence.printerReport.PrinterReportRepositoryBridge;
import persistence.weeklyReport.WeeklyReportRepositoryBridge;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        // Set macOS/Aqua Look and Feel
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            // Update the UI of existing components
            SwingUtilities.updateComponentTreeUI(new JFrame());
        } catch (Exception e) {
            // Print the stack trace if an exception occurs while setting the look and feel
            e.printStackTrace();
            // Handle the exception gracefully if needed
        }

        // Launch GUI in the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Initialize repository bridges
            ComplaintRepositoryBridge complaintRepositoryBridge = new ComplaintRepositoryBridge();
            CustomerReportRepositoryBridge customerReportRepositoryBridge = new CustomerReportRepositoryBridge();
            PrinterReportRepositoryBridge printerReportRepositoryBridge = new PrinterReportRepositoryBridge();
            WeeklyReportRepositoryBridge weeklyReportRepositoryBridge = new WeeklyReportRepositoryBridge();

            // Initialize and display the GUI
            GUIComplaint guiComplaint = new GUIComplaint(
                    complaintRepositoryBridge,
                    customerReportRepositoryBridge,
                    printerReportRepositoryBridge,
                    weeklyReportRepositoryBridge
            );
            guiComplaint.init();
        });
    }
}
