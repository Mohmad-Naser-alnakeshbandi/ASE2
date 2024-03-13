package persistence.weeklyReport;
import weeklyreport.Repository.WeeklyReportRepository;
import weeklyreport.WeeklyReportService;
import weeklyreport.entity.WeeklyReport;
import java.io.IOException;

public class WeeklyReportRepositoryBridge implements WeeklyReportRepository {


    @Override
    public void createWeeklyReport(WeeklyReport weeklyReport) throws IOException {
        WeeklyReportService weeklyReportService = new WeeklyReportService();
        weeklyReportService.getWeeklyComplaintImplementation(weeklyReport);
    }


    @Override
    public void saveWeeklyReport(WeeklyReport weeklyReport, String filePath) throws IOException {
        WeeklyReportService weeklyReportService  = new WeeklyReportService();
        weeklyReportService.saveWeeklyReportImplementation(weeklyReport, filePath);
    }
}
