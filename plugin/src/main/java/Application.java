import gui.complaint.GUIComplaint;
import persistence.complaint.ComplaintRepositoryBridge;
import persistence.customerReport.CustomerReportRepositoryBridge;
import persistence.printerReport.PrinterReportRepositoryBridge;
import persistence.weeklyReport.WeeklyReportRepositoryBridge;
import constants.constants;
import javax.swing.*;
import java.awt.*;

public class Application extends Component {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(constants.UhTheme);
            // Update the UI of existing components
            SwingUtilities.updateComponentTreeUI(new JFrame());
        } catch (Exception e) {
            // If setting the custom theme fails, set the default look and feel
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                SwingUtilities.updateComponentTreeUI(new JFrame());
            } catch (Exception ex) {
                // Handle any further exceptions if setting the default look and feel fails
                JOptionPane.showMessageDialog(null, "Can not start the application", "Something went Wrong", JOptionPane.ERROR_MESSAGE);
            }
        }

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
