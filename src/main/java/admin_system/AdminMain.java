package admin_system;

import javax.swing.SwingUtilities;


public class AdminMain {
    public static void main(String[] args) {
        int userId = Integer.parseInt(args[0]); // Lấy userId từ args
        SwingUtilities.invokeLater(() -> new adminui(userId));
    }
}
