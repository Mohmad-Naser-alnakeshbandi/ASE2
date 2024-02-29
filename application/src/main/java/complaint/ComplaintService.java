package complaint;
import complaint.entity.Complaint;
import org.json.JSONArray;
import org.json.JSONObject;
import success.Success;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ComplaintService {

    private static final String COMPLAINT_FILE_PATH = "Data/complaint.json";
    private static final String CONTRACT_FILE_PATH = "Data/contract.json";

    public void addCompliantImplementation(Complaint complaint) throws IOException {
        JSONArray existingArray = readJsonArrayFromFile();
        JSONObject newComplaintJson = createComplaintJsonObject(complaint);
        existingArray.put(newComplaintJson);
        writeJsonArrayToFile(existingArray);
    }


    private JSONArray readJsonArrayFromFile() throws IOException {
        String jsonContent = new String(Files.readAllBytes(Paths.get(ComplaintService.COMPLAINT_FILE_PATH)));
        // Check if the content is empty or doesn't start with '['
        if (jsonContent.trim().isEmpty() || !jsonContent.startsWith("[")) {
            System.out.println("Invalid or empty JSON array in the file.");
            return new JSONArray();
        }
        // Parse the JSON array
        return new JSONArray(jsonContent);
    }

    private JSONObject createComplaintJsonObject(Complaint complaint) throws IOException {
        boolean isContractValid;
        isContractValid = validateContract(complaint.getCustomerID().toString(), complaint.getPrinterID().toString());
        if (isContractValid) {
            JSONObject jsonObject = new JSONObject();

            if (complaint.getComplaintDate() != null) {
                jsonObject.put("Date", complaint.getComplaintDate().getCurrentDate());
            } else {
                // Handle the case where complaint date is null
                jsonObject.put("Date", "Unknown");
            }

            jsonObject.put("ComplaintID", complaint.getComplaintID());
            jsonObject.put("Date",complaint.getComplaintDate());
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
            return jsonObject;
        }

        return null;
    }

    public static boolean validateContract(String customerID, String printerID) throws IOException {

        Path path = Paths.get(CONTRACT_FILE_PATH);
        String jsonContent = Files.readString(path);

        // Check if the content is a valid JSON object
        if (jsonContent.trim().startsWith("{") && jsonContent.trim().endsWith("}")) {
            // Parse the JSON content into a JSONObject
            JSONObject jsonObject = new JSONObject(jsonContent);

            // Check if the customerID exists in the JSON object
            if (jsonObject.has(customerID)) {
                // Get the JSONArray associated with the current customerID
                JSONArray printerIDs = jsonObject.getJSONArray(customerID);

                // Check if the printerID exists in the array
                if (printerIDs.toList().contains(printerID)) {
                    new Success("Add Complaint", "Contract is valid for Customer ID: " + customerID + " and Printer ID:" + printerID + "\n" + " we will deal with the complaint as fast as possible");
                   // System.out.println("Contract is valid for Customer ID: " + customerID + " and Printer ID: " + printerID);
                    return true;
                } else {
                    throw new IllegalStateException("Printer ID: " + printerID + " not found in the contract for Customer ID: " + customerID);
                }
            } else {
                throw new IllegalStateException("Customer ID: " + customerID + " not found in the contract Data, you need to check again?");
            }
            // Return false in case of any exception or invalid JSON
        }
        return false;
    }



    private void writeJsonArrayToFile(JSONArray jsonArray) throws IOException {
        if (jsonArray != null) {
            Path path = Paths.get(ComplaintService.COMPLAINT_FILE_PATH);
            Files.write(path, jsonArray.toString(2).getBytes()); // The second parameter for pretty printing (indentation)
        }
    }
}
