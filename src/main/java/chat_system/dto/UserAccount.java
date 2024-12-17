/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat_system.dto;

/**
 *
 * @author tin75
 */
public class UserAccount {
    private int id;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String address;
    private String dob;
    private String gender;

    public UserAccount( String username, String password, String fullname, String email, String address, String dob, String gender) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
    }

    // Getters and Setters
    public int getUserId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }


}

