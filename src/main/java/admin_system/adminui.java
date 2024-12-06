package admin_system;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class adminui extends JFrame {
    private JComboBox<String> comboBox1;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JPanel listPanel;

    public adminui() {
        setTitle("Admin UI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // Khởi tạo JPanel chính
        mainPanel = new JPanel(new BorderLayout());

        // Khởi tạo CardLayout để chuyển đổi giữa các panel
        cardLayout = new CardLayout();
        listPanel = new JPanel(cardLayout);

        // Panel mặc định "Home" chứa quản lý tài khoản
        Home homePanel = new Home();
        listPanel.add(homePanel, "Home");

        // Panel LoginHistory
        LoginHistory loginHistoryPanel = new LoginHistory();
        listPanel.add(loginHistoryPanel, "Login History");

        // Panel Group
        Group groupPanel = new Group();
        listPanel.add(groupPanel, "Group");

        // Panel Report
        Report reportPanel = new Report();
        listPanel.add(reportPanel, "Report");

        // Panel New Registration
        NewRegistration newRegistrationPanel = new NewRegistration();
        listPanel.add(newRegistrationPanel, "New Registration");

        // Panel Number Friend
        NumFriend NumFriendPanel = new NumFriend();
        listPanel.add(NumFriendPanel, "Number Friend");

        // Panel Status Account
        StatusAccount statusPanel = new StatusAccount();
        listPanel.add(statusPanel, "Status Account");

        mainPanel.add(listPanel, BorderLayout.CENTER);

        // Tạo sidePanel để chuyển màn hình
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(30, 0, 30, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1;

        JButton homeButton = new JButton("Home");
        JButton loginHistoryButton = new JButton("Login History");
        JButton groupButton = new JButton("Group");
        JButton reportButton = new JButton("Report");
        JButton newRegistrationButton = new JButton("New Registration");
        JButton NumFriendButton = new JButton("Number Friend");
        JButton statusButton = new JButton("Status Account");

        // Thêm các nút vào sidePanel
        gbc.gridy = 0;
        sidePanel.add(homeButton, gbc);

        gbc.gridy = 1;
        sidePanel.add(loginHistoryButton, gbc);

        gbc.gridy = 2;
        sidePanel.add(groupButton, gbc);

        gbc.gridy = 3;
        sidePanel.add(reportButton, gbc);

        gbc.gridy = 4;
        sidePanel.add(newRegistrationButton, gbc);

        gbc.gridy = 5;
        sidePanel.add(NumFriendButton, gbc);

        gbc.gridy = 6;
        sidePanel.add(statusButton, gbc);

        mainPanel.add(sidePanel, BorderLayout.WEST);

        // Thêm ActionListener để chuyển panel
        homeButton.addActionListener(e -> cardLayout.show(listPanel, "Home"));
        loginHistoryButton.addActionListener(e -> cardLayout.show(listPanel, "Login History"));
        groupButton.addActionListener(e -> cardLayout.show(listPanel, "Group"));
        reportButton.addActionListener(e -> cardLayout.show(listPanel, "Report"));
        newRegistrationButton.addActionListener(e -> cardLayout.show(listPanel, "New Registration"));
        NumFriendButton.addActionListener(e -> cardLayout.show(listPanel, "Number Friend"));
        statusButton.addActionListener(e -> cardLayout.show(listPanel, "Status Account"));

        // Cài đặt JFrame
        setContentPane(mainPanel);
        setVisible(true);
    }



    
}
