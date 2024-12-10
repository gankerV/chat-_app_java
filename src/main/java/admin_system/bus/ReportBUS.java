package admin_system.bus;

import java.sql.Timestamp;
import java.util.List;

import admin_system.dao.ReportDAO;
import admin_system.dto.ReportDTO;

public class ReportBUS {
    private ReportDAO reportDAO;

    // Constructor
    public ReportBUS() {
        this.reportDAO = new ReportDAO(); 
    }

    // Lấy tất cả các báo cáo
    public List<ReportDTO> getAllReports(String orderBy) {
        return reportDAO.getAllReports(orderBy);
    }

    public List<ReportDTO> getByReportTime(Timestamp reportTime) {
        return reportDAO.getByReportTime(reportTime);
    }

    public List<ReportDTO> getByReporter(String reporterName) {
        return reportDAO.getByReporter(reporterName);
    }
}
