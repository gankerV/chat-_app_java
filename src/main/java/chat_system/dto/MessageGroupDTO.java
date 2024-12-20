package chat_system.dto;

import java.sql.Timestamp;

public class MessageGroupDTO {
    private int id; // ID của tin nhắn
    private int fromUser; // ID người gửi
    private int toGroup; // ID nhóm nhận
    private Timestamp timeSend; // Thời gian gửi tin nhắn
    private String content; // Nội dung tin nhắn

    // Constructor không tham số
    public MessageGroupDTO() {}

    // Constructor đầy đủ tham số
    public MessageGroupDTO(int id, int fromUser, int toGroup, Timestamp timeSend, String content) {
        this.id = id;
        this.fromUser = fromUser;
        this.toGroup = toGroup;
        this.timeSend = timeSend;
        this.content = content;
    }
    // Constructor không id
    public MessageGroupDTO(int fromUser, int toGroup, Timestamp timeSend, String content) {
        this.fromUser = fromUser;
        this.toGroup = toGroup;
        this.timeSend = timeSend;
        this.content = content;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromUser() {
        return fromUser;
    }

    public void setFromUser(int fromUser) {
        this.fromUser = fromUser;
    }

    public int getToGroup() {
        return toGroup;
    }

    public void setToGroup(int toGroup) {
        this.toGroup = toGroup;
    }

    public Timestamp getTimeSend() {
        return timeSend;
    }

    public void setTimeSend(Timestamp timeSend) {
        this.timeSend = timeSend;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Phương thức toString để hiển thị thông tin tin nhắn
    @Override
    public String toString() {
        return "MessageGroupDTO{" +
                "id=" + id +
                ", fromUser=" + fromUser +
                ", toGroup=" + toGroup +
                ", timeSend=" + timeSend +
                ", content='" + content + '\'' +
                '}';
    }
}
