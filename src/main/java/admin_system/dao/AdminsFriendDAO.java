package admin_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import admin_system.dto.AdminsFriendDTO;
import connect_db.UtilityDAO;

public class AdminsFriendDAO {

    public List<AdminsFriendDTO> getFriendsByAdminId(int adminId, String orderBy, String searchUsername, int NumCommonFriend) {
        UtilityDAO utilityDAO = new UtilityDAO();
        Connection conn = utilityDAO.getConnection();
        List<AdminsFriendDTO> adminsFriendDTOList = new ArrayList<>();
    
        if (conn == null) {
            return adminsFriendDTOList; // Nếu không thể kết nối, trả về danh sách rỗng
        }
    
        // Đặt phần sắp xếp trong câu lệnh SQL dựa trên tham số orderBy
        String orderByClause = "ORDER BY ";
        if ("Username".equalsIgnoreCase(orderBy)) {
            orderByClause += "FA.USERNAME";  // Sắp xếp theo USERNAME
        } else if ("Created at".equalsIgnoreCase(orderBy)) {
            orderByClause += "FA.CREATED_AT";  // Sắp xếp theo CREATED_AT
        } else {
            orderByClause += "FA.USERNAME";  // Mặc định sắp xếp theo USERNAME nếu không có tham số hợp lệ
        }
    
        // Câu lệnh SQL lấy danh sách bạn bè của admin và thông tin ngày tạo tài khoản của bạn
        // Nếu có `searchUsername`, lọc theo tên người dùng
        String sqlFriends = "SELECT CASE WHEN UF.ID = ? THEN UF.FRIEND_ID ELSE UF.ID END AS FRIEND_ID, " +
                            "FA.USERNAME, FA.CREATED_AT " +
                            "FROM USER_FRIEND UF " +
                            "JOIN USER_ACCOUNT FA ON FA.ID = UF.FRIEND_ID " +
                            "WHERE UF.ID = ? AND UF.FRIEND_ID != ? ";
    
        // Nếu searchUsername không rỗng, thêm điều kiện tìm kiếm tên
        if (searchUsername != null && !searchUsername.trim().isEmpty()) {
            sqlFriends += "AND FA.USERNAME LIKE ? ";
        }
    
        sqlFriends += orderByClause;
    
        // Câu lệnh SQL lấy số lượng bạn bè chung giữa admin và một người bạn
        String sqlCommonFriends = "SELECT COUNT(DISTINCT uf1.FRIEND_ID) FROM USER_FRIEND uf1 " +
                                  "JOIN USER_FRIEND uf2 ON uf1.FRIEND_ID = uf2.FRIEND_ID " +
                                  "WHERE uf1.ID = ? AND uf2.ID = ? AND uf1.FRIEND_ID != ?";
    
        // Câu lệnh SQL lấy tổng số bạn bè của một người bạn
        String sqlTotalFriends = "SELECT COUNT(DISTINCT CASE WHEN ID = ? THEN FRIEND_ID ELSE ID END) " +
                                 "FROM USER_FRIEND WHERE ID = ? OR FRIEND_ID = ?";
    
        try (PreparedStatement stmtFriends = conn.prepareStatement(sqlFriends);
             PreparedStatement stmtCommonFriends = conn.prepareStatement(sqlCommonFriends);
             PreparedStatement stmtTotalFriends = conn.prepareStatement(sqlTotalFriends)) {
    
            // Truy vấn danh sách bạn bè của admin
            stmtFriends.setInt(1, adminId);
            stmtFriends.setInt(2, adminId);
            stmtFriends.setInt(3, adminId);
    
            // Nếu có searchUsername, truyền tham số vào câu lệnh SQL
            if (searchUsername != null && !searchUsername.trim().isEmpty()) {
                stmtFriends.setString(4, "%" + searchUsername + "%"); // Thêm điều kiện tìm kiếm tên người dùng
            }
    
            try (ResultSet rsFriends = stmtFriends.executeQuery()) {
                // Duyệt qua từng bạn bè của admin
                while (rsFriends.next()) {
                    int friendId = rsFriends.getInt("FRIEND_ID");
                    String username = rsFriends.getString("USERNAME");
                    Timestamp createdAt = rsFriends.getTimestamp("CREATED_AT"); // Lấy CREATED_AT từ kết quả truy vấn
    
                    // Tính số lượng bạn bè chung giữa admin và người bạn này
                    stmtCommonFriends.setInt(1, adminId);
                    stmtCommonFriends.setInt(2, friendId);
                    stmtCommonFriends.setInt(3, adminId); // Loại bỏ chính admin khỏi tính toán bạn bè chung
                    int commonFriends = 0;
                    try (ResultSet rsCommonFriends = stmtCommonFriends.executeQuery()) {
                        if (rsCommonFriends.next()) {
                            commonFriends = rsCommonFriends.getInt(1);
                        }
                    }
    
                    // Kiểm tra số lượng bạn bè chung, nếu lớn hơn hoặc bằng NumCommonFriend thì mới thêm vào danh sách
                    if (commonFriends >= NumCommonFriend) {
                        // Tính tổng số bạn bè của người bạn này
                        stmtTotalFriends.setInt(1, friendId);
                        stmtTotalFriends.setInt(2, friendId);
                        stmtTotalFriends.setInt(3, friendId);
                        int totalFriends = 0;
                        try (ResultSet rsTotalFriends = stmtTotalFriends.executeQuery()) {
                            if (rsTotalFriends.next()) {
                                totalFriends = rsTotalFriends.getInt(1);
                            }
                        }
    
                        // Tạo đối tượng AdminsFriendDTO và thêm vào danh sách
                        AdminsFriendDTO adminsFriendDTO = new AdminsFriendDTO(friendId, username, commonFriends, totalFriends, createdAt);
                        adminsFriendDTOList.add(adminsFriendDTO);
                    }
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
    
        return adminsFriendDTOList;
    }
    
    
}