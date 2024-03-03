package weeklyreport.valueobject;

import java.io.IOException;
import java.util.Objects;

public class SelectedYear {

    private final int selectedYear;

    public int getSelectedYear() {
        return selectedYear;
    }

    public SelectedYear(int selectedYear) throws IOException {
        validateSelectedYear(selectedYear);
        this.selectedYear = selectedYear;
    }

    private void validateSelectedYear(int selectedYear) throws IOException {
        // Check if the selected year is an integer
        if (!isInteger(String.valueOf(selectedYear))) {
            throw new IOException("Invalid selected year. Year should be an Number.");
        }
    }

    // Method to check if a string is an integer
    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(getSelectedYear());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SelectedYear)) return false;
        SelectedYear that = (SelectedYear) o;
        return selectedYear == that.selectedYear;
    }

    @Override
    public int hashCode() {
        return Objects.hash(selectedYear);
    }
}
