package gui.complaint;

import colors.UsedColors;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;


public class GUIComplaint {

    void init() {
        // Initialize the main JFrame
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
        generalInformationPanelRight.setLayout(new GridLayout(4, 2, 20, 0));
        complaintInformationPanel.setLayout(new GridLayout(2, 2, 20, 0));
        submitPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        titlePanel.setBackground(Color.BLACK);

        // Add a JLabel to the titlePanel in the CENTER position
        JLabel titleLabel = new JLabel("IPRINTER");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        titleLabel.setForeground(UsedColors.Font_COLOR_Submit);

        // Create buttons
        JButton complaintButton = createButton(50, "Complaint");
        JButton customerReportButton = createButton(280, "Customer Report");
        JButton printerReportButton = createButton(510, "Printer Report");
        JButton weeklyReportButton = createButton(740, "Weekly Report");
        JButton addComplaintButton = new JButton("Add complaint");
        addComplaintButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        addComplaintButton.setPreferredSize(new Dimension(600, 50));
        addComplaintButton.setBackground(UsedColors.BACKGROUND_COLOR_Submit);
        addComplaintButton.setForeground(UsedColors.Font_COLOR_Submit);

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
        JTextField countryLabelTextField = new JTextField();
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
        controlPanel.add(complaintButton);
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
        generalInformationPanelRight.add(countryLabelTextField);
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


        // add ActionListener to the Buttons
        complaintButton.addActionListener(e -> {

        });
        complaintButton.addActionListener(e -> {

        });
        customerReportButton.addActionListener(e -> {

        });
        printerReportButton.addActionListener(e -> {

        });
        weeklyReportButton.addActionListener(e -> {

        });

        addComplaintButton.addActionListener(e -> {
            if (checkAndSetBorder(firstNameTextField) || checkAndSetBorder(lastNameTextField) || checkAndSetBorder(printerIDTextField) || checkAndSetBorder(customerIDInputTextField) || checkAndSetBorder(emailInputTextField) || checkAndSetBorder(countryLabelTextField) || checkAndSetBorder(streetTextField) || checkAndSetBorder(locationNumberTextField) || checkAndSetBorder(callNumberTextField) || checkAndSetBorder(titleTextArea) || checkAndSetBorder(descriptionTextArea)) {
                return;  // Exit the method if any field is blank
            }
            // Continue with the rest of your code if all fields are non-blank - success case!
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
        button.setBounds(x, 5, 210, 50);
        button.setOpaque(true);
        return button;
    }

    private JPanel createPanel(int y, int height) {
        JPanel panel = new JPanel();
        panel.setBounds(0, y, 1000, height);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return panel;
    }

    private boolean checkAndSetBorder(JTextComponent textComponent) {
        boolean isBlank = textComponent.getText().isBlank();
        textComponent.setBorder(isBlank ? BorderFactory.createLineBorder(UsedColors.ERROR_COLOR) : null);
        return isBlank;
    }

    public static void main(String[] args) {
        new GUIComplaint().init();
    }
}
