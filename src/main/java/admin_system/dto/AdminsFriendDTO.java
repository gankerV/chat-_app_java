package admin_system.dto;

import java.sql.Timestamp;

public class AdminsFriendDTO {
    private int id;
    private String username;
    private Timestamp createdAt; 
    private int numDirectFriends;
    private int numTotalFriends;

    // Constructor bao gồm createdAt
    public AdminsFriendDTO(int id, String username, int numDirectFriends, int numTotalFriends, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.numDirectFriends = numDirectFriends;
        this.numTotalFriends = numTotalFriends;
        this.createdAt = createdAt; 
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNumDirectFriends() {
        return numDirectFriends;
    }

    public void setNumDirectFriends(int numDirectFriends) {
        this.numDirectFriends = numDirectFriends;
    }

    public int getNumTotalFriends() {
        return numTotalFriends;
    }

    public void setNumTotalFriends(int numTotalFriends) {
        this.numTotalFriends = numTotalFriends;
    }

    public Timestamp getCreatedAt() {
        return createdAt; 
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt; 
    }

    @Override
    public String toString() {
        return "AdminsFriendDTO{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", createdAt=" + createdAt +  
               ", numDirectFriends=" + numDirectFriends +
               ", numTotalFriends=" + numTotalFriends +
               '}';
    }
}
