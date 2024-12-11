package admin_system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import admin_system.bus.LoginHistoryBUS;
import admin_system.bus.UserAccountBUS;
import admin_system.dto.LoginHistoryDTO;
import admin_system.dto.UserAccountDTO;

public class Home extends JPanel {
    private JComboBox<String> comboBox1;
    private JTable accountList;
    private DefaultTableModel model;
    private UserAccountBUS userBUS;
    private LoginHistoryBUS loginBUS;

    public Home() {
        // khởi tạo
        userBUS = new UserAccountBUS();
        loginBUS = new LoginHistoryBUS();


        setLayout(new BorderLayout());
        // Content Panel with Titled Border
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Management Account",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16),
                Color.BLUE
        ));

        // Tạo topPanel chứa Add button và Filter
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton addButton = new JButton("Add");
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(addButton);
        topPanel.add(leftPanel, BorderLayout.WEST);

        // tạo comboBox Order by button
        JLabel orderLabel = new JLabel("Order by:");
        comboBox1 = new JComboBox<>(new String[]{"ID", "FullName", "UserName", "Status", "Created at"});

        // vị trí 2 nút Add và Order by 
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(orderLabel);
        rightPanel.add(comboBox1);
        topPanel.add(rightPanel, BorderLayout.EAST);
        contentPanel.add(topPanel, BorderLayout.NORTH);

        // Khởi tạo JTable
        String[] columnNames = {"ID", "Username", "Password", "Name", "Email", "Status", "Banned"};

        model = new DefaultTableModel(columnNames, 0); // Không có dòng dữ liệu ban đầu
        accountList = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(accountList);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Lấy dữ liệu ban đầu và hiển thị
        
        updateTableData(userBUS.getAll("ID"));

        // Tạo buttonPanel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JButton banButton = new JButton("Ban - UnBan");
        JButton viewLoginHistoryButton = new JButton("Login History");
        JButton viewFriendsButton = new JButton("Friends List");
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(banButton);
        buttonPanel.add(viewLoginHistoryButton);
        buttonPanel.add(viewFriendsButton);
        buttonPanel.setVisible(false);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Hiển thị buttonPanel khi chọn hàng
        accountList.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && accountList.getSelectedRow() != -1) {
                buttonPanel.setVisible(true);
            }
        });

        add(contentPanel, BorderLayout.CENTER);

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy giá trị được chọn trong JComboBox
                String selectedOrder = (String) comboBox1.getSelectedItem();
                // Gọi hàm getAll với giá trị được chọn và cập nhật dữ liệu
                updateTableData(userBUS.getAll(selectedOrder));
            }
        });
        // Thêm nút cho bảng
        addButton.addActionListener(e -> showAddDialog(model,userBUS));

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = accountList.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy thông tin từ hàng được chọn
                    int id = (int) model.getValueAt(selectedRow, 0); // ID là kiểu int
                    String username = (String) model.getValueAt(selectedRow, 1);
                    String password = (String) model.getValueAt(selectedRow, 2);
                    String name = (String) model.getValueAt(selectedRow, 3);
                    String email = (String) model.getValueAt(selectedRow, 4);
                    String status = (String) model.getValueAt(selectedRow, 5);
        
                    // Hiển thị hộp thoại chỉnh sửa
                    showEditDialog(id, username, password, name, email, status, selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to edit.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = accountList.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy thông tin từ hàng được chọn
                    int id = (int) model.getValueAt(selectedRow, 0); // ID là kiểu int
                    String username = (String) model.getValueAt(selectedRow, 1);
        
                    // Hiển thị thông báo xác nhận
                    int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Bạn muốn xóa người dùng \"" + username + "\"?",
                        "Xác nhận xóa",
                        JOptionPane.YES_NO_OPTION
                    );
        
                    if (confirm == JOptionPane.YES_OPTION) {
                        // Gọi hàm deleteUser của userBUS
                        boolean isDeleted = userBUS.deleteUser(id);
                        if (isDeleted) {
                            // Xóa hàng khỏi bảng
                            // update lại bảng theo thứ tự đang được chọn ở order by button 
                            updateTableData(userBUS.getAll((String) comboBox1.getSelectedItem()));
                            JOptionPane.showMessageDialog(null, "Xóa người dùng thành công.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Xóa người dùng thất bại.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete.");
                }
            }
        });
        
        banButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = accountList.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy thông tin từ hàng được chọn
                    int id = (int) model.getValueAt(selectedRow, 0); // ID là kiểu int
                    boolean isBanned = model.getValueAt(selectedRow, 6).equals("Yes"); // Cột "BANNED" hiển thị "Yes" hoặc "No"
        
                    // Gọi hàm banUser của userBUS để thay đổi trạng thái
                    boolean isSuccess = userBUS.banUser(id, !isBanned);
                    if (isSuccess) {
                        // Cập nhật trạng thái trong bảng
                        // update lại bảng theo thứ tự đang được chọn ở order by button 
                        updateTableData(userBUS.getAll((String) comboBox1.getSelectedItem()));
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật trạng thái thất bại.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to ban/unban.");
                }
            }
        });

        viewLoginHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = accountList.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy thông tin từ hàng được chọn
                    int id = (int) model.getValueAt(selectedRow, 0); // ID là kiểu int
        
                    // Gọi hàm viewHistoryLogin của userBUS để lấy lịch sử đăng nhập
                    List<LoginHistoryDTO> loginHistory = loginBUS.viewLoginHistory(id);
                    
                    if (loginHistory != null && !loginHistory.isEmpty()) {
                        
                        showLoginHistoryDialog(loginHistory);
                    } else {
                        JOptionPane.showMessageDialog(null, "No login history found for this user.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to view login history.");
                }
            }
        });

        viewFriendsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = accountList.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy thông tin từ hàng được chọn
                    int id = (int) model.getValueAt(selectedRow, 0); // ID là kiểu int
        
                    // Gọi hàm viewFriend của userBUS để lấy danh sách bạn bè
                    List<UserAccountDTO> friendsList = userBUS.viewFriends(id);
        
                    if (friendsList != null && !friendsList.isEmpty()) {
                        // Hiển thị danh sách bạn bè, ví dụ bằng cách mở một dialog
                        showFriendsListDialog(friendsList);
                    } else {
                        JOptionPane.showMessageDialog(null, "No friends found for this user.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to view friends.");
                }
            }
        });
    }

    private void updateTableData(List<UserAccountDTO> userDTOs) {
        // Xóa tất cả các dòng cũ trong model
        model.setRowCount(0);

        // Thêm dữ liệu mới vào model
        for (UserAccountDTO user : userDTOs) {
            Object[] row = {
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFullname(),
                user.getEmail(),
                user.isOnOff() ? "Online" : "Offline",
                user.isBanned() ? "Yes" : "No"
            };
            model.addRow(row);
        }
    }

    private void showEditDialog(int id, String username, String password, String name, String email, String status, int selectedRow) {
        // Tạo hộp thoại chỉnh sửa
        JDialog editDialog = new JDialog();
        editDialog.setLayout(new GridLayout(6, 2, 10, 10));
        editDialog.setSize(400, 300);

        // Tạo các trường chỉnh sửa
        JTextField usernameField = new JTextField(username);
        JTextField passwordField = new JTextField(password);
        JTextField nameField = new JTextField(name);
        JTextField emailField = new JTextField(email);
        JComboBox<String> statusComboBox = new JComboBox<>(new String[]{"Online", "Offline"});
        statusComboBox.setSelectedItem(status);

        // Thêm các thành phần vào hộp thoại
        editDialog.add(new JLabel("Username:"));
        editDialog.add(usernameField);
        editDialog.add(new JLabel("Password:"));
        editDialog.add(passwordField);
        editDialog.add(new JLabel("Full Name:"));
        editDialog.add(nameField);
        editDialog.add(new JLabel("Email:"));
        editDialog.add(emailField);
        editDialog.add(new JLabel("Status:"));
        editDialog.add(statusComboBox);

        // Nút Save
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            // Lấy thông tin đã chỉnh sửa
            String newUsername = usernameField.getText();
            String newPassword = passwordField.getText();
            String newName = nameField.getText();
            String newEmail = emailField.getText();
            String newStatus = (String) statusComboBox.getSelectedItem();

            // Tạo đối tượng UserAccountDTO để cập nhật
            UserAccountDTO updatedUser = new UserAccountDTO(id, newUsername, newPassword, newName, "", null, "", newEmail, 
                                                            newStatus.equals("Online"), null, false);

            // Gọi hàm updateUser() của userBUS
            userBUS.updateUser(updatedUser);

            
            // update lại bảng theo thứ tự đang được chọn ở order by button 
            updateTableData(userBUS.getAll((String) comboBox1.getSelectedItem()));

            // Đóng hộp thoại chỉnh sửa
            editDialog.dispose();
        });

        // Nút Cancel
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> editDialog.dispose());

        editDialog.add(saveButton);
        editDialog.add(cancelButton);

        editDialog.setLocationRelativeTo(null);
        editDialog.setVisible(true);
    }

    private void showAddDialog(DefaultTableModel model, UserAccountBUS userBUS) {
        JDialog addDialog = new JDialog();
        addDialog.setLayout(new GridLayout(0, 2));
    
        JTextField usernameField = new JTextField();
        JTextField passwordField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField statusField = new JTextField();
    
        addDialog.add(new JLabel("Username:"));
        addDialog.add(usernameField);
        addDialog.add(new JLabel("Password:"));
        addDialog.add(passwordField);
        addDialog.add(new JLabel("Name:"));
        addDialog.add(nameField);
        addDialog.add(new JLabel("Email:"));
        addDialog.add(emailField);
        addDialog.add(new JLabel("Status (true/false):"));
        addDialog.add(statusField);
    
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
    
        addDialog.add(saveButton);
        addDialog.add(cancelButton);
    
        saveButton.addActionListener(e -> {
            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(addDialog, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            try {
                // Create a new UserAccountDTO object with the entered data
                UserAccountDTO newUser = new UserAccountDTO();
                //newUser.setId(model.getRowCount() + 1); // Automatically set ID
                newUser.setUsername(usernameField.getText());
                newUser.setPassword(passwordField.getText());
                newUser.setFullname(nameField.getText());
                newUser.setEmail(emailField.getText());
                newUser.setOnOff(Boolean.parseBoolean(statusField.getText()));
                newUser.setCreatedAt(new Timestamp(System.currentTimeMillis())); // Set current timestamp
                newUser.setBanned(false); // Default value
    
                // Call the saveUser method
                boolean isSaved = userBUS.saveUser(newUser);
    
                if (isSaved) {
                    
                    // update lại bảng theo thứ tự đang được chọn ở order by button 
                    updateTableData(userBUS.getAll((String) comboBox1.getSelectedItem()));

                    JOptionPane.showMessageDialog(addDialog, "User added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    addDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(addDialog, "Failed to add user.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(addDialog, "Invalid data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        cancelButton.addActionListener(e -> addDialog.dispose());
    
        addDialog.setSize(400, 300);
        addDialog.setLocationRelativeTo(this);
        addDialog.setVisible(true);
    }
    
    private void showLoginHistoryDialog(List<LoginHistoryDTO> loginHistory) {
        JDialog historyDialog = new JDialog();
        historyDialog.setTitle("Login History");
        historyDialog.setLayout(new BorderLayout());
    
        // Dữ liệu bảng - lấy thông tin từ loginHistory
        String[] columnNames = {"Login ID", "User ID", "Login Time"};
        Object[][] data = new Object[loginHistory.size()][columnNames.length];
    
        for (int i = 0; i < loginHistory.size(); i++) {
            LoginHistoryDTO history = loginHistory.get(i);
            data[i][0] = history.getLoginId();
            data[i][1] = history.getUserId();
            data[i][2] = history.getLoginTime();
        }
    
        // Tạo bảng và cuộn
        JTable historyTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(historyTable);
        historyDialog.add(scrollPane, BorderLayout.CENTER);
    
        // Thêm nút đóng
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> historyDialog.dispose());
        historyDialog.add(closeButton, BorderLayout.SOUTH);
    
        // Thiết lập kích thước và hiển thị cửa sổ
        historyDialog.setSize(500, 300);
        historyDialog.setLocationRelativeTo(this);
        historyDialog.setVisible(true);
    }
    
    private void showFriendsListDialog(List<UserAccountDTO> friendsList) {
        JDialog friendsDialog = new JDialog();
        friendsDialog.setTitle("Friends List");
        friendsDialog.setLayout(new BorderLayout());
    
        // Dữ liệu cho bảng bạn bè
        String[] columnNames = {"ID", "Username", "Full Name"};
        Object[][] data = new Object[friendsList.size()][columnNames.length];
    
        // Điền dữ liệu vào bảng
        for (int i = 0; i < friendsList.size(); i++) {
            UserAccountDTO friend = friendsList.get(i);
            data[i][0] = friend.getId();
            data[i][1] = friend.getUsername();
            data[i][2] = friend.getFullname();
        }
    
        // Tạo bảng với dữ liệu
        JTable friendsTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(friendsTable);
        friendsDialog.add(scrollPane, BorderLayout.CENTER);
    
        // Nút đóng
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> friendsDialog.dispose());
        friendsDialog.add(closeButton, BorderLayout.SOUTH);
    
        friendsDialog.setSize(500, 300);
        friendsDialog.setLocationRelativeTo(this);
        friendsDialog.setVisible(true);
    }
    
}
