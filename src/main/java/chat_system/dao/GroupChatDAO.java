package chat_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect_db.UtilityDAO;
import chat_system.dto.GroupChat;

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

    
}
