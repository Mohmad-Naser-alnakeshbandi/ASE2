package weeklyreport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import weeklyreport.entity.WeeklyReport;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WeeklyReportService {
    private static final String COMPLAINT_FILE_PATH = "Data/complaint.json";

    public void getWeeklyComplaintImplementation(WeeklyReport weeklyReport) {
        int weekNumber = weeklyReport.getSelectedWeek().getSelectedWeek();
        int year = weeklyReport.getSelectedYear().getSelectedYear();

        Map<String, Integer> complaintCountByDay = countComplaintsByWeek(weekNumber, year);

        if (!complaintCountByDay.isEmpty()) {
            for (Map.Entry<String, Integer> entry : complaintCountByDay.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " complaints");
            }
            showResult(complaintCountByDay, year, weekNumber);
        } else {
            System.out.println("No complaints found for week " + weekNumber + " of year " + year);
        }
    }

    private Map<String, Integer> countComplaintsByWeek(int weekNumber, int year) {
        Map<String, Integer> complaintCountByDay = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(COMPLAINT_FILE_PATH))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            JSONArray jsonArray = new JSONArray(sb.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String dateString = jsonObject.optString("Date", null);
                if (dateString != null) {
                    try {
                        Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(dateString);
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        int jsonWeekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
                        int jsonYear = calendar.get(Calendar.YEAR);

                        if (jsonWeekNumber == weekNumber && jsonYear == year) {
                            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                            String dayOfWeekString = getDayOfWeekString(dayOfWeek);
                            complaintCountByDay.put(dayOfWeekString, complaintCountByDay.getOrDefault(dayOfWeekString, 0) + 1);
                        }
                    } catch (ParseException e) {
                        System.err.println("Error parsing date: " + e.getMessage());
                    }
                }
            }
        } catch (IOException | JSONException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }

        return complaintCountByDay;
    }

    private String getDayOfWeekString(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                return "Sunday";
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";
            case Calendar.WEDNESDAY:
                return "Wednesday";
            case Calendar.THURSDAY:
                return "Thursday";
            case Calendar.FRIDAY:
                return "Friday";
            case Calendar.SATURDAY:
                return "Saturday";
            default:
                return "";
        }
    }

    public void showResult(Map<String, Integer> complaintCountByDay, int year, int weekNumber) {
        JFrame frame = new JFrame("Weekly Complaint Report");
        frame.setSize(500,158);
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Year: " + year + ", Week Number: " + weekNumber);
        JTable table = new JTable();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Day of Week");
        model.addColumn("Complaint Amount");

        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

        int maxComplaints = Integer.MIN_VALUE;
        int minComplaints = Integer.MAX_VALUE;
        String maxDay = "";
        String minDay = "";

        for (String day : daysOfWeek) {
            Integer complaintAmount = complaintCountByDay.getOrDefault(day, 0);
            model.addRow(new Object[]{day, complaintAmount});
            if (complaintAmount > maxComplaints) {
                maxComplaints = complaintAmount;
                maxDay = day;
            }
            if (complaintAmount < minComplaints) {
                minComplaints = complaintAmount;
                minDay = day;
            }
        }

        table.setModel(model);
        table.setEnabled(false);


        highlightMaxDayAndMinDay( table,  maxDay,  minDay);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(label, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setVisible(true);
    }

    private void highlightMaxDayAndMinDay(JTable table, String maxDay, String minDay) {
        // Find the minimum number of complaints
        final int minComplaints = findMinComplaints(table);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String day = table.getValueAt(row, 0).toString();
                int complaints = (int) table.getValueAt(row, 1);
                if (day.equals(maxDay)) {
                    // Set background color for maxDay cells
                    cellComponent.setBackground(Color.RED);
                } else if (day.equals(minDay) || complaints == minComplaints) {
                    // Set background color for minDay cells and cells with complaints equal to minComplaints
                    cellComponent.setBackground(Color.GREEN);
                } else {
                    // Reset background color for other cells
                    cellComponent.setBackground(Color.WHITE);
                }
                return cellComponent;
            }
        };

        table.getColumnModel().getColumn(0).setCellRenderer(renderer);
    }

    private int findMinComplaints(JTable table) {
        int minComplaints = Integer.MAX_VALUE;
        for (int row = 0; row < table.getRowCount(); row++) {
            int complaints = (int) table.getValueAt(row, 1);
            if (complaints < minComplaints) {
                minComplaints = complaints;
            }
        }
        return minComplaints;
    }



}
