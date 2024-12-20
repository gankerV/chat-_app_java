package chat_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect_db.UtilityDAO;
import chat_system.dto.GroupChat;
import chat_system.dto.User;

public class GroupChatDAO {
    private Connection conn;

    public GroupChatDAO() {
        UtilityDAO utilityDAO = new UtilityDAO();
        this.conn = utilityDAO.getConnection();  // Assume DatabaseConnection is a class for DB connection
    }

    // Lấy danh sách group của currentUserID
    public List<GroupChat> getGroupsByUserId(int userId) throws SQLException {
        List<GroupChat> groups = new ArrayList<>();
        String query = """
            SELECT g.ID, g.GROUP_NAME
            FROM GROUPCHAT g
            JOIN GROUPCHAT_MEMBER gm ON g.ID = gm.GROUPCHAT_ID
            WHERE gm.MEMBER_ID = ?;
        """;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String name = rs.getString("GROUP_NAME");
                    groups.add(new GroupChat(id, name));
                }
            }
        }
        return groups;
    }

    // Thêm user vào group
    public boolean addUserToGroup(int groupId, int userId) {
        String query = "INSERT INTO GROUPCHAT_MEMBER (GROUPCHAT_ID, MEMBER_ID, POSITION) VALUES (?, ?, 'Member');";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, groupId);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
            return true;  // Return true if the operation is successful
        } catch (SQLException e) {
            e.printStackTrace();  // Optionally log the exception
            return false;  // Return false if there's an error
        }
    }

    // Tạo group mới và thêm admin cùng thành viên đầu tiên
    public  boolean createGroup(String groupName, int adminId, int memberId) {
        String query1 = "INSERT INTO GROUPCHAT (GROUP_NAME, CREATED_AT) VALUES (?, CURRENT_TIMESTAMP);";
        String query2 = """
            INSERT INTO GROUPCHAT_MEMBER (GROUPCHAT_ID, MEMBER_ID, POSITION)
            VALUES (LAST_INSERT_ID(), ?, 'Admin'),
                (LAST_INSERT_ID(), ?, 'Member');
        """;

        try (PreparedStatement stmt1 = conn.prepareStatement(query1);
            PreparedStatement stmt2 = conn.prepareStatement(query2)) {
            // Tạo group
            stmt1.setString(1, groupName);
            stmt1.executeUpdate();

            // Thêm admin và thành viên
            stmt2.setInt(1, adminId);
            stmt2.setInt(2, memberId);
            stmt2.executeUpdate();

            return true;  // Return true if the operation is successful
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();  // Optionally log the exception
            return false;  // Return false if there's an error
        } 
    }

    // Cập nhật tên nhóm
    public boolean updateGroupName(int groupId, String newName) {
        String query = "UPDATE GROUPCHAT SET GROUP_NAME = ? WHERE ID = ?";
    
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newName);
            stmt.setInt(2, groupId);
            int rowsAffected = stmt.executeUpdate();
            
            return rowsAffected > 0; // Return true if rows were updated
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy danh sách admin của group
    public List<User> getAdminsByGroupId(int groupId) throws SQLException {
        List<User> admins = new ArrayList<>();
        String query = """
            SELECT u.ID, u.USERNAME, u.ON_OFF, u.EMAIL
            FROM USER_ACCOUNT u
            JOIN GROUPCHAT_MEMBER gm ON u.ID = gm.MEMBER_ID
            WHERE gm.GROUPCHAT_ID = ? AND gm.POSITION = 'Admin';
        """;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, groupId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String username = rs.getString("USERNAME");
                    String status = rs.getString("ON_OFF");
                    String email = rs.getString("EMAIL");
                    admins.add(new User(id, username, status, email));
                }
            }
        }
        return admins;
    }

    // Lấy danh sách member của group
    public List<User> getMembersByGroupId(int groupId) throws SQLException {
        List<User> members = new ArrayList<>();
        String query = """
            SELECT u.ID, u.USERNAME, u.ON_OFF, u.EMAIL
            FROM USER_ACCOUNT u
            JOIN GROUPCHAT_MEMBER gm ON u.ID = gm.MEMBER_ID
            WHERE gm.GROUPCHAT_ID = ? AND gm.POSITION = 'Member';
        """;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, groupId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String username = rs.getString("USERNAME");
                    String status = rs.getString("ON_OFF");
                    String email = rs.getString("EMAIL");
                    members.add(new User(id, username, status, email));
                }
            }
        }
        return members;
    }

    // Kiểm tra xem người dùng có phải là admin của nhóm không
    public boolean isUserAdmin(int groupId, int userId) throws SQLException {
        String query = """
            SELECT COUNT(*) 
            FROM GROUPCHAT_MEMBER 
            WHERE GROUPCHAT_ID = ? AND MEMBER_ID = ? AND POSITION = 'Admin';
        """;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, groupId);
            stmt.setInt(2, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Nếu COUNT(*) > 0, nghĩa là người dùng là admin của nhóm
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;  // Trả về false nếu không phải admin
    }

    // Xóa thành viên khỏi nhóm
    public boolean removeMemberFromGroup(int groupId, int memberId) {
        String query = "DELETE FROM GROUPCHAT_MEMBER WHERE GROUPCHAT_ID = ? AND MEMBER_ID = ?;";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, groupId);
            stmt.setInt(2, memberId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Trả về true nếu xóa thành công (tức là có ít nhất 1 dòng bị xóa)
        } catch (SQLException e) {
            e.printStackTrace();  // Log lỗi nếu có
            return false;  // Trả về false nếu có lỗi xảy ra
        }
    }

    // Gán quyền admin cho thành viên
    public boolean assignAdminToMember(int groupId, int memberId) {
        String query = "UPDATE GROUPCHAT_MEMBER SET POSITION = 'Admin' WHERE GROUPCHAT_ID = ? AND MEMBER_ID = ?;";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, groupId);
            stmt.setInt(2, memberId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Trả về false nếu có lỗi xảy ra
        }
    }




    




    
}
