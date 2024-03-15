package customerreport;

import customerreport.entity.CustomerReport;
import org.json.JSONArray;
import org.json.JSONObject;
import constants.constants;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class CustomerReportService {
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

    public void getCustomerCompliantImplementation(CustomerReport customerReport) throws ParseException {
        String customerID = customerReport.getCustomerID().getCustomerID();
        Date reportStartDate = customerReport.getReportDate().getStartDate();
        Date reportEndDate = customerReport.getReportDate().getEndDate();

        List<Map<String, String>> complaintsList = readCustomerComplaintsFromJson();
        int amountOfComplaints = countComplaintsForCustomer(complaintsList, customerID, reportStartDate, reportEndDate);

        showResult(customerID, amountOfComplaints);
    }

    private int countComplaintsForCustomer(List<Map<String, String>> complaintsList, String customerID, Date startDate, Date endDate) throws ParseException {
        int amountOfComplaints = 0;
        for (Map<String, String> complaintInfo : complaintsList) {
            String complaintDateString = complaintInfo.get("Date");
            Date complaintDate;
            try {
                complaintDate = DATE_FORMATTER.parse(complaintDateString);
            } catch (ParseException e) {
                throw new ParseException("Error parsing date: " + complaintDateString, 0);
            }
            if (complaintInfo.get("CustomerID").equals(customerID) && isDateInRange(complaintDate, startDate, endDate)) {
                amountOfComplaints++;
            }
        }
        return amountOfComplaints;
    }

    private boolean isDateInRange(Date date, Date startDate, Date endDate) {
        return date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0;
    }

    private void showResult(String customerID, int amountOfComplaints) {
        JFrame frame = new JFrame();
        String[] columnHeaders = {"CustomerID", "Amount of Complaints"};
        Object[][] data = {{customerID, amountOfComplaints}};
        DefaultTableModel model = new DefaultTableModel(data, columnHeaders);
        JTable table = new JTable(model);
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(table.getPreferredSize());
        frame.setTitle("Customer Complaints");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setMinimumSize(new Dimension(400, 200));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private List<Map<String, String>> readCustomerComplaintsFromJson() throws ParseException {
        List<Map<String, String>> complaintsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(constants.COMPLAINT_FILE_PATH))) {
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
        } catch (IOException e) {
            throw new ParseException("Error reading store files", 0);
        }
        return complaintsList;
    }

    public void saveCustomerReportImplementation(CustomerReport customerReport, String filepath) throws IOException {
        String fileContent = getString(customerReport);
        File directory = new File(filepath);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("The provided path is not a directory.");
        }
        File file = new File(directory, "Report" + customerReport.getCustomerID().getCustomerID() + ".csv");
        try (FileWriter fw = new FileWriter(file); BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(fileContent);
        }
        if (!file.exists()) {
            throw new IOException("Something went wrong, we cannot save the file!");
        }
    }

    private String getString(CustomerReport customerReport) {
        if (customerReport == null) {
            throw new IllegalStateException("Firstly, you should generate a report before saving it");
        }
        String customerID = customerReport.getCustomerID().getCustomerID();
        String reportStartDate = String.valueOf(customerReport.getReportDate().getStartDate()).substring(0, 10);
        String reportEndDate = String.valueOf(customerReport.getReportDate().getEndDate()).substring(0, 10);
        return "CustomerID ," + " Amount Of Complaints , " + "Report Start Date, " + "Report End Date" + "\n" +
                customerID + "," + "N/A" + "," + reportStartDate + "," + reportEndDate;
    }
}
