package customerreport;

import customerreport.entity.CustomerReport;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import success.Success;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerReportService {
    private static final String COMPLAINT_FILE_PATH = "Data/complaint.json";
    int amountOfComplaints = 0;

    public void getCustomerCompliantImplementation(CustomerReport customerReport) {
        String customerID = customerReport.getCustomerID().getCustomerID();
        String reportStartDate = String.valueOf(customerReport.getReportDate().getStartDate()).substring(0, 10); // Extracting day and month
        String reportEndDate = String.valueOf(customerReport.getReportDate().getEndDate()).substring(0, 10); // Extracting day and month


        List<Map<String, String>> complaintsList = readCustomerComplaintsFromJson();
        if (!complaintsList.isEmpty()) {
            for (Map<String, String> complaintInfo : complaintsList) {
                String complaintDate = complaintInfo.get("Date").substring(0, 10); // Extracting day and month
                if (complaintInfo.get("CustomerID").equals(customerID) && isDateInRange(complaintDate, reportStartDate, reportEndDate)) {
                    System.out.println("CustomerID: " + complaintInfo.get("CustomerID"));
                    System.out.println("Date: " + complaintInfo.get("Date"));
                    System.out.println("------------------------");
                    amountOfComplaints++;
                }
            }
            showResult(customerID, amountOfComplaints);
        } else {
            System.out.println("No customer complaints found.");
        }
    }

    private void showResult(String customerID, int amountOfComplaints) {
        String[] columnHeaders = {"CustomerID", "Amount of Complaints"};
        Object[][] data = {{customerID, amountOfComplaints}};

        JFrame frame = new JFrame();
        JTable table = new JTable(data, columnHeaders);

        // Set the table to non-editable
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        // Set preferred size of the scroll pane to fit the table's size
        scrollPane.setPreferredSize(table.getPreferredSize());

        frame.setTitle("Customer Complaints");

        // Change the default close operation to dispose instead of exit
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.pack();

        // Set a minimum size for the frame
        frame.setMinimumSize(new Dimension(400, 200));

        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }



    private boolean isDateInRange(String date, String startDate, String endDate) {
        return date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0;
    }

    private List<Map<String, String>> readCustomerComplaintsFromJson() {
        List<Map<String, String>> complaintsList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(COMPLAINT_FILE_PATH))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            JSONArray jsonArray = new JSONArray(sb.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String customerID = jsonObject.getString("CustomerID");
                String date = jsonObject.getString("Date");

                Map<String, String> customerInfo = new HashMap<>();
                customerInfo.put("CustomerID", customerID);
                customerInfo.put("Date", date);
                complaintsList.add(customerInfo);
            }
        } catch (IOException | org.json.JSONException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }

        return complaintsList;
    }

    public void saveCustomerCompliantImplementation(CustomerReport customerReport, String filepath) throws IOException {

        String fileContent = getString(customerReport);
        System.out.println(customerReport);
        System.out.println(filepath);

        File directory = new File(filepath);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("The provided path is not a directory.");
        }

        File file = new File(directory, "Report" + customerReport.getCustomerID().getCustomerID() + ".csv");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(fileContent);
        bw.close();

        new Success("Report is generated", "A Report has been generated and saved in chosen location ");
        if (!file.exists()) {
            throw new IOException("Something went wrong, we cannot save the file!");
        }
    }

    @NotNull
    private String getString(CustomerReport customerReport) {
        if (customerReport == null) {
            throw new IllegalStateException("Firstly, you should generate a report before saving it");
        }

        String customerID = customerReport.getCustomerID().getCustomerID();
        String reportStartDate = String.valueOf(customerReport.getReportDate().getStartDate()).substring(0, 10); // Extracting day and month
        String reportEndDate = String.valueOf(customerReport.getReportDate().getEndDate()).substring(0, 10); // Extracting day and month

        return "CustomerID ," +" Amount Of Complaints , "+ "Report Start Date, " + "Report End Date"+ "\n"+
                customerID + "," + amountOfComplaints + "," + reportStartDate + ","+ reportEndDate
                ;
    }
}


