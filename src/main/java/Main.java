import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import chat_system.UserMain;
import login_system.LoginScreen;

public class Main {
    public static void main(String[] args) {
        int userId = LoginScreen.showLoginScreen(); // Đăng nhập và lấy userId

        if (userId == -1) {
            System.out.println("Login failed. Exiting...");
            return;
        }

        // Hiển thị giao diện để chọn chương trình
        JFrame frame = new JFrame("Select Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel label = new JLabel("Choose a program to execute:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(label, gbc);

        // Nút Admin
        JButton adminButton = new JButton("Admin UI");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        frame.add(adminButton, gbc);

        // Nút Chat
        JButton chatButton = new JButton("Chat UI");
        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(chatButton, gbc);

        // Xử lý sự kiện cho nút Admin
        adminButton.addActionListener(e -> {
            frame.dispose(); // Đóng giao diện chính
            //AdminMain.main(new String[]{String.valueOf(userId)});
            UserMain.main(new String[]{}); 
        });

        // Xử lý sự kiện cho nút Chat
        chatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Đóng giao diện hiện tại
                UserMain.main(new String[]{}); // Gọi chương trình Chat
            }
        });

        frame.setVisible(true);
    }
}
