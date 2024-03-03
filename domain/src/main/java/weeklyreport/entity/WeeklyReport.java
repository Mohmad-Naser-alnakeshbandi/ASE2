package weeklyreport.entity;

import weeklyreport.valueobject.SelectedWeek;
import weeklyreport.valueobject.SelectedYear;

public class WeeklyReport {

    private final SelectedWeek selectedWeek;
    private final SelectedYear selectedYear;

    // Private constructor to prevent instantiation from outside
    private WeeklyReport(Builder builder) {
        this.selectedYear = builder.selectedYear;

        this.selectedWeek = builder.selectedWeek;
    }

    public SelectedYear getSelectedYear() {
        return selectedYear;
    }

    public SelectedWeek getSelectedWeek() {
        return selectedWeek;
    }

    // Nested Builder class
    public static class Builder {
        private SelectedWeek selectedWeek;

        private SelectedYear selectedYear;


        // Method to set SelectedWeek
        public Builder setSelectedWeek(SelectedWeek selectedWeek) {
            this.selectedWeek = selectedWeek;
            return this; // Return the builder instance for method chaining
        }
        public Builder setSelectedYear(SelectedYear selectedYear) {
            this.selectedYear = selectedYear;
            return this; // Return the builder instance for method chaining
        }
        // Method to build WeeklyReport object
        public WeeklyReport build() {
            return new WeeklyReport(this);
        }
    }

    @Override
    public String toString() {
        return "Weekly Report{" +
                "selected Year= " + selectedYear +
                "\n" +
                "selected Week =" + selectedWeek +
                '}';
    }
}
