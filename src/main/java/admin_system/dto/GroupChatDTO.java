package admin_system.dto;


import java.sql.Timestamp;

public class GroupChatDTO {
    private int id;              // ID
    private String groupName;    // GROUP_NAME
    private Timestamp createdAt; // CREATED_AT

    // Constructor
    public GroupChatDTO(int id, String groupName, Timestamp createdAt) {
        this.id = id;
        this.groupName = groupName;
        this.createdAt = createdAt;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "GroupChatDTO{" +
               "id=" + id +
               ", groupName='" + groupName + '\'' +
               ", createdAt=" + createdAt +
               '}';
    }
}
