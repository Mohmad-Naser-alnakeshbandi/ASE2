package weeklyreport.valueobject;

import java.io.IOException;
import java.util.Objects;

public class SelectedWeek {

    private final int selectedWeek;

    public int getSelectedWeek() {
        return selectedWeek;
    }

    public SelectedWeek(int selectedWeek) throws IOException {
        validateSelectedWeek(selectedWeek);
        this.selectedWeek = selectedWeek;
    }

    private void validateSelectedWeek(int selectedWeek) throws IOException {
        // Check if the week number is within the range 1 to 52
        if (selectedWeek < 1 || selectedWeek > 52) {
            throw new IOException("Invalid week number. Week number should be between 1 and 52.");
        }
    }

    @Override
    public String toString() {
        return String.valueOf(getSelectedWeek());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SelectedWeek)) return false;
        SelectedWeek that = (SelectedWeek) o;
        return selectedWeek == that.selectedWeek;
    }

    @Override
    public int hashCode() {
        return Objects.hash(selectedWeek);
    }
}
