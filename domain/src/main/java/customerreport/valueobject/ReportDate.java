package customerreport.valueobject;

import java.io.IOException;
import java.util.Date;

public class ReportDate {
    private final Date startDate;
    private final Date endDate;

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public ReportDate(Date startDate, Date endDate)throws IOException {
        validateDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validateDate(Date startDate, Date endDate)throws IOException {
        if (startDate == null || endDate == null) {
            throw new  IOException("Both start date and end date must be provided.");
        }
        if (startDate.after(endDate)) {
            throw new  IOException("Start date must be before end date.");
        }
    }
}