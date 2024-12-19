package chat_system.dto;

import java.sql.Timestamp;

public class MessageFriendDTO {
    private int id;
    private int fromUser;
    private int toUser;
    private Timestamp sendAt;
    private String content;
    private int visibleOnly;

    // Constructor
    public MessageFriendDTO(int id, int fromUser, int toUser, Timestamp sendAt, String content, int visibleOnly) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.sendAt = sendAt;
        this.content = content;
        this.visibleOnly = visibleOnly;
    }
    // Constructor no id 
    public MessageFriendDTO( int fromUser, int toUser, Timestamp sendAt, String content, int visibleOnly) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.sendAt = sendAt;
        this.content = content;
        this.visibleOnly = visibleOnly;
    }

    // Getters and Setters
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

    public int getToUser() {
        return toUser;
    }

    public void setToUser(int toUser) {
        this.toUser = toUser;
    }

    public Timestamp getSendAt() {
        return sendAt;
    }

    public void setSendAt(Timestamp sendAt) {
        this.sendAt = sendAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVisibleOnly() {
        return visibleOnly;
    }

    public void setVisibleOnly(int visibleOnly) {
        this.visibleOnly = visibleOnly;
    }

    // Optional: Override toString for better output
    @Override
    public String toString() {
        return "MessageFriendDTO{" +
                "id=" + id +
                ", fromUser=" + fromUser +
                ", toUser=" + toUser +
                ", sendAt=" + sendAt +
                ", content='" + content + '\'' +
                ", visibleOnly=" + visibleOnly +
                '}';
    }
}
