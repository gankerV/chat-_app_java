package admin_system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import admin_system.bus.AdminsFriendBUS;
import admin_system.dto.AdminsFriendDTO;

public class NumFriend extends JPanel {
    private JComboBox<String> comboBox1;
    private JTable accountList;
    private DefaultTableModel model;
    private AdminsFriendBUS adminsFriendBUS;

    private String searchUsername;
    private String selectedOption;
    private int searchCommonFriend;

    public NumFriend() {
        int adminId = adminui.getUserId();

        adminsFriendBUS = new AdminsFriendBUS();

        setLayout(new BorderLayout());


        // Content Panel with Titled Border
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Management Number Friend",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16),
                Color.BLUE
        ));

        // Tạo topPanel chứa Add button và Filter
        JPanel topPanel = new JPanel(new BorderLayout());

        JTextField searchNameField = new JTextField(15);
        JLabel searchNameLabel = new JLabel("Search by name:");
        JTextField searchDateField = new JTextField(15);
        JLabel searchDateLabel = new JLabel("Search by number common friend:");

        // Tạo panel để chứa các thành phần tìm kiếm trong cùng một cột (bên trái)
        JPanel leftPanel = new JPanel(new GridLayout(2, 2)); // 2 hàng, 2 cột
        leftPanel.add(searchNameLabel);
        leftPanel.add(searchNameField);
        leftPanel.add(searchDateLabel);
        leftPanel.add(searchDateField);
        topPanel.add(leftPanel, BorderLayout.WEST);

        JLabel orderLabel = new JLabel("Order by:");
        comboBox1 = new JComboBox<>(new String[]{"Username", "Created at"});

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(orderLabel);
        rightPanel.add(comboBox1);
        topPanel.add(rightPanel, BorderLayout.EAST);

        contentPanel.add(topPanel, BorderLayout.NORTH);

        // Khởi tạo JTable
        String[] columnNames = {"ID", "Username", "Created at", "Common Friends", "Friends"};
        model = new DefaultTableModel(columnNames, 0);
        accountList = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(accountList);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        add(contentPanel, BorderLayout.CENTER);
        
        // Gọi hàm cập nhật dữ liệu, sử dụng getFriendsByAdminId để lấy danh sách bạn bè của admin
        updateTableData(adminsFriendBUS.getFriendsByAdminId(adminId, "Username","",0));

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedOption = (String) comboBox1.getSelectedItem();
                // Gọi hàm để xử lý theo sự lựa chọn
                updateTableData(adminsFriendBUS.getFriendsByAdminId(adminId, selectedOption, searchUsername, searchCommonFriend));
            }
        });

        searchNameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchUsername = searchNameField.getText().trim(); 
                updateTableData(adminsFriendBUS.getFriendsByAdminId(adminId, selectedOption, searchUsername, searchCommonFriend));
                System.out.println(searchUsername);
            }
        });

        searchDateField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = searchDateField.getText().trim();
                
                // Kiểm tra nếu người dùng không nhập gì, gán giá trị mặc định là 0
                if (input.isEmpty()) {
                    searchCommonFriend = 0; // Mặc định là 0 nếu không có giá trị
                } else {
                    try {
                        searchCommonFriend = Integer.parseInt(input); // Chuyển đổi nếu có giá trị
                    } catch (NumberFormatException ex) {
                        searchCommonFriend = 0; // Nếu nhập không phải số, gán giá trị mặc định là 0
                        System.out.println("Invalid number format. Setting default to 0.");
                    }
                }
        
                // Cập nhật dữ liệu bảng với tham số tìm kiếm
                updateTableData(adminsFriendBUS.getFriendsByAdminId(adminId, selectedOption, searchUsername, searchCommonFriend));
                System.out.println(searchCommonFriend); // In ra giá trị số bạn bè chung
            }
        });
    }
        
    private void updateTableData(List<AdminsFriendDTO> friends) {
        // Xóa tất cả các dòng cũ trong model
        model.setRowCount(0);
    
        // Duyệt qua danh sách bạn bè và thêm từng đối tượng vào bảng
        for (AdminsFriendDTO friend : friends) {
            Object[] row = {
                friend.getId(),
                friend.getUsername(),
                friend.getCreatedAt() != null ? friend.getCreatedAt().toString() : "", // Hiển thị ngày tạo
                friend.getNumDirectFriends(),
                friend.getNumTotalFriends()
            };
            model.addRow(row);
        }
    }
        
}
