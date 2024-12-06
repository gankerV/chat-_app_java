package admin_system.dto;

import java.sql.Timestamp;
import java.util.Date;

public class UserAccountDTO {
    private int id;
    private String username;
    private String password;
    private String fullname;
    private String address;
    private Date dateOfBirth;
    private String gender;
    private String email;
    private boolean onOff;
    private Timestamp createdAt;
    private boolean banned;

    // Constructor không tham số
    public UserAccountDTO() {
        this.id = 0;
        this.username = "";
        this.password = "";
        this.fullname = "";
        this.address = "";
        this.dateOfBirth = null;
        this.gender = "";
        this.email = "";
        this.onOff = false;
        this.createdAt = null;
        this.banned = false;
    }

    // Constructor đầy đủ tham số
    public UserAccountDTO(int id, String username, String password, String fullname, String address, Date dateOfBirth,
                          String gender, String email, boolean onOff, Timestamp createdAt, boolean banned) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.onOff = onOff;
        this.createdAt = createdAt;
        this.banned = banned;
    }

    // Getter và Setter cho từng thuộc tính
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOnOff() {
        return onOff;
    }

    public void setOnOff(boolean onOff) {
        this.onOff = onOff;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    // Override phương thức toString để hiển thị thông tin đối tượng
    @Override
    public String toString() {
        return "UserAccountDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", onOff=" + onOff +
                ", createdAt=" + createdAt +
                ", banned=" + banned +
                '}';
    }
}
