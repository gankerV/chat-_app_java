package login_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import admin_system.dto.UserAccountDTO;
import connect_db.UtilityDAO;

public class LoginDAO {
    public int checkLogin(String email, String password) {
        UserAccountDTO userAccount = null;
        UtilityDAO utilityDAO = new UtilityDAO();
        Connection conn = utilityDAO.getConnection();

        if (conn == null) {
            return -1;
        }

        // Truy vấn SQL để lấy thông tin người dùng theo userId
        String query = "SELECT * FROM USER_ACCOUNT WHERE EMAIL = ? AND PASSWORD = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Gán giá trị cho tham số truy vấn
            stmt.setString(1, email);
            stmt.setString(2, password);

            // Thực thi truy vấn
            ResultSet rs = stmt.executeQuery();

            // Kiểm tra kết quả
            if (rs.next()) {
                return rs.getInt("ID"); // Trả về ID nếu tìm thấy người dùng
            }
        } catch (SQLException e) {
            System.err.println("Error during login check: " + e.getMessage());
        }
        return -1; // Trả về -1 nếu không tìm thấy hoặc có lỗi
    }
}
