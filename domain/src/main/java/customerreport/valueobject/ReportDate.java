package customerreport.valueobject;

import errors.Errors;

public class ReportDate {
    private String startDate;
    private String endDate;

    public String getEndDate() {
        return endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public ReportDate(String startDate, String endDate) {
        validateDate( startDate,  endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validateDate(String startDate, String endDate) {
        if (startDate != null && endDate != null && startDate.compareTo(endDate) > 0) {
            throw new Errors("Start date must be less than or equal to end date.");
        }
    }
}
