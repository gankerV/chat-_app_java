package login_system;

public class LoginDTO {
    private int id;
    private String email;
    private String password;

    // Constructor
    public LoginDTO(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public int getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
