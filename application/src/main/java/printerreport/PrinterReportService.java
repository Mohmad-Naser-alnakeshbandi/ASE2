package printerreport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import printerreport.entity.PrinterReport;
import success.Success;
import constants.constants;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrinterReportService {

    private List<Map<String, String>> complaints = new ArrayList<>(); // Initialization here

    public List<Map<String, String>> getComplaints() {
        return complaints;
    }

    public void getPrinterCompliantImplementation(PrinterReport printerReport) throws IOException {
        String printerID = printerReport.getPrinterID().getPrinterID();
        complaints = readComplaintFromJson(printerID);

        if (!complaints.isEmpty()) {
            showResult(printerID, complaints);
        } else {
            throw  new IOException("No customer complaints found for Printer ID " + printerID);
        }
    }

    private List<Map<String, String>> readComplaintFromJson(String printerID) throws IOException {
        List<Map<String, String>> complaints = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(constants.COMPLAINT_FILE_PATH))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            JSONArray jsonArray = new JSONArray(sb.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String jsonPrinterID = jsonObject.optString("common.PrinterID", null);

                if (jsonPrinterID != null && jsonPrinterID.equals(printerID)) {
                    Map<String, String> complaint = new HashMap<>();
                    complaint.put("common.CustomerID", jsonObject.getString("common.CustomerID"));
                    JSONObject complaintObject = jsonObject.getJSONObject("Complaint");
                    complaint.put("Title", complaintObject.getString("title"));
                    complaint.put("Description", complaintObject.getString("description"));
                    complaints.add(complaint);
                }
            }
        } catch (IOException | JSONException e) {
            throw new IOException("Error reading store files");
        }

        return complaints;
    }

    private void showResult(String printerID, List<Map<String, String>> complaints) {
        JFrame frame = new JFrame();
        frame.setTitle("Printer Complaints Report");

        JLabel result = new JLabel("Total amount of complaints for Printer ID " + printerID + ": " + complaints.size());

        String[] columnHeaders = {"common.CustomerID", "Complaint title", "Complaint description"};
        Object[][] data = new Object[complaints.size()][3];

        for (int i = 0; i < complaints.size(); i++) {
            Map<String, String> complaint = complaints.get(i);
            data[i][0] = complaint.get("common.CustomerID");
            data[i][1] = complaint.get("Title");
            data[i][2] = complaint.get("Description");
        }

        JTable table = new JTable(data, columnHeaders);
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 400));

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(result, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void savePrinterReportImplementation(PrinterReport printerReport, String filePath) throws IOException {
        String printerID = printerReport.getPrinterID().getPrinterID();

        // Retrieve complaints after getting the printer complaints
        getPrinterCompliantImplementation(printerReport);
        List<Map<String, String>> complaints = getComplaints();

        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(filePath + "/" + printerID + ".csv"));
            StringBuilder fileContent = new StringBuilder();

            // Append header
            fileContent.append("Total amount of complaints for Printer with the ID is:  ").append(printerID).append(" ").append(complaints.size()).append("\n");
            fileContent.append("common.CustomerID,Complaint title,Complaint description\n");

            // Append data
            for (Map<String, String> complaint : complaints) {
                fileContent.append(complaint.get("common.CustomerID")).append(",");
                fileContent.append(complaint.get("Title")).append(",");
                fileContent.append(complaint.get("Description")).append("\n");
            }

            // Write content to file
            writer.write(fileContent.toString());
            new Success("Report is generated", "A Report has been generated and saved in chosen location ");

        } catch (IOException e) {
            // If an error occurs during file writing, throw IOException
            throw new IOException("Error saving printer report: " + e.getMessage());
        }
    }
}



