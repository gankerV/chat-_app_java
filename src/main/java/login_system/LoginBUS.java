package login_system;

public class LoginBUS {
    private LoginDAO loginDAO;

    // Constructor
    public LoginBUS() {
        this.loginDAO = new LoginDAO();
    }

    public int checkLogin(String email, String password) {
        return loginDAO.checkLogin(email, password);
    }
}
