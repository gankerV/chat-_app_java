package chat_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connect_db.UtilityDAO;

public class MessageDAO {
    private final Connection conn;

    public MessageDAO() {
        UtilityDAO utilityDAO = new UtilityDAO();
        this.conn = utilityDAO.getConnection();  // Assume DatabaseConnection is a class for DB connection
    }

    // Xóa toàn bộ tin nhắn giữa hai người dùng
    public boolean deleteAllMessages(int fromUserId, int toUserId) throws SQLException {
        String sql = "DELETE FROM MESSAGE_USER WHERE (FROM_USER = ? AND TO_USER = ?) OR (FROM_USER = ? AND TO_USER = ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, fromUserId);
            stmt.setInt(2, toUserId);
            stmt.setInt(3, toUserId);
            stmt.setInt(4, fromUserId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    // Xóa tin nhắn mới nhất giữa hai người dùng
    public boolean deleteLatestMessage(int fromUserId, int toUserId) throws SQLException {
        String sql = """
                        DELETE FROM MESSAGE_USER 
                        WHERE ID = (
                            SELECT ID FROM (
                                SELECT ID 
                                FROM chat_system.MESSAGE_USER 
                                WHERE (FROM_USER = ? AND TO_USER = ? ) 
                                OR (FROM_USER = ? AND TO_USER = ?) 
                                ORDER BY SEND_AT DESC 
                                LIMIT 1
                            ) AS temp
                        );
                        """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, fromUserId);
            stmt.setInt(2, toUserId);
            stmt.setInt(3, toUserId);
            stmt.setInt(4, fromUserId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public List<String> getChatHistory(int fromUserId, int toUserId) throws SQLException {
        String sql = """
            SELECT 
                m.SEND_AT, 
                m.CONTENT, 
                u.USERNAME AS FROM_USERNAME 
            FROM 
                MESSAGE_USER m
            JOIN 
                USER_ACCOUNT u 
            ON 
                m.FROM_USER = u.ID
            WHERE 
                (m.FROM_USER = ? AND m.TO_USER = ?) OR (m.FROM_USER = ? AND m.TO_USER = ?)
            ORDER BY 
                m.SEND_AT ASC
        """;
    
        List<String> messages = new ArrayList<>();
    
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, fromUserId);
            stmt.setInt(2, toUserId);
            stmt.setInt(3, toUserId);
            stmt.setInt(4, fromUserId);
    
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String formattedMessage = "[" + rs.getTimestamp("SEND_AT") + "] " + 
                                              rs.getString("FROM_USERNAME") + ": " + 
                                              rs.getString("CONTENT");
                    messages.add(formattedMessage);
                }
            }
        }
        return messages;
    }

    public void addMessage(int fromUserId, int toUserId, String content) throws SQLException {
        String sql = "INSERT INTO MESSAGE_USER (FROM_USER, TO_USER, SEND_AT, CONTENT, VISIBLE_ONLY) VALUES (?, ?, CURRENT_TIMESTAMP, ?, 0)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, fromUserId);
            stmt.setInt(2, toUserId);
            stmt.setString(3, content);
            stmt.executeUpdate();
        }
    }

    
    public Map<String, Object> getLastMessageDetails(int fromUserId, int toUserId) throws SQLException {
        String sql = "SELECT mu.SEND_AT, ua.USERNAME " +
                    "FROM MESSAGE_USER mu " +
                    "JOIN USER_ACCOUNT ua ON mu.FROM_USER = ua.ID " +
                    "WHERE mu.FROM_USER = ? AND mu.TO_USER = ? " +
                    "ORDER BY mu.SEND_AT DESC LIMIT 1";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, fromUserId);
            stmt.setInt(2, toUserId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Map<String, Object> result = new HashMap<>();
                result.put("SEND_AT", rs.getTimestamp("SEND_AT"));
                result.put("USERNAME", rs.getString("USERNAME"));
                return result;
            }
        }

        return null; // Không tìm thấy
    }

    //Tìm kiếm lịch sử chat với một người
    public List<String> searchMessagesWithUser(int currentUserId, int otherUserId, String keyword) throws SQLException {
        String sql = "SELECT mu.SEND_AT, ua.USERNAME, mu.CONTENT " +
                     "FROM MESSAGE_USER mu " +
                     "JOIN USER_ACCOUNT ua ON mu.FROM_USER = ua.ID " +
                     "WHERE ((mu.FROM_USER = ? AND mu.TO_USER = ?) OR (mu.FROM_USER = ? AND mu.TO_USER = ?)) " +
                     "AND mu.CONTENT LIKE ? " +
                     "ORDER BY mu.SEND_AT";
    
        List<String> messages = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, currentUserId);
            stmt.setInt(2, otherUserId);
            stmt.setInt(3, otherUserId);
            stmt.setInt(4, currentUserId);
            stmt.setString(5, "%" + keyword + "%");
    
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String formattedMessage = "[" + rs.getTimestamp("SEND_AT") + "] " +
                                          rs.getString("USERNAME") + ": " +
                                          rs.getString("CONTENT");
                messages.add(formattedMessage);
            }
        }
        return messages;
    }

    //Tìm kiếm trong lịch sử chat với tất cả mọi người
    public List<String> searchMessagesWithAllUsers(int currentUserId, String keyword) throws SQLException {
        String sql = "SELECT mu.SEND_AT, ua.USERNAME, mu.CONTENT " +
                     "FROM MESSAGE_USER mu " +
                     "JOIN USER_ACCOUNT ua ON mu.FROM_USER = ua.ID " +
                     "WHERE (mu.FROM_USER = ? OR mu.TO_USER = ?) " +
                     "AND mu.CONTENT LIKE ? " +
                     "ORDER BY mu.SEND_AT";
    
        List<String> messages = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, currentUserId);
            stmt.setInt(2, currentUserId);
            stmt.setString(3, "%" + keyword + "%");
    
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String formattedMessage = "[" + rs.getTimestamp("SEND_AT") + "] " +
                                          rs.getString("USERNAME") + ": " +
                                          rs.getString("CONTENT");
                messages.add(formattedMessage);
            }
        }
        return messages;
    }

    
    
    

    
    



}

