package admin_system.dto;

import java.sql.Timestamp;

public class UserStatusDTO {
    private int id;
    private String username;
    private Timestamp createdAt;
    private int numLogged;
    private int numChatUser;
    private int numChatGroup;

    // Constructor
    public UserStatusDTO(int id, String username, Timestamp createdAt, int numLogged, int numChatUser, int numChatGroup) {
        this.id = id;
        this.username = username;
        this.createdAt = createdAt;
        this.numLogged = numLogged;
        this.numChatUser = numChatUser;
        this.numChatGroup = numChatGroup;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getNumLogged() {
        return numLogged;
    }

    public void setNumLogged(int numLogged) {
        this.numLogged = numLogged;
    }

    public int getNumChatUser() {
        return numChatUser;
    }

    public void setNumChatUser(int numChatUser) {
        this.numChatUser = numChatUser;
    }

    public int getNumChatGroup() {
        return numChatGroup;
    }

    public void setNumChatGroup(int numChatGroup) {
        this.numChatGroup = numChatGroup;
    }
}