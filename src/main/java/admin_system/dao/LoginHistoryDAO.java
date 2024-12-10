package admin_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import admin_system.dto.LoginHistoryDTO;
import connect_db.UtilityDAO;

public class LoginHistoryDAO {

    public List<LoginHistoryDTO> viewLoginHistory(int id) {
        List<LoginHistoryDTO> loginHistoryList = new ArrayList<>();
        UtilityDAO utilityDAO = new UtilityDAO();
        Connection conn = utilityDAO.getConnection();

        if (conn == null) {
            return loginHistoryList;
        }

        String query = "SELECT LOGIN_ID, USER_ID, LOGIN_TIME FROM LOGIN_HISTORY WHERE USER_ID = ? ORDER BY LOGIN_TIME DESC";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id); // Set USER_ID parameter

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int loginId = rs.getInt("LOGIN_ID");
                int userId = rs.getInt("USER_ID");
                Timestamp loginTime = rs.getTimestamp("LOGIN_TIME");

                LoginHistoryDTO loginHistoryDTO = new LoginHistoryDTO(loginId, userId, loginTime);
                loginHistoryList.add(loginHistoryDTO);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return loginHistoryList;
    }

    public List<LoginHistoryDTO> viewAllLoginHistory() {
        List<LoginHistoryDTO> loginHistoryList = new ArrayList<>();
        UtilityDAO utilityDAO = new UtilityDAO();
        Connection conn = utilityDAO.getConnection();
    
        if (conn == null) {
            return loginHistoryList; // Nếu không thể kết nối, trả về danh sách rỗng
        }
    
        // Truy vấn SQL lấy tất cả lịch sử đăng nhập
        String query = "SELECT LOGIN_ID, USER_ID, LOGIN_TIME FROM LOGIN_HISTORY ORDER BY LOGIN_TIME DESC";
    
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Không cần tham số nào vì không lọc theo USER_ID nữa
            ResultSet rs = stmt.executeQuery();
    
            // Duyệt qua kết quả truy vấn và tạo danh sách LoginHistoryDTO
            while (rs.next()) {
                int loginId = rs.getInt("LOGIN_ID");
                int userIdFromDB = rs.getInt("USER_ID");
                Timestamp loginTime = rs.getTimestamp("LOGIN_TIME");
    
                LoginHistoryDTO history = new LoginHistoryDTO(loginId, userIdFromDB, loginTime);
                loginHistoryList.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
    
        return loginHistoryList;
    }
    

}
