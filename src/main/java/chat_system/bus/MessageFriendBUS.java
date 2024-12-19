package chat_system.bus;

import java.util.List;

import chat_system.dao.MessageFriendDAO;
import chat_system.dto.MessageFriendDTO;

/**
 * Class này xử lý các thao tác liên quan đến tin nhắn giữa hai người dùng.
 */
public class MessageFriendBUS {
    private final MessageFriendDAO messageFriendDAO;

    public MessageFriendBUS() {
        messageFriendDAO = new MessageFriendDAO();
    }

    /**
     * Thêm tin nhắn vào cơ sở dữ liệu.
     * 
     * @param message Tin nhắn cần thêm.
     * @return true nếu tin nhắn được thêm thành công, false nếu thất bại.
     */
    public boolean saveMessage(MessageFriendDTO message) {
        // Có thể thêm một số kiểm tra hợp lệ cho nội dung tin nhắn
        if (message.getContent().length() < 1) {
            return false;  // Tin nhắn không thể rỗng
        }

        // Gọi lớp DAO để thêm tin nhắn vào cơ sở dữ liệu
        return messageFriendDAO.addMessage(message);
    }


    /**
     * Lấy danh sách tin nhắn giữa hai người dùng.
     * 
     * @param senderId ID của người gửi.
     * @param receiverId ID của người nhận.
     * @return danh sách tin nhắn giữa người gửi và người nhận.
     */
    public List<MessageFriendDTO> getMessage(int senderId, int receiverId) {
        // Gọi lớp DAO để lấy danh sách tin nhắn từ cơ sở dữ liệu
        return messageFriendDAO.getMessages(senderId, receiverId);
    }
}
