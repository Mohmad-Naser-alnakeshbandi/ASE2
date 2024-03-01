package printerreport;

import org.json.JSONArray;
import org.json.JSONObject;
import printerreport.entity.PrinterReport;
import success.Success;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrinterReportService {
    private static final String COMPLAINT_FILE_PATH = "Data/complaint.json";
    int amountOfComplaints = 0;

    public void getPrinterCompliantImplementation(PrinterReport printerReport) {
        String printerID = printerReport.getPrinterID().getPrinterID();

        List<String> customerIDs = readCustomerIDsFromJson(printerID);
        if (!customerIDs.isEmpty()) {
            System.out.println("Customer IDs associated with Printer ID " + printerID + ":");
            for (String customerID : customerIDs) {
                System.out.println(customerID);
                amountOfComplaints++;
            }
            showResult(printerID, amountOfComplaints);
        } else {
            System.out.println("No customer complaints found for Printer ID " + printerID);
        }
    }

    private List<String> readCustomerIDsFromJson(String printerID) {
        List<String> customerIDs = new ArrayList<>();

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
                String jsonPrinterID = jsonObject.optString("PrinterID", null);

                if (customerID != null && jsonPrinterID != null && jsonPrinterID.equals(printerID)) {
                    customerIDs.add(customerID);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }

        return customerIDs;
    }

    private void showResult(String printerID, int amountOfComplaints) {
        System.out.println("Total amount of complaints for Printer ID " + printerID + ": " + amountOfComplaints);
        new Success("Printer Report Generated", "A Printer Report has been generated for Printer ID " + printerID);
    }
}
