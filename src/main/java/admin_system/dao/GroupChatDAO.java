package admin_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import admin_system.dto.GroupChatDTO;
import connect_db.UtilityDAO;

public class GroupChatDAO {

    public List<GroupChatDTO> getAllGroupChats(String orderBy) {
        List<GroupChatDTO> groupChatList = new ArrayList<>();
        UtilityDAO utilityDAO = new UtilityDAO();
        Connection conn = utilityDAO.getConnection();

        if (conn == null) {
            return groupChatList; // Nếu không thể kết nối, trả về danh sách rỗng
        }

        String orderByColumn;
        switch (orderBy) {
            case "Group Name":
                orderByColumn = "GROUP_NAME";
                break;
            case "Created at":
                orderByColumn = "CREATED_AT";
                break;
            default: // Mặc định là "ID"
                orderByColumn = "ID";
                break;
        }
        // Truy vấn SQL lấy tất cả các nhóm chat
        String query = "SELECT ID, GROUP_NAME, CREATED_AT FROM GROUPCHAT ORDER BY "+ orderByColumn;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Không cần tham số nào vì không lọc dữ liệu
            ResultSet rs = stmt.executeQuery();

            // Duyệt qua kết quả truy vấn và tạo danh sách GroupChatDTO
            while (rs.next()) {
                int id = rs.getInt("ID");
                String groupName = rs.getString("GROUP_NAME");
                Timestamp createdAt = rs.getTimestamp("CREATED_AT");

                GroupChatDTO groupChat = new GroupChatDTO(id, groupName, createdAt);
                groupChatList.add(groupChat);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }

        return groupChatList;
    }


    public List<GroupChatDTO> getByGroupName(String groupName) {
        List<GroupChatDTO> groupChatList = new ArrayList<>();
        UtilityDAO utilityDAO = new UtilityDAO();
        Connection conn = utilityDAO.getConnection();
    
        if (conn == null) {
            return groupChatList; // Nếu không thể kết nối, trả về danh sách rỗng
        }
    
        // Truy vấn SQL để tìm nhóm chat theo tên
        String query = "SELECT ID, GROUP_NAME, CREATED_AT FROM GROUPCHAT WHERE GROUP_NAME LIKE ?";
    
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Sử dụng LIKE để hỗ trợ tìm kiếm gần đúng
            stmt.setString(1, "%" + groupName + "%");
            ResultSet rs = stmt.executeQuery();
    
            // Duyệt qua kết quả truy vấn và thêm vào danh sách GroupChatDTO
            while (rs.next()) {
                int id = rs.getInt("ID");
                String groupNameResult = rs.getString("GROUP_NAME");
                Timestamp createdAt = rs.getTimestamp("CREATED_AT");
    
                GroupChatDTO groupChat = new GroupChatDTO(id, groupNameResult, createdAt);
                groupChatList.add(groupChat);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
    
        return groupChatList;
    }

    public List<Integer> getIDByPosition(int groupID, String position) {
        List<Integer> memberIDs = new ArrayList<>();
        UtilityDAO utilityDAO = new UtilityDAO();
        Connection conn = utilityDAO.getConnection();
    
        if (conn == null) {
            return memberIDs; // Nếu không thể kết nối, trả về danh sách rỗng
        }
    
        // Truy vấn SQL để lấy danh sách MEMBER_ID theo GROUPCHAT_ID và POSITION
        String query = "SELECT MEMBER_ID FROM GROUPCHAT_MEMBER WHERE GROUPCHAT_ID = ? AND POSITION = ?";
    
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Gán tham số cho câu lệnh truy vấn
            stmt.setInt(1, groupID);
            stmt.setString(2, position);
    
            ResultSet rs = stmt.executeQuery();
    
            // Duyệt qua kết quả truy vấn và thêm MEMBER_ID vào danh sách
            while (rs.next()) {
                memberIDs.add(rs.getInt("MEMBER_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi nếu có
        }
    
        return memberIDs;
    }
    
    
}
