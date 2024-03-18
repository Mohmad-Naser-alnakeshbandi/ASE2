package complaint;

import common.CustomerID;
import common.PrinterID;
import complaint.entity.Complaint;
import complaint.valueobject.*;
import org.json.JSONArray;
import org.json.JSONObject;
import constants.constants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ComplaintService {

    public void addCompliantImplementation(Complaint complaint) throws IOException {

        JSONArray existingArray = readJsonArrayFromFile();
        JSONObject newComplaintJson = createComplaintJsonObject(complaint);
        existingArray.put(newComplaintJson);
        writeJsonArrayToFile(existingArray);
    }

    private JSONArray readJsonArrayFromFile() throws IOException {
        String jsonContent = new String(Files.readAllBytes(Paths.get(constants.COMPLAINT_FILE_PATH)));

        if (jsonContent.trim().isEmpty() || !jsonContent.startsWith("[")) {
            throw new IOException("Invalid or empty Data Store.");
        }
        // Parse the JSON array
        return new JSONArray(jsonContent);
    }

    private JSONObject createComplaintJsonObject(Complaint complaint) throws IOException {
        boolean isContractValid = ContractValidator.validateContract(complaint.getCustomerID().getCustomerID(), complaint.getPrinterID().toString());
        if (isContractValid) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ComplaintID", complaint.getComplaintID());
            jsonObject.put("Date", complaint.getComplaintDate());
            jsonObject.put("First Name", complaint.getName().getFirstName());
            jsonObject.put("Last Name", complaint.getName().getLastName());
            jsonObject.put("CustomerID", complaint.getCustomerID().toString());
            jsonObject.put("PrinterID", complaint.getPrinterID().toString());
            jsonObject.put("Email", complaint.getEmail().toString());
            jsonObject.put("Call Number", complaint.getCallNumber());

            JSONObject location = new JSONObject();
            location.put("Country", complaint.getLocation().getCountry());
            location.put("State", complaint.getLocation().getState());
            location.put("City", complaint.getLocation().getCity());
            location.put("Street", complaint.getLocation().getStreet());
            location.put("Location Number", complaint.getLocation().getNumber());
            jsonObject.put("Location", location);

            JSONObject complaintInfo = new JSONObject();
            complaintInfo.put("title", complaint.getDescription().getTitle());
            complaintInfo.put("description", complaint.getDescription().getBody());
            jsonObject.put("Complaint", complaintInfo);

            // Add the status field based on the ComplaintState enum
            jsonObject.put("Status", complaint.getComplaintState().toString());

            return jsonObject;
        }

        return null;
    }

    private void writeJsonArrayToFile(JSONArray jsonArray) throws IOException {
        if (jsonArray != null) {
            Path path = Paths.get(constants.COMPLAINT_FILE_PATH);
            Files.write(path, jsonArray.toString(2).getBytes()); // The second parameter for pretty printing (indentation)
        }
    }

    public List<Complaint> getAllComplaintImplementation() throws IOException {
        List<Complaint> complaints = new ArrayList<>();

        JSONArray jsonArray = readJsonArrayFromFile();
        for (Object obj : jsonArray) {
            if (obj instanceof JSONObject jsonComplaint) {
                complaints.add(convertJsonObjectToComplaint(jsonComplaint));
            }
        }
        showResult(complaints);
        return complaints;
    }

    private Complaint convertJsonObjectToComplaint(JSONObject jsonObject) throws IOException {
        // Extracting data from JSONObject
        String complaintID = jsonObject.getString("ComplaintID");
        String date = jsonObject.getString("Date");
        String firstName = jsonObject.getString("First Name");
        String lastName = jsonObject.getString("Last Name");
        String customerID = jsonObject.getString("CustomerID");
        String printerID = jsonObject.getString("PrinterID");
        String email = jsonObject.getString("Email");
        String callNumber = jsonObject.getString("Call Number");

        JSONObject locationJson = jsonObject.getJSONObject("Location");
        String country = locationJson.getString("Country");
        String state = locationJson.getString("State");
        String city = locationJson.getString("City");
        String street = locationJson.getString("Street");
        String locationNumber = locationJson.getString("Location Number");

        JSONObject complaintInfoJson = jsonObject.getJSONObject("Complaint");
        String title = complaintInfoJson.getString("title");
        String description = complaintInfoJson.getString("description");
        ComplaintState complaintState = ComplaintState.valueOf(jsonObject.getString("Status").toUpperCase());

        // Create value objects
        CustomerName customerName = new CustomerName(firstName, lastName);
        ComplaintDescription complaintDescription = new ComplaintDescription(title, description);
        CustomerCallNumber customerCallNumber = new CustomerCallNumber(callNumber);
        CustomerEmail customerEmail = new CustomerEmail(email);
        CustomerID customerIdObj = new CustomerID(customerID);
        CustomerLocation customerLocation = new CustomerLocation(country, state, city, street, locationNumber);
        PrinterID printerIdObj = new PrinterID(printerID);
        ComplaintID complaintIdObj = new ComplaintID(complaintID);
        ComplaintDate complaintDateObj = new ComplaintDate(date);

        // Build the Complaint object

        return new Complaint.Builder()
                .name(customerName)
                .description(complaintDescription)
                .callNumber(customerCallNumber)
                .email(customerEmail)
                .customerID(customerIdObj)
                .location(customerLocation)
                .printerID(printerIdObj)
                .complaintID(complaintIdObj)
                .complaintDate(complaintDateObj)
                .complaintState(complaintState)
                .build();

    }

    public void showResult(List<Complaint> complaints) {
        // Create JFrame
        JFrame frame = new JFrame("All Complaints");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);

        // Create JTable
        String[] columnNames = {"Complaint ID", "Date", "Customer Name", "Email", "Call Number", "Printer ID", "Description", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        // Add complaints data to the table
        for (Complaint complaint : complaints) {
            String complaintID = complaint.getComplaintID().toString();
            String date = complaint.getComplaintDate().toString();
            String customerName = complaint.getName().getFirstName() + " " + complaint.getName().getLastName();
            String email = complaint.getEmail().toString();
            String callNumber = String.valueOf(complaint.getCallNumber());
            String printerID = complaint.getPrinterID().toString();
            String description = complaint.getDescription().getTitle() + ": " + complaint.getDescription().getBody();
            String status = complaint.getComplaintState().toString();

            Object[] rowData = {complaintID, date, customerName, email, callNumber, printerID, description, status};
            tableModel.addRow(rowData);
        }

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        // Display the JFrame
        frame.setVisible(true);
    }
}
