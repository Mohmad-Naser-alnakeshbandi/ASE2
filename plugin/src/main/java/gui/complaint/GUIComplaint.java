package gui.complaint;
import colors.UsedColors;
import complaint.entity.Complaint;
import complaint.valueobject.*;
import gui.customerreport.GUICustomerReport;
import gui.printerreport.GUIPrinterReport;
import gui.weeklyreport.GUIWeeklyReport;
import persistence.complaint.ComplaintRepositoryBridge;
import persistence.customerReport.CustomerReportRepositoryBridge;
import persistence.printerReport.PrinterReportRepositoryBridge;
import persistence.weeklyReport.WeeklyReportRepositoryBridge;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;



public class GUIComplaint extends Component {

    private final ComplaintRepositoryBridge complaintRepositoryBridge;
    private final CustomerReportRepositoryBridge customerReportRepositoryBridge;
    final PrinterReportRepositoryBridge printerReportRepositoryBridge;
    final WeeklyReportRepositoryBridge weeklyReportRepositoryBridge;

    public GUIComplaint(ComplaintRepositoryBridge complaintRepositoryBridge, CustomerReportRepositoryBridge customerReportRepositoryBridge, PrinterReportRepositoryBridge printerReportRepositoryBridge, WeeklyReportRepositoryBridge weeklyReportRepositoryBridge) {
        this.complaintRepositoryBridge = complaintRepositoryBridge;
        this.customerReportRepositoryBridge = customerReportRepositoryBridge;
        this.printerReportRepositoryBridge = printerReportRepositoryBridge;
        this.weeklyReportRepositoryBridge = weeklyReportRepositoryBridge;
    }

    public void init() {
        // Initialize the main
        JFrame window = new JFrame("Complaint Management System");
        window.setSize(1000, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setLayout(null);

        // Initialize panels and buttons
        JPanel titlePanel = createPanel(0, 50);
        JPanel controlPanel = createPanel(50, 60);
        JPanel generalInformationPanel = createPanel(110, 300);
        JPanel complaintInformationPanel = createPanel(410, 290);
        JPanel submitPanel = createPanel(700, 100);
        JPanel generalInformationPanelLeft = new JPanel();
        JPanel generalInformationPanelRight = new JPanel();

        // Set BorderLayout for titlePanel
        titlePanel.setLayout(new BorderLayout());
        generalInformationPanel.setLayout(new GridLayout(1, 2));
        generalInformationPanelLeft.setLayout(new GridLayout(5, 2, 20, 0));
        generalInformationPanelRight.setLayout(new GridLayout(6, 2, 20, 0));
        complaintInformationPanel.setLayout(new GridLayout(2, 2, 20, 0));
        submitPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        titlePanel.setBackground(Color.BLACK);

        // Add a JLabel to the titlePanel in the CENTER position
        JLabel titleLabel = new JLabel("IPRINTER");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        titleLabel.setForeground(UsedColors.Font_COLOR_Submit);

        // Create buttons
        JButton customerReportButton = createButton(50, "Customer Report");
        JButton printerReportButton = createButton(375, "Printer Report");
        JButton weeklyReportButton = createButton(700, "Weekly Report");
        JButton addComplaintButton = new JButton("Add complaint");
        addComplaintButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        addComplaintButton.setPreferredSize(new Dimension(600, 50));
        addComplaintButton.setBackground(UsedColors.BACKGROUND_COLOR_Submit);
        addComplaintButton.setForeground(UsedColors.FONT_COLOR);

        // Initialize labels and Input fields
        JLabel firstNameLabel = new JLabel("First Name");
        JTextField firstNameTextField = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name");
        JTextField lastNameTextField = new JTextField();
        JLabel printerIDLabel = new JLabel("Printer ID");
        JTextField printerIDTextField = new JTextField();
        JLabel customerIDLabel = new JLabel("Customer ID");
        JTextField customerIDInputTextField = new JTextField();
        JLabel emailLabel = new JLabel("Email");
        JTextField emailInputTextField = new JTextField();
        JLabel countryLabel = new JLabel("Country");
        JTextField countryTextField = new JTextField();
        JLabel stateLabel = new JLabel("State");
        JTextField stateTextField = new JTextField();
        JLabel cityLabel = new JLabel("City");
        JTextField cityTextField = new JTextField();
        JLabel streetLabel = new JLabel("Street");
        JTextField streetTextField = new JTextField();
        JLabel locationNumberLabel = new JLabel("Location Number");
        JTextField locationNumberTextField = new JTextField();
        JLabel callNumberLabel = new JLabel("Call Number");
        JTextField callNumberTextField = new JTextField();
        JLabel titlelLabel = new JLabel("Title");
        JTextArea titleTextArea = new JTextArea();
        titleTextArea.setLineWrap(true); // Enable line wrapping
        JScrollPane titleScrollPane = new JScrollPane(titleTextArea);
        titleScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Set vertical scrollbar policy
        JLabel descriptionLabel = new JLabel("Description");
        JTextArea descriptionTextArea = new JTextArea();
        descriptionTextArea.setLineWrap(true); // Enable line wrapping
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
        descriptionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Set vertical scrollbar policy

        // Add components to panels
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        controlPanel.add(customerReportButton);
        controlPanel.add(printerReportButton);
        controlPanel.add(weeklyReportButton);

        generalInformationPanel.add(generalInformationPanelLeft);
        generalInformationPanel.add(generalInformationPanelRight);

        generalInformationPanelLeft.add(firstNameLabel);
        generalInformationPanelLeft.add(firstNameTextField);
        generalInformationPanelLeft.add(lastNameLabel);
        generalInformationPanelLeft.add(lastNameTextField);
        generalInformationPanelLeft.add(printerIDLabel);
        generalInformationPanelLeft.add(printerIDTextField);
        generalInformationPanelLeft.add(customerIDLabel);
        generalInformationPanelLeft.add(customerIDInputTextField);
        generalInformationPanelLeft.add(emailLabel);
        generalInformationPanelLeft.add(emailInputTextField);

        generalInformationPanelRight.add(countryLabel);
        generalInformationPanelRight.add(countryTextField);
        generalInformationPanelRight.add(stateLabel);
        generalInformationPanelRight.add(stateTextField);
        generalInformationPanelRight.add(cityLabel);
        generalInformationPanelRight.add(cityTextField);
        generalInformationPanelRight.add(streetLabel);
        generalInformationPanelRight.add(streetTextField);
        generalInformationPanelRight.add(locationNumberLabel);
        generalInformationPanelRight.add(locationNumberTextField);
        generalInformationPanelRight.add(callNumberLabel);
        generalInformationPanelRight.add(callNumberTextField);

        complaintInformationPanel.add(titlelLabel);
        complaintInformationPanel.add(titleScrollPane);
        complaintInformationPanel.add(descriptionLabel);
        complaintInformationPanel.add(descriptionScrollPane);
        submitPanel.add(addComplaintButton);


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

        addComplaintButton.addActionListener(e -> {
            try {

                Complaint complaint = new Complaint.Builder()
                        .name(new CustomerName(firstNameTextField.getText(), lastNameTextField.getText()))
                        .description(new ComplaintDescription(titleTextArea.getText(), descriptionTextArea.getText()))
                        .callNumber(new CustomerCallNumber(callNumberTextField.getText()))
                        .email(new CustomerEmail(emailInputTextField.getText()))
                        .customerID(new CustomerID(customerIDInputTextField.getText()))
                        .location(new CustomerLocation(countryTextField.getText(), stateTextField.getText(), cityTextField.getText(), streetTextField.getText(), locationNumberTextField.getText()))
                        .printerID(new PrinterID(printerIDTextField.getText()))
                        .complaintID(new ComplaintID())
                        .complaintDate(new ComplaintDate())
                        .complaintState(ComplaintState.RECEIVE)
                        .build();
                complaintRepositoryBridge.addComplaint(complaint);

                // After adding the complaint the input text field have to be empty
                firstNameTextField.setText("");
                lastNameTextField.setText("");
                titleTextArea.setText("");
                descriptionTextArea.setText("");
                callNumberTextField.setText("");
                emailInputTextField.setText("");
                customerIDInputTextField.setText("");
                countryTextField.setText("");
                stateTextField.setText("");
                cityTextField.setText("");
                streetTextField.setText("");
                locationNumberTextField.setText("");
                printerIDTextField.setText("");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "your input in incorrect", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Something went wrong", JOptionPane.ERROR_MESSAGE);
            }
        });


        // Add panels to the main window
        window.add(titlePanel);
        window.add(controlPanel);
        window.add(generalInformationPanel);
        window.add(complaintInformationPanel);
        window.add(submitPanel);

        // Set layout manager to null for controlPanel after adding components
        controlPanel.setLayout(null);

        // Make the window visible
        window.setVisible(true);
    }

    private JButton createButton(int x, String title) {
        JButton button = new JButton(title);
        button.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        button.setBackground(UsedColors.COLOR_Button_Background);
        button.setForeground(UsedColors.COLOR_Button_Text);
        button.setBounds(x, 5, 250, 50);
        button.setOpaque(true);
        return button;
    }

    private JPanel createPanel(int y, int height) {
        JPanel panel = new JPanel();
        panel.setBounds(0, y, 1000, height);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return panel;
    }



}
