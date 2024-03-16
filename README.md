﻿# Advance Software Engineering - based on Domain Driven Design

## Principles:
- SRP "Single-responsibility principle": Value object for the entities, in this way no validation in the entity class
  - Clearly defined task for each object
  - Higher-level behavior through the interaction of objects
  - Low coupling and complexity
  - Recognize more responsibility 
- ISP:
  - ISP is intended to keep a system decoupled and thus easier to refactor, change, and redeploy
  - Not to write all method in one interface if you only want to implement 2 of them
- DRY "Don't repeat yourself":
  - reducing repetition of information which is likely to change, replacing it with abstractions that are less likely to change, or using data normalization which avoids redundancy in the first place.
- [See it here](https://github.com/Mohmad-Naser-alnakeshbandi/ASE2/commit/99e61e9633f7e2f63f9001fa4b7c918a7f7c7220) 

- POLA: Design interfaces and behaviors in a way that is intuitive to users or developers, minimizing surprises or unexpected behavior.
````java
package customerreport.repository;
import customerreport.entity.CustomerReport;
import java.io.IOException;
import java.text.ParseException;
public interface CustomerReportRepository {
    void createCustomerReport(CustomerReport customerReport) throws IOException, ParseException;
    void saveCustomerReport(CustomerReport customerReport, String filePath) throws IOException;
}
  ````
the code above aligns with the Principle of Least Astonishment (POLA) to a certain extent. Let's break down how it adheres to POLA:
- Intuitive Naming: The package name customerreport.repository and interface name CustomerReportRepository are intuitive and follow common Java naming conventions. This makes it easier for other developers to understand the purpose of the code without needing extensive documentation.
- Clear Method Signatures: The method signatures createCustomerReport and saveCustomerReport are clear and descriptive. They indicate what actions the methods perform and what parameters they require, enhancing readability and reducing ambiguity.
- Consistent Exception Handling: Both methods declare the exceptions they may throw (IOException and ParseException), which is consistent and helps developers understand potential error scenarios upfront. 
- Minimal Cognitive Load: The interface is concise and focuses on the core functionality of creating and saving customer reports. It avoids unnecessary complexity or additional methods, reducing cognitive load for developers who interact with this code.
  
- kiss
  ````java
    private String getDayOfWeekString(int dayOfWeek) {
    return switch (dayOfWeek) {
    case Calendar.SUNDAY -> "Sunday";
    case Calendar.MONDAY -> "Monday";
    case Calendar.TUESDAY -> "Tuesday";
    case Calendar.WEDNESDAY -> "Wednesday";
    case Calendar.THURSDAY -> "Thursday";
    case Calendar.FRIDAY -> "Friday";
    case Calendar.SATURDAY -> "Saturday";
    default -> "";
    };
    }
  ````

## Code refactoring
- The ComplaintService class is responsible for both reading from files and validating contracts. 
It's better to separate concerns into different classes/methods. Consider creating a separate class for contract validation. [See it here](https://github.com/Mohmad-Naser-alnakeshbandi/ASE2/commit/2f2b7a8539367fae3da45db96d6303a4217d231d)

Before:
````java
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
      throw  new IOException("Invalid or empty Data Store.");
    }
    // Parse the JSON array
    return new JSONArray(jsonContent);
  }
  private JSONObject createComplaintJsonObject(Complaint complaint) throws IOException {
    boolean isContractValid;
    isContractValid = validateContract(complaint.getCustomerID().toString(), complaint.getPrinterID().toString());
    if (isContractValid) {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("ComplaintID", complaint.getComplaintID());
      jsonObject.put("Date",complaint.getComplaintDate());
      jsonObject.put("First Name", complaint.getName().getFirstName());
      jsonObject.put("Last Name", complaint.getName().getLastName());
      jsonObject.put("common.CustomerID", complaint.getCustomerID().toString());
      jsonObject.put("common.PrinterID", complaint.getPrinterID().toString());
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
      Path path = Paths.get(constants.COMPLAINT_FILE_PATH);
      Files.write(path, jsonArray.toString(2).getBytes()); // The second parameter for pretty printing (indentation)
    }
  }
}
  ````

After:

````java
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
        boolean isContractValid = ContractValidator.validateContract(complaint.getCustomerID().toString(), complaint.getPrinterID().toString());
        if (isContractValid) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ComplaintID", complaint.getComplaintID());
            jsonObject.put("Date", complaint.getComplaintDate());
            jsonObject.put("First Name", complaint.getName().getFirstName());
            jsonObject.put("Last Name", complaint.getName().getLastName());
            jsonObject.put("common.CustomerID", complaint.getCustomerID().toString());
            jsonObject.put("common.PrinterID", complaint.getPrinterID().toString());
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
````


 ````java
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
  ````
- Inefficient Date Parsing: In getCustomerCompliantImplementation, a new SimpleDateFormat object is created for each complaint entry. 
  This is inefficient and can be improved by creating a single static instance of SimpleDateFormat. [See it here](https://chat.openai.com/c/97807efb-0f35-496f-b0a0-3cb7188b2bc8)

Before: 

````java
  public class CustomerReportService {
    public void getCustomerCompliantImplementation(CustomerReport customerReport) throws ParseException {
        String customerID = customerReport.getCustomerID().getCustomerID();
        Date reportStartDate = customerReport.getReportDate().getStartDate();
        Date reportEndDate = customerReport.getReportDate().getEndDate();

        List<Map<String, String>> complaintsList = readCustomerComplaintsFromJson();
        int amountOfComplaints = 0;
        if (!complaintsList.isEmpty()) {
            for (Map<String, String> complaintInfo : complaintsList) {
                String complaintDateString = complaintInfo.get("Date");
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                Date complaintDate;
                try {
                    complaintDate = dateFormat.parse(complaintDateString);
                } catch (ParseException e) {
                    throw new ParseException("Error parsing date: " + e.getMessage(), 0);
                }
                if (complaintInfo.get("common.CustomerID").equals(customerID) &&
                        isDateInRange(complaintDate, reportStartDate, reportEndDate)) {

                    amountOfComplaints++;
                }
            }
            showResult(customerID, amountOfComplaints);
        } else {
            throw new ParseException("No customer complaints found.", 0);
        }
    }
    private void showResult(String customerID, int amountOfComplaints) {
        JFrame frame = new JFrame();
        String[] columnHeaders = {"common.CustomerID", "Amount of Complaints"};
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

    private boolean isDateInRange(Date date, Date startDate, Date endDate) {
        return date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0;
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
                String customerID = jsonObject.getString("common.CustomerID");
                String date = jsonObject.getString("Date");
                Map<String, String> customerInfo = new HashMap<>();
                customerInfo.put("common.CustomerID", customerID);
                customerInfo.put("Date", date);
                complaintsList.add(customerInfo);
                System.out.println(customerInfo);
            }
        } catch (IOException e) {
            throw new ParseException("Error reading store files", 0);
        }
        return complaintsList;
    }

    public void saveCustomerReportImplementation(CustomerReport customerReport, String filepath) throws IOException {
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

    private String getString(CustomerReport customerReport) {
        if (customerReport == null) {
            throw new IllegalStateException("Firstly, you should generate a report before saving it");
        }
        String customerID = customerReport.getCustomerID().getCustomerID();
        String reportStartDate = String.valueOf(customerReport.getReportDate().getStartDate()).substring(0, 10);
        String reportEndDate = String.valueOf(customerReport.getReportDate().getEndDate()).substring(0, 10);
        return "common.CustomerID ," + " Amount Of Complaints , " + "Report Start Date, " + "Report End Date" + "\n" +
                customerID + "," + "N/A" + "," + reportStartDate + "," + reportEndDate;
    }
}

````

After: 

`````java

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
            if (complaintInfo.get("common.CustomerID").equals(customerID) && isDateInRange(complaintDate, startDate, endDate)) {
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
        String[] columnHeaders = {"common.CustomerID", "Amount of Complaints"};
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
                String customerID = jsonObject.getString("common.CustomerID");
                String date = jsonObject.getString("Date");
                Map<String, String> customerInfo = new HashMap<>();
                customerInfo.put("common.CustomerID", customerID);
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
        return "common.CustomerID ," + " Amount Of Complaints , " + "Report Start Date, " + "Report End Date" + "\n" +
                customerID + "," + "N/A" + "," + reportStartDate + "," + reportEndDate;
    }
}
`````
