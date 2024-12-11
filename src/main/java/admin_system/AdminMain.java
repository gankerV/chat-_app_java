package admin_system;

import javax.swing.SwingUtilities;

import login_system.LoginScreen;

public class AdminMain {
    public static void main(String[] args) {
        // Gọi màn hình đăng nhập và lấy userId
        int userId = LoginScreen.showLoginScreen(); // Đăng nhập và lấy userId

        if (userId == -1) {
            System.out.println("Login failed. Exiting...");
            return; // Thoát nếu đăng nhập thất bại
        }

        // Nếu đăng nhập thành công, mở giao diện adminui
        SwingUtilities.invokeLater(() -> new adminui(userId));
    }
}
