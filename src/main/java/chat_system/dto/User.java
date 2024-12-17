package chat_system.dto;

public class User {
    private int id;
    private String username;
    private String status; // online/offline
    private String email;

    public User(int id, String username, String status, String email) {    
        this.id = id;
        this.username = username;
        this.status = status;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return username;
    }
}

