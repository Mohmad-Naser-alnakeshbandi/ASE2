package complaint;

import org.json.JSONArray;
import org.json.JSONObject;
import constants.constants;
import success.Success;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ContractValidator {

    public static boolean validateContract(String customerID, String printerID) throws IOException {
        Path path = Paths.get(constants.CONTRACT_FILE_PATH);
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
                    return true;
                } else {
                    throw new IllegalStateException("Printer ID: " + printerID + " not found in the contract for Customer ID: " + customerID);
                }
            } else {
                throw new IllegalStateException("Customer ID: " + customerID + " not found in the contract Data, you need to check again?");
            }
        }
        return false;
    }
}
