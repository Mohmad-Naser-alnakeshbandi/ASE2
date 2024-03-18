package complaint.valueobject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class ComplaintDate {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
    String currentDate = dateFormat.format(calendar.getTime());

    public String getCurrentDate() {
        return currentDate;
    }

    public ComplaintDate() {
    }

    public ComplaintDate(String currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public String toString() {
        return currentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplaintDate that)) return false;
        return Objects.equals(calendar, that.calendar) && Objects.equals(dateFormat, that.dateFormat) && Objects.equals(getCurrentDate(), that.getCurrentDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(calendar, dateFormat, getCurrentDate());
    }
}
