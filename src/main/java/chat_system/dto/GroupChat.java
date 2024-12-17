package chat_system.dto;

public class GroupChat {
    private int id;
    private String name;

    // Constructor
    public GroupChat(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter cho id
    public int getId() {
        return id;
    }

    // Setter cho id
    public void setId(int id) {
        this.id = id;
    }

    // Getter cho name
    public String getName() {
        return name;
    }

    // Setter cho name
    public void setName(String name) {
        this.name = name;
    }

    // Override phương thức toString để hiển thị trong danh sách
    @Override
    public String toString() {
        return name; // Hiển thị tên nhóm trong JList
    }
}
