import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import constants.constants;
import colors.UsedColors;
import gui.complaint.GUIComplaint;
import gui.customerreport.GUICustomerReport;
import gui.printerreport.GUIPrinterReport;
import gui.weeklyreport.GUIWeeklyReport;
import persistence.complaint.ComplaintRepositoryBridge;
import persistence.customerReport.CustomerReportRepositoryBridge;
import persistence.printerReport.PrinterReportRepositoryBridge;
import persistence.weeklyReport.WeeklyReportRepositoryBridge;

public class Application extends Component {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(constants.UITheme);
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


            // Create an instance of GUIComplaint



            JFrame window = new JFrame(constants.StartScreenTitle);
            window.setSize(1000, 800);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setLocationRelativeTo(null);
            window.setResizable(false);
            window.setLayout(null);



            GUIComplaint guiComplaint = new GUIComplaint();
            ComplaintRepositoryBridge complaintRepositoryBridge = new ComplaintRepositoryBridge();

            // Initialize panels and buttons
            JPanel titlePanel = createPanel(0, 50);
            JPanel controlPanel = createPanel(50, 60);
            JPanel inputPanel =guiComplaint.initializeFieldsPanel();
            JPanel submitPanel = createPanel(700, 100);
            controlPanel.setLayout(null);

            // Set BorderLayout for titlePanel
            titlePanel.setLayout(new BorderLayout());
            submitPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            titlePanel.setBackground(Color.BLACK);

            // Add a JLabel to the titlePanel in the CENTER position
            JLabel titleLabel = new JLabel(constants.CompanyLogo);
            titleLabel.setHorizontalAlignment(JLabel.CENTER);
            titleLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
            titleLabel.setForeground(UsedColors.TITlE_COLOR_TEXT);

            // Create buttons
            JButton complaintButton = createButton(50, "All Complaints");
            JButton customerReportButton = createButton(275, "Customer Report");
            JButton printerReportButton = createButton(500, "Printer Report");
            JButton weeklyReportButton = createButton(725, "Weekly Report");


            JButton addComplaintButton = new JButton("Add complaint");
            addComplaintButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
            addComplaintButton.setPreferredSize(new Dimension(600, 50));
            addComplaintButton.setBackground(UsedColors.BACKGROUND_COLOR_Button);
            addComplaintButton.setForeground(UsedColors.FONT_COLOR_TEXT);


            // Add components to panels
            titlePanel.add(titleLabel, BorderLayout.CENTER);
            controlPanel.add(complaintButton);
            controlPanel.add(customerReportButton);
            controlPanel.add(printerReportButton);
            controlPanel.add(weeklyReportButton);
            submitPanel.add(addComplaintButton);



            // Add panels to the main window
            window.add(titlePanel);
            window.add(controlPanel);
            window.add(inputPanel);
            window.add(submitPanel);


            // Make the window visible
            window.setVisible(true);

            complaintButton.addActionListener(e -> {
                try {
                    guiComplaint.getComplaints(complaintRepositoryBridge);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,"can not access the Data store","Something went Wrong", JOptionPane.ERROR_MESSAGE);
                }
            });
            addComplaintButton.addActionListener(e -> guiComplaint.addComplaint(complaintRepositoryBridge));

            customerReportButton.addActionListener(e -> {
                CustomerReportRepositoryBridge customerReportRepositoryBridge = new CustomerReportRepositoryBridge(); // Instantiate your CustomerReportRepositoryBridge
                GUICustomerReport customReport = new GUICustomerReport(customerReportRepositoryBridge);
                customReport.init();
            });
            printerReportButton.addActionListener(e -> {
                PrinterReportRepositoryBridge printerReportRepositoryBridge = new PrinterReportRepositoryBridge();
                GUIPrinterReport printerReport= new GUIPrinterReport(printerReportRepositoryBridge);
                printerReport.init();
            });
            weeklyReportButton.addActionListener(e -> {
                WeeklyReportRepositoryBridge weeklyReportRepositoryBridge = new WeeklyReportRepositoryBridge();
                GUIWeeklyReport weeklyReport = new GUIWeeklyReport(weeklyReportRepositoryBridge);
                weeklyReport.init();
            });
        });
    }

    private static JButton createButton(int x, String title) {
        JButton button = new JButton(title);
        button.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        button.setBackground(UsedColors.COLOR_Button_Background);
        button.setForeground(UsedColors.COLOR_Button_Text);
        button.setBounds(x, 5, 200, 50);
        button.setOpaque(true);
        return button;
    }

    private static JPanel createPanel(int y, int height) {
        JPanel panel = new JPanel();
        panel.setBounds(0, y, 1000, height);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return panel;
    }
}
