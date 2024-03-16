package gui.complaint;

import common.CustomerID;
import common.PrinterID;
import persistence.complaint.ComplaintRepositoryBridge;
import complaint.entity.Complaint;
import complaint.valueobject.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUIComplaint extends Component {


    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField printerIDTextField;
    private JTextField customerIDInputTextField;
    private JTextField emailInputTextField;
    private JTextField countryTextField;
    private JTextField stateTextField;
    private JTextField cityTextField;
    private JTextField streetTextField;
    private JTextField locationNumberTextField;
    private JTextField callNumberTextField;
    private JTextArea titleTextArea;
    private JTextArea descriptionTextArea;

    public JPanel initializeFieldsPanel() {
        JPanel complaintPanel = new JPanel();
        complaintPanel.setBounds(0, 110, 1000, 590);
        complaintPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        complaintPanel.setBackground(Color.BLUE);
        complaintPanel.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(500, 590));
        leftPanel.setLayout(new GridLayout(11, 2, 0, 10));

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(500, 590));

        JLabel firstNameLabel = new JLabel("  First Name");
        firstNameTextField = new JTextField();
        JLabel lastNameLabel = new JLabel("  Last Name");
        lastNameTextField = new JTextField();
        JLabel printerIDLabel = new JLabel("  Printer ID");
        printerIDTextField = new JTextField();
        JLabel customerIDLabel = new JLabel("  Customer ID");
        customerIDInputTextField = new JTextField();
        JLabel emailLabel = new JLabel("  Email");
        emailInputTextField = new JTextField();
        JLabel countryLabel = new JLabel("  Country");
        countryTextField = new JTextField();
        JLabel stateLabel = new JLabel("  State");
        stateTextField = new JTextField();
        JLabel cityLabel = new JLabel("  City");
        cityTextField = new JTextField();
        JLabel streetLabel = new JLabel("  Street");
        streetTextField = new JTextField();
        JLabel locationNumberLabel = new JLabel("  Location Number");
        locationNumberTextField = new JTextField();
        JLabel callNumberLabel = new JLabel("  Call Number");
        callNumberTextField = new JTextField();

        leftPanel.add(firstNameLabel);
        leftPanel.add(firstNameTextField);
        leftPanel.add(lastNameLabel);
        leftPanel.add(lastNameTextField);
        leftPanel.add(printerIDLabel);
        leftPanel.add(printerIDTextField);
        leftPanel.add(customerIDLabel);
        leftPanel.add(customerIDInputTextField);
        leftPanel.add(emailLabel);
        leftPanel.add(emailInputTextField);
        leftPanel.add(countryLabel);
        leftPanel.add(countryTextField);
        leftPanel.add(stateLabel);
        leftPanel.add(stateTextField);
        leftPanel.add(cityLabel);
        leftPanel.add(cityTextField);
        leftPanel.add(streetLabel);
        leftPanel.add(streetTextField);
        leftPanel.add(locationNumberLabel);
        leftPanel.add(locationNumberTextField);
        leftPanel.add(callNumberLabel);
        leftPanel.add(callNumberTextField);

        JLabel titleLabel = new JLabel("  Title");
        titleLabel.setPreferredSize(new Dimension(500, 45));
        titleTextArea = new JTextArea(13, 45);
        titleTextArea.setLineWrap(true);
        JScrollPane titleScrollPane = new JScrollPane(titleTextArea);
        titleScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JLabel descriptionLabel = new JLabel("  Description");
        descriptionLabel.setPreferredSize(new Dimension(500, 45));
        descriptionTextArea = new JTextArea(13, 45);
        descriptionTextArea.setLineWrap(true);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
        descriptionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        rightPanel.add(titleLabel);
        rightPanel.add(titleScrollPane);
        rightPanel.add(descriptionLabel);
        rightPanel.add(descriptionScrollPane);

        complaintPanel.add(leftPanel, BorderLayout.WEST);
        complaintPanel.add(rightPanel, BorderLayout.EAST);

        return complaintPanel;
    }

    // Getter methods for text fields
    public JTextField getFirstNameTextField() {
        return firstNameTextField;
    }

    public JTextField getLastNameTextField() {
        return lastNameTextField;
    }

    public JTextField getPrinterIDTextField() {
        return printerIDTextField;
    }

    public JTextField getCustomerIDInputTextField() {
        return customerIDInputTextField;
    }

    public JTextField getEmailInputTextField() {
        return emailInputTextField;
    }

    public JTextField getCountryTextField() {
        return countryTextField;
    }

    public JTextField getStateTextField() {
        return stateTextField;
    }

    public JTextField getCityTextField() {
        return cityTextField;
    }

    public JTextField getStreetTextField() {
        return streetTextField;
    }

    public JTextField getLocationNumberTextField() {
        return locationNumberTextField;
    }

    public JTextField getCallNumberTextField() {
        return callNumberTextField;
    }

    // Getter methods for text areas
    public JTextArea getTitleTextArea() {
        return titleTextArea;
    }

    public JTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    public void addComplaint(ComplaintRepositoryBridge complaintRepositoryBridge) {
        try {
            Complaint complaint = new Complaint.Builder()
                    .name(new CustomerName(getFirstNameTextField().getText(), getLastNameTextField().getText()))
                    .description(new ComplaintDescription(getTitleTextArea().getText(), getDescriptionTextArea().getText()))
                    .callNumber(new CustomerCallNumber(getCallNumberTextField().getText()))
                    .email(new CustomerEmail(getEmailInputTextField().getText()))
                    .customerID(new CustomerID(getCustomerIDInputTextField().getText()))
                    .location(new CustomerLocation(getCountryTextField().getText(), getStateTextField().getText(), getCityTextField().getText(), getStreetTextField().getText(), getLocationNumberTextField().getText()))
                    .printerID(new PrinterID(getPrinterIDTextField().getText()))
                    .complaintID(new ComplaintID())
                    .complaintDate(new ComplaintDate())
                    .complaintState(ComplaintState.RECEIVE)
                    .build();
            complaintRepositoryBridge.addComplaint(complaint);

            // Clear the input fields after adding the complaint
            clearInputFields();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Your input is incorrect", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Something went wrong", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to clear input fields
    private void clearInputFields() {
        getFirstNameTextField().setText("");
        getLastNameTextField().setText("");
        getTitleTextArea().setText("");
        getDescriptionTextArea().setText("");
        getCallNumberTextField().setText("");
        getEmailInputTextField().setText("");
        getCustomerIDInputTextField().setText("");
        getCountryTextField().setText("");
        getStateTextField().setText("");
        getCityTextField().setText("");
        getStreetTextField().setText("");
        getLocationNumberTextField().setText("");
        getPrinterIDTextField().setText("");
    }

}
