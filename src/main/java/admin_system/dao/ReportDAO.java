package admin_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import admin_system.dto.ReportDTO;
import connect_db.UtilityDAO;

public class ReportDAO {

    // Lấy tất cả các báo cáo và sắp xếp theo tiêu chí
    public List<ReportDTO> getAllReports(String orderBy) {
        List<ReportDTO> reportList = new ArrayList<>();
        UtilityDAO utilityDAO = new UtilityDAO();
        Connection conn = utilityDAO.getConnection();

        if (conn == null) {
            return reportList; // Nếu không thể kết nối, trả về danh sách rỗng
        }

        // Xác định cột sắp xếp dựa trên tham số orderBy
        String orderByColumn;
        switch (orderBy) {
            case "Reporter":
                orderByColumn = "USERNAME";
                break;
            case "Reported at":
                orderByColumn = "REPORT_AT";
                break;
            default:
                orderByColumn = "ID"; // Mặc định sắp xếp theo ID
                break;
        }

        // Truy vấn SQL lấy tất cả báo cáo và sắp xếp theo cột orderBy
        String query = "SELECT r.ID, r.REPORTER_ID, r.REPORTED_ID, r.CONTENT, r.REPORT_AT " +
            "FROM REPORT_SPAM r " +
            "JOIN USER_ACCOUNT u1 ON r.REPORTER_ID = u1.ID " +
            "ORDER BY " + orderByColumn;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            // Duyệt qua kết quả truy vấn và tạo danh sách ReportDTO
            while (rs.next()) {
                int id = rs.getInt("ID");
                int reporterId = rs.getInt("REPORTER_ID");
                int reportedId = rs.getInt("REPORTED_ID");
                String content = rs.getString("CONTENT");
                java.sql.Timestamp reportAt = rs.getTimestamp("REPORT_AT");

                ReportDTO report = new ReportDTO(id, reporterId, reportedId, content, reportAt);
                reportList.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }

        return reportList;
    }

    public List<ReportDTO> getByReportTime(Timestamp reportTime) {
        List<ReportDTO> reportList = new ArrayList<>();
        UtilityDAO utilityDAO = new UtilityDAO();
        Connection conn = utilityDAO.getConnection();

        if (conn == null) {
            return reportList; // Nếu không thể kết nối, trả về danh sách rỗng
        }

        String query = "SELECT * FROM REPORT_SPAM WHERE REPORT_AT = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setTimestamp(1, reportTime);  // Thêm giá trị của thời gian tìm kiếm vào câu truy vấn
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                // Lấy dữ liệu từ ResultSet và tạo đối tượng ReportDTO
                int id = rs.getInt("ID");
                int reporterId = rs.getInt("REPORTER_ID");
                int reportedId = rs.getInt("REPORTED_ID");
                String content = rs.getString("CONTENT");
                Timestamp reportAt = rs.getTimestamp("REPORT_AT");
    
                // Tạo đối tượng ReportDTO và thêm vào danh sách
                ReportDTO report = new ReportDTO(id, reporterId, reportedId, content, reportAt);
                reportList.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportList;
    }

    public List<ReportDTO> getByReporter(String reporterName) {
        List<ReportDTO> reportList = new ArrayList<>();
        UtilityDAO utilityDAO = new UtilityDAO();
        Connection conn = utilityDAO.getConnection();
        
        if (conn == null) {
            return reportList; // Nếu không thể kết nối, trả về danh sách rỗng
        }

        String query = "SELECT r.ID, r.REPORTER_ID, r.REPORTED_ID, r.CONTENT, r.REPORT_AT " +
                        "FROM REPORT_SPAM r " +
                        "JOIN USER_ACCOUNT u1 ON r.REPORTER_ID = u1.ID " +
                        "WHERE u1.USERNAME LIKE ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + reporterName + "%"); 
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Lấy dữ liệu từ ResultSet và tạo đối tượng ReportDTO
                int id = rs.getInt("ID");
                int reporterId = rs.getInt("REPORTER_ID");
                int reportedId = rs.getInt("REPORTED_ID");
                String content = rs.getString("CONTENT");
                Timestamp reportAt = rs.getTimestamp("REPORT_AT");
    
                // Tạo đối tượng ReportDTO và thêm vào danh sách
                ReportDTO report = new ReportDTO(id, reporterId, reportedId, content, reportAt);
                
                reportList.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportList;
    }
    

}
