/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat_system.dao;


import chat_system.dto.User;
import chat_system.dto.UserAccount;
import connect_db.UtilityDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserAccountDAO {
    private final Connection conn;

    public UserAccountDAO() {
        UtilityDAO utilityDAO = new UtilityDAO();
        this.conn = utilityDAO.getConnection();  // Assume DatabaseConnection is a class for DB connection
    }
    
    public boolean isEmailExists(String email) {
        String query = "SELECT COUNT(*) FROM USER_ACCOUNT WHERE EMAIL = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean addUser(UserAccount user) {
        String sql = "INSERT INTO USER_ACCOUNT (USERNAME, PASSWORD, FULLNAME, EMAIL, ADDRESS, DATE_OF_BIRTH, GENDER, ON_OFF, BANNED) VALUES (?, ?, ?, ?, ?, ?, ?, FALSE, FALSE)";
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        System.out.println("Fullname: " + user.getFullname());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Address: " + user.getAddress());
        System.out.println("DOB: " + user.getDob());
        System.out.println("Gender: " + user.getGender());

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFullname());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getDob());
            stmt.setString(7, user.getGender());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // If at least one row is inserted
        } catch (SQLException e) {
            System.out.println("Fail: " + e.getMessage());
            return false;  // If something goes wrong
        }
    }
    
    public boolean validateUser(String email, String password) {
        String sql = "SELECT * FROM USER_ACCOUNT WHERE EMAIL = ? AND PASSWORD = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();  // If there is a record, the user is valid
        } catch (SQLException e) {
            System.err.println("Error during login: " + e.getMessage());
            return false;
        }
    }
    
    public boolean updateUserStatus(String id, boolean status) {
        String sql = "UPDATE USER_ACCOUNT SET ON_OFF = ? WHERE ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, status);  // TRUE/FALSE
            stmt.setString(2, id);   // Email của người dùng
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Nếu ít nhất một hàng được cập nhật
        } catch (SQLException e) {
            System.out.println("Error updating ON_OFF: " + e.getMessage());
            return false;
        }
    }

    public boolean updatePassword(String email, String newPassword) {
        String sql = "UPDATE USER_ACCOUNT SET PASSWORD = ? WHERE EMAIL = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, email);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Cập nhật thành công
        } catch (SQLException e) {
            System.out.println("Error updating password: " + e.getMessage());
            return false;
        }
    }

    public String getUserIdByEmail(String email) throws SQLException {
        String query = "SELECT ID FROM USER_ACCOUNT WHERE EMAIL = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy
    }

    public String getEmailByUserId(String userId) throws SQLException {
        String query = "SELECT EMAIL FROM USER_ACCOUNT WHERE ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("EMAIL");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy
    }

    // Lấy thông tin user từ database
    public UserAccount getUserByEmail(String email) {
        String sql = "SELECT * FROM USER_ACCOUNT WHERE EMAIL = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UserAccount(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("fullname"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getString("date_of_birth"),
                    rs.getString("gender")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public UserAccount getUserByID(String id) {
        String sql = "SELECT * FROM USER_ACCOUNT WHERE ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UserAccount(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("fullname"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getString("date_of_birth"),
                    rs.getString("gender")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // Cập nhật thông tin user
    public boolean updateUser(UserAccount user, String userId) {
        String sql = "UPDATE USER_ACCOUNT SET USERNAME = ?, EMAIL = ?,FULLNAME = ?,  ADDRESS = ?, DATE_OF_BIRTH = ?, GENDER = ? WHERE ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getFullname());
            stmt.setString(4, user.getAddress());
            stmt.setString(5, user.getDob());
            stmt.setString(6, user.getGender());
            stmt.setString(7, userId); 

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Phương thức để lấy danh sách tất cả người dùng từ database
    public List<User> getAllUsersExcludingBlocked(int currentUserID) throws SQLException {
        List<User> users = new ArrayList<>();
        String query = """
                SELECT ua.id, ua.username, ua.on_off, ua.email
                FROM USER_ACCOUNT ua
                WHERE ua.id != ? AND ua.id NOT IN (
                    SELECT BLOCK_ID FROM USER_BLOCK WHERE USER_ID = ?
                )
                """;
    
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, currentUserID); // Không hiển thị chính mình
            stmt.setInt(2, currentUserID); // Lọc người đã bị block
    
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    boolean isOnline = rs.getBoolean("on_off");
                    String status = isOnline ? "online" : "offline";
                    String email = rs.getString("email");
                    users.add(new User(id, username, status, email));
                }
            }
        }
    
        return users;
    }
    

    // Phuong thuc kiem tra quan he ban be
    public boolean checkFriendship(int currentUserId, int selectedUserId) throws SQLException {
        String query = "SELECT COUNT(*) FROM USER_FRIEND WHERE (id = ? AND friend_id = ?) OR (friend_id = ? AND id = ?)";
        try (
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setInt(1, currentUserId);
            stmt.setInt(2, selectedUserId);
            stmt.setInt(3, selectedUserId);
            stmt.setInt(4, currentUserId);
    
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Có tồn tại quan hệ bạn bè
                }
            }
        }
        return false;
    }

    // Phuong thuc xoa quan he ban be
    public boolean unfriend(int userId, int friendId) throws SQLException {
        String deleteFriendshipSql = "DELETE FROM USER_FRIEND WHERE (ID = ? AND FRIEND_ID = ?) OR (ID = ? AND FRIEND_ID = ?)";
        String updateFriendRequestSql = "UPDATE FRIEND_REQUEST SET STATUS = 'Cancel' WHERE (FROM_ID = ? AND TO_ID = ?) OR (FROM_ID = ? AND TO_ID = ?)";
        
        try {
            // Bắt đầu giao dịch
            conn.setAutoCommit(false);
    
            // Xóa quan hệ bạn bè
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteFriendshipSql)) {
                deleteStmt.setInt(1, userId);
                deleteStmt.setInt(2, friendId);
                deleteStmt.setInt(3, friendId);
                deleteStmt.setInt(4, userId);
                deleteStmt.executeUpdate();
            }
    
            // Cập nhật trạng thái trong FRIEND_REQUEST
            try (PreparedStatement updateStmt = conn.prepareStatement(updateFriendRequestSql)) {
                updateStmt.setInt(1, userId);
                updateStmt.setInt(2, friendId);
                updateStmt.setInt(3, friendId);
                updateStmt.setInt(4, userId);
                updateStmt.executeUpdate();
            }
    
            // Commit giao dịch
            conn.commit();
            return true;
        } catch (SQLException e) {
            // Rollback nếu có lỗi
            conn.rollback();
            throw e;
        } finally {
            // Đặt lại chế độ tự động commit
            conn.setAutoCommit(true);
        }
    }
    


    // Phuong thuc kiem tra lop moi kết bạn
    public boolean hasFriendRequest(int fromID, int toID) throws SQLException {
        String query = "SELECT COUNT(*) FROM FRIEND_REQUEST WHERE FROM_ID = ? AND TO_ID = ? AND STATUS = 'Pending'";
        try (
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, fromID);
            stmt.setInt(2, toID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Nếu COUNT(*) > 0, nghĩa là có lời mời
                }
            }
        }
        return false;
    }

    //  Phuong thuc chap nhan loi moi ket ban
    public void acceptFriendRequest(int fromID, int toID) throws SQLException {
        String updateStatusQuery = "UPDATE FRIEND_REQUEST SET STATUS = 'Accepted' WHERE FROM_ID = ? AND TO_ID = ?";
        String insertFriendQuery1 = "INSERT INTO USER_FRIEND(ID, FRIEND_ID) VALUES(?, ?)";
        String insertFriendQuery2 = "INSERT INTO USER_FRIEND(FRIEND_ID, ID) VALUES(?, ?)";
        try (
             PreparedStatement updateStmt = conn.prepareStatement(updateStatusQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertFriendQuery1);
             PreparedStatement insertStmt2 = conn.prepareStatement(insertFriendQuery2)) {
            conn.setAutoCommit(false);
            updateStmt.setInt(1, fromID);
            updateStmt.setInt(2, toID);
            updateStmt.executeUpdate();
    
            insertStmt.setInt(1, fromID);
            insertStmt.setInt(2, toID);
            insertStmt.executeUpdate();

            insertStmt2.setInt(1, fromID);
            insertStmt2.setInt(2, toID);
            insertStmt2.executeUpdate();
    
            conn.commit();
        } catch (SQLException ex) {
            throw new SQLException("Lỗi khi chấp nhận lời mời kết bạn: " + ex.getMessage(), ex);
        }
    }

    // Phuong thuc tu choi loi moi ket ban
    public void rejectFriendRequest(int fromID, int toID) throws SQLException {
        String query = "UPDATE FRIEND_REQUEST SET STATUS = 'Rejected' WHERE FROM_ID = ? AND TO_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, fromID);
            stmt.setInt(2, toID);
            stmt.executeUpdate();
        }
    }

    // Phuong thuc gui loi moi ket ban
    public boolean sendFriendRequest(int fromId, int toId) throws SQLException {
        String checkStatusSql = "SELECT STATUS FROM FRIEND_REQUEST WHERE (FROM_ID = ? AND TO_ID = ?) OR (FROM_ID = ? AND TO_ID = ?)";
        String insertRequestSql = "INSERT INTO FRIEND_REQUEST (FROM_ID, TO_ID, STATUS) VALUES (?, ?, 'Pending')";
    
        try (PreparedStatement checkStmt = conn.prepareStatement(checkStatusSql)) {
            // Kiểm tra trạng thái hiện tại
            checkStmt.setInt(1, fromId);
            checkStmt.setInt(2, toId);
            checkStmt.setInt(3, toId);
            checkStmt.setInt(4, fromId);
    
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    String status = rs.getString("STATUS");
                    if ("Cancel".equalsIgnoreCase(status) || "Rejected".equalsIgnoreCase(status) || "Pending".equalsIgnoreCase(status)) {
                        return false; // Không gửi yêu cầu kết bạn nếu trạng thái là Cancel hoặc Rejected
                    }
                }
            }
        }
    
        // Thêm yêu cầu kết bạn nếu không có trạng thái nào ngăn cản
        try (PreparedStatement insertStmt = conn.prepareStatement(insertRequestSql)) {
            insertStmt.setInt(1, fromId);
            insertStmt.setInt(2, toId);
            int rowsInserted = insertStmt.executeUpdate();
            return rowsInserted > 0;
        }
    }   
    
    // Phuong thuc kiem tra da bao cao chua
    public boolean checkIfAlreadyReported(int reporterId, int reportedId) throws SQLException {
        String sql = "SELECT 1 FROM REPORT_SPAM WHERE REPORTER_ID = ? AND REPORTED_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reporterId);
            stmt.setInt(2, reportedId);
    
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Trả về true nếu đã tồn tại báo cáo
            }
        }
    }

    // Phuong thuc bao cao spam
    public boolean reportSpam(int reporterId, int reportedId) throws SQLException {
        String sql = "INSERT INTO REPORT_SPAM (REPORTER_ID, REPORTED_ID, CONTENT, REPORT_AT) VALUES (?, ?, 'Spam', CURRENT_TIMESTAMP)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reporterId);
            stmt.setInt(2, reportedId);
    
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Trả về true nếu báo cáo thành công
        }
    }

    // Phuong thuc kiem tra da bao cao chua
    public boolean isBlockedUser(int userId, int blockId) throws SQLException {
        String sql = "SELECT 1 FROM USER_BLOCK WHERE USER_ID = ? AND BLOCK_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, blockId);
    
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Trả về true nếu đã tồn tại bản ghi
            }
        }
    }
    
    //  Phuong thuc khoa nguoi dung
    public boolean blockUser(int userId, int blockId) throws SQLException {
        String sql = "INSERT INTO USER_BLOCK (USER_ID, BLOCK_ID) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, blockId);
    
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Trả về true nếu thêm thành công
        }
    }

    // Phuong thuc lay danh sach nguoi bi khoa
    public List<User> getBlockedUsers(int currentUserID) throws SQLException {
        List<User> blockedUsers = new ArrayList<>();
        String query = """
                SELECT ua.id, ua.username, ua.on_off, ua.email
                FROM USER_ACCOUNT ua
                INNER JOIN USER_BLOCK ub ON ua.id = ub.BLOCK_ID
                WHERE ub.USER_ID = ?
                """;
    
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, currentUserID);
    
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    boolean isOnline = rs.getBoolean("on_off");
                    String status = isOnline ? "online" : "offline";
                    String email = rs.getString("email");
                    blockedUsers.add(new User(id, username, status, email));
                }
            }
        }
    
        return blockedUsers;
    }
    
    // Phuong thuc bo khoa nguoi dung
    public void unblockUser(int currentUserID, int blockedUserID) throws SQLException {
        String query = "DELETE FROM USER_BLOCK WHERE USER_ID = ? AND BLOCK_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, currentUserID);
            stmt.setInt(2, blockedUserID);
            stmt.executeUpdate();
        }
    }
    
    
    
    
    


}

