package complaint;

import complaint.entity.Complaint;
import org.json.JSONArray;
import org.json.JSONObject;
import constants.constants;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
}
