package chat_system.bus;

import chat_system.dao.MessageGroupDAO;
import chat_system.dto.MessageGroupDTO;

/**
 * Class này xử lý các thao tác liên quan đến tin nhắn giữa hai người dùng.
 */
public class MessageGroupBUS {
    private final MessageGroupDAO messageGroupDAO;

    public MessageGroupBUS() {
        messageGroupDAO = new MessageGroupDAO();
    }

    /**
     * Thêm tin nhắn vào cơ sở dữ liệu.
     * 
     * @param message Tin nhắn cần thêm.
     * @return true nếu tin nhắn được thêm thành công, false nếu thất bại.
     */
    public boolean saveGroupMessage(MessageGroupDTO message) {
        // Có thể thêm một số kiểm tra hợp lệ cho nội dung tin nhắn
        if (message.getContent().length() < 1) {
            return false;  // Tin nhắn không thể rỗng
        }

        // Gọi lớp DAO để thêm tin nhắn vào cơ sở dữ liệu
        return messageGroupDAO.addMessage(message);
    }
}
