package gui.printerreport;

import customerreport.entity.CustomerReport;
import customerreport.valueobject.CustomerID;
import customerreport.valueobject.ReportDate;
import persistence.printerReport.PrinterReportRepositoryBridge;
import printerreport.entity.PrinterReport;
import printerreport.valueobject.PrinterID;

import javax.swing.*;
import java.awt.*;

import java.io.IOException;

public class GUIPrinterReport extends JFrame {

    final PrinterReportRepositoryBridge printerReportRepositoryBridge;
    private JTextField printerIDField;
    private JLabel printerIDLabel;

    public GUIPrinterReport(PrinterReportRepositoryBridge printerReportRepositoryBridge) {

        this.printerReportRepositoryBridge = printerReportRepositoryBridge;
    }

    public void init() {
        setTitle("Printer Report");
        setSize(300,110);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        // Initialize components
        printerIDLabel = new JLabel("Printer ID:");
        printerIDField = new JTextField(20);
        JButton generateButton = new JButton("Generate");
        JButton saveButton = new JButton("Save");

        // Set layout
        setLayout(new BorderLayout());

        // Create panel for top components
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(printerIDLabel);
        topPanel.add(printerIDField);

        // Create panel for bottom buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(generateButton);
        bottomPanel.add(saveButton);

        // Add panels to the frame
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // Add action listeners
        generateButton.addActionListener(e -> {
            try{

                PrinterReport printerReport = new PrinterReport.Builder().
                        setPrinterID(new PrinterID(printerIDField.getText()))
                        .build();
                printerReportRepositoryBridge.createPrinterReport(printerReport);
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "your input in incorrect", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Something went wrong, ", JOptionPane.ERROR_MESSAGE);
            }
        });


        saveButton.addActionListener(e -> {
            try {
                PrinterReport printerReport = new PrinterReport.Builder().
                        setPrinterID(new PrinterID(printerIDField.getText()))
                        .build();

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Set to select directories only
                int result = fileChooser.showSaveDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String selectedPath = fileChooser.getSelectedFile().getAbsolutePath();
                    printerReportRepositoryBridge.savePrinterReport(printerReport, selectedPath);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error: Can't save the file", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Something went wrong, ", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}
