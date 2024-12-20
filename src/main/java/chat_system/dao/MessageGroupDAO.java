package chat_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import chat_system.dto.MessageGroupDTO;
import connect_db.UtilityDAO;

public class MessageGroupDAO {
    private Connection conn;

    public MessageGroupDAO() {
        UtilityDAO utilityDAO = new UtilityDAO();
        this.conn = utilityDAO.getConnection(); // Kết nối với cơ sở dữ liệu qua UtilityDAO
    }

    /**
     * Thêm tin nhắn nhóm vào cơ sở dữ liệu.
     * 
     * @param message Tin nhắn nhóm cần thêm.
     * @return true nếu thêm thành công, false nếu xảy ra lỗi.
     */
    public boolean addMessage(MessageGroupDTO message) {
        String query = """
            INSERT INTO MESSAGE_GROUP (FROM_USER, TO_GROUP, TIME_SEND, CONTENT)
            VALUES (?, ?, ?, ?);
        """;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Thiết lập các tham số của câu truy vấn
            stmt.setInt(1, message.getFromUser());      // FROM_USER
            stmt.setInt(2, message.getToGroup());       // TO_GROUP
            stmt.setTimestamp(3, message.getTimeSend()); // TIME_SEND
            stmt.setString(4, message.getContent());    // CONTENT

            // Thực thi câu truy vấn
            stmt.executeUpdate();
            return true; // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace(); // Ghi log nếu có lỗi
            return false; // Trả về false nếu có lỗi
        }
    }
}
