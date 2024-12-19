package chat_system.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import chat_system.dto.MessageFriendDTO;
import connect_db.UtilityDAO;

public class MessageFriendDAO {
    private Connection conn;

    public MessageFriendDAO() {
        UtilityDAO utilityDAO = new UtilityDAO();
        this.conn = utilityDAO.getConnection();  // Kết nối với cơ sở dữ liệu qua UtilityDAO
    }

    // Thêm tin nhắn vào cơ sở dữ liệu
    public boolean addMessage(MessageFriendDTO message) {
        String query = """
            INSERT INTO MESSAGE_USER (FROM_USER, TO_USER, SEND_AT, CONTENT, VISIBLE_ONLY)
            VALUES (?, ?, ?, ?, ?);
        """;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Thiết lập các tham số của câu truy vấn
            stmt.setInt(1, message.getFromUser());  // FROM_USER
            stmt.setInt(2, message.getToUser());    // TO_USER
            stmt.setTimestamp(3, message.getSendAt());  // SEND_AT
            stmt.setString(4, message.getContent());  // CONTENT
            stmt.setInt(5, message.getVisibleOnly());  // VISIBLE_ONLY

            // Thực thi câu truy vấn
            stmt.executeUpdate();
            return true;  // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();  // Ghi log nếu có lỗi
            return false;  // Trả về false nếu có lỗi
        }
    }

    // Lấy danh sách tin nhắn giữa người gửi và người nhận
    public List<MessageFriendDTO> getMessages(int senderId, int receiverId) {
        List<MessageFriendDTO> messages = new ArrayList<>();
        String query = """
            SELECT ID, FROM_USER, TO_USER, SEND_AT, CONTENT, VISIBLE_ONLY
            FROM MESSAGE_USER
            WHERE (FROM_USER = ? AND TO_USER = ?)
            ORDER BY SEND_AT ASC;
        """;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Thiết lập các tham số của câu truy vấn
            stmt.setInt(1, senderId);  // FROM_USER
            stmt.setInt(2, receiverId);  // TO_USER (để lấy các tin nhắn đến người gửi)

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Lấy dữ liệu từ kết quả truy vấn và tạo đối tượng MessageFriendDTO
                    int id = rs.getInt("ID");
                    int fromUser = rs.getInt("FROM_USER");
                    int toUser = rs.getInt("TO_USER");
                    Timestamp sendAt = rs.getTimestamp("SEND_AT");
                    String content = rs.getString("CONTENT");
                    int visibleOnly = rs.getInt("VISIBLE_ONLY");

                    // Tạo đối tượng MessageFriendDTO và thêm vào danh sách
                    MessageFriendDTO message = new MessageFriendDTO(fromUser, toUser, sendAt, content, visibleOnly);
                    messages.add(message);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Ghi log nếu có lỗi
        }
        return messages;  // Trả về danh sách tin nhắn
    }
}
