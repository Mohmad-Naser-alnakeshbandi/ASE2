package gui.weeklyreport;

import org.jetbrains.annotations.NotNull;
import persistence.weeklyReport.WeeklyReportRepositoryBridge;
import weeklyreport.entity.WeeklyReport;
import weeklyreport.valueobject.SelectedWeek;
import weeklyreport.valueobject.SelectedYear;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.Year;
import java.util.Objects;

public class GUIWeeklyReport extends JFrame {

    private final WeeklyReportRepositoryBridge weeklyReportRepositoryBridge;
    private final JComboBox<Integer> weekComboBox;
    private final JComboBox<Integer> yearComboBox;

    public GUIWeeklyReport(WeeklyReportRepositoryBridge weeklyReportRepositoryBridge) {
        this.weeklyReportRepositoryBridge = Objects.requireNonNull(weeklyReportRepositoryBridge, "Weekly Report Repository must not be null");
        this.weekComboBox = new JComboBox<>(generateWeekNumbers());
        this.yearComboBox = new JComboBox<>(generateYearRange());
    }

    public void init() {
        setTitle("Weekly Report");
        setSize(350, 120);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel weekLabel = new JLabel("Select Week Number:");
        JLabel yearLabel = new JLabel("Select Year:");
        JButton generateButton = new JButton("Generate");
        JButton saveButton = new JButton("Save");

        generateButton.addActionListener(e -> generateReport());
        saveButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Save button clicked!"));

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(weekLabel);
        panel.add(weekComboBox);
        panel.add(yearLabel);
        panel.add(yearComboBox);
        panel.add(generateButton);
        panel.add(saveButton);

        add(panel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void generateReport() {
        try {
            Integer selectedWeek = (Integer) weekComboBox.getSelectedItem();
            Integer selectedYear = (Integer) yearComboBox.getSelectedItem();
            if (selectedWeek == null || selectedYear == null) {
                JOptionPane.showMessageDialog(this, "Please select week and year", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            WeeklyReport weeklyReport = new WeeklyReport.Builder()
                    .setSelectedWeek(new SelectedWeek(selectedWeek))
                    .setSelectedYear(new SelectedYear(selectedYear))
                    .build();
            weeklyReportRepositoryBridge.createWeeklyReport(weeklyReport);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Something went wrong", JOptionPane.ERROR_MESSAGE);
        }
    }

    @NotNull
    private Integer[] generateWeekNumbers() {
        Integer[] weeks = new Integer[25];
        for (int i = 0; i < weeks.length; i++) {
            weeks[i] = i + 1;
        }
        return weeks;
    }

    @NotNull
    private Integer[] generateYearRange() {
        int currentYear = Year.now().getValue();
        Integer[] years = new Integer[25];
        for (int i = 0; i < years.length; i++) {
            years[i] = currentYear - i;
        }
        return years;
    }


}
