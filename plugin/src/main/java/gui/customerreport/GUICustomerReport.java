package gui.customerreport;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import customerreport.entity.CustomerReport;
import customerreport.valueobject.CustomerID;
import customerreport.valueobject.ReportDate;
import persistence.customerReport.CustomerReportRepositoryBridge;
import constants.constants;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;

public class GUICustomerReport extends JFrame {

    final CustomerReportRepositoryBridge customerReportRepositoryBridge;
    private JTextField customerIdField;
    private JDateChooser startDateChooser;
    private JDateChooser endDateChooser;

    public GUICustomerReport(CustomerReportRepositoryBridge customerReportRepositoryBridge) {
        this.customerReportRepositoryBridge = customerReportRepositoryBridge;
    }

    public void init() {
        setTitle(constants.CustomerReportTitle);
        setSize(600, 165); // Set the size to 400x400
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creating components
        JLabel customerIdLabel = new JLabel("Customer ID:");
        customerIdField = new JTextField(20);
        JLabel startDateLabel = new JLabel("Start Date:");
        startDateChooser = new JDateChooser();
        JLabel endDateLabel = new JLabel("End Date:");
        endDateChooser = new JDateChooser();
        JButton generateBtn = new JButton("Generate Report");
        generateBtn.setPreferredSize(new Dimension(150, 30)); // Set preferred size for the button
        JButton savedBtn = new JButton("Saved");
        savedBtn.setPreferredSize(new Dimension(150, 30)); // Set preferred size for the button

        // Panels
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.add(customerIdLabel);
        inputPanel.add(customerIdField);
        inputPanel.add(startDateLabel);
        inputPanel.add(startDateChooser);
        inputPanel.add(endDateLabel);
        inputPanel.add(endDateChooser);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(generateBtn);
        buttonPanel.add(savedBtn); // Add the saved button

        // Layout
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some space at the top
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER); // Move the button panel to the CENTER

        // Adding components to the frame
        add(panel);

        setVisible(true);

        generateBtn.addActionListener(e -> {
            try{
                CustomerReport customerReport= new CustomerReport.Builder()
                        .customerID( new CustomerID(customerIdField.getText()))
                        .reportDate( new ReportDate(startDateChooser.getDate(),endDateChooser.getDate())).
                        build();
                customerReportRepositoryBridge.createCustomerReport(customerReport);
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "your input in incorrect", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Something went wrong ", JOptionPane.ERROR_MESSAGE);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Something went wrong in the data store ", JOptionPane.ERROR_MESSAGE);
            }
        });

        savedBtn.addActionListener(e -> {
            try {
                CustomerReport customerReport= new CustomerReport.Builder()
                        .customerID( new CustomerID(customerIdField.getText()))
                        .reportDate( new ReportDate(startDateChooser.getDate(),endDateChooser.getDate())).
                        build();

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Set to select directories only
                int result = fileChooser.showSaveDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String selectedPath = fileChooser.getSelectedFile().getAbsolutePath();
                    customerReportRepositoryBridge.saveCustomerReport(customerReport, selectedPath);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error: Can't save the file", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Something went wrong, ", JOptionPane.ERROR_MESSAGE);
            }
        });

    }
}
