package weeklyreport.Repository;


import weeklyreport.entity.WeeklyReport;

import java.io.IOException;

public interface WeeklyReportRepository {

    void createWeeklyReport(WeeklyReport weeklyReport) throws IOException;
    void saveWeeklyReport(WeeklyReport weeklyReport, String filePath) throws IOException;
}
