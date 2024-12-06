package admin_system;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JPanel {
    private JComboBox<String> comboBox1;
    private JTable accountList;


    public Home() {

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

        JLabel orderLabel = new JLabel("Order by:");
        comboBox1 = new JComboBox<>(new String[]{"ID", "Họ tên", "Tên đăng nhập", "Trạng thái", "Ngày tạo"});

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(orderLabel);
        rightPanel.add(comboBox1);
        topPanel.add(rightPanel, BorderLayout.EAST);

        contentPanel.add(topPanel, BorderLayout.NORTH);

        // Khởi tạo JTable
        String[] columnNames = {"ID", "Username", "Password", "Name", "Email", "Status", "Banned"};
        Object[][] data = {{"1", "xanh1", "123", "Nguyen Van Xanh", "nvxanh75@gmail.com", "online", "0"}};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        accountList = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(accountList);
        contentPanel.add(scrollPane, BorderLayout.CENTER);



        // Tạo buttonPanel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JButton banButton = new JButton("Ban - UnBan");
        JButton viewHistoryButton = new JButton("Login History");
        JButton viewFriendsButton = new JButton("Friends List");
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(banButton);
        buttonPanel.add(viewHistoryButton);
        buttonPanel.add(viewFriendsButton);
        buttonPanel.setVisible(false);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Hiển thị buttonPanel khi chọn hàng
        accountList.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && accountList.getSelectedRow() != -1) {
                buttonPanel.setVisible(true);
            }
        });

        // Thêm nút cho bảng
        addButton.addActionListener(e -> showAddDialog(model));

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = accountList.getSelectedRow();
                if (selectedRow != -1) {
                    String id = (String) model.getValueAt(selectedRow, 0);
                    String username = (String) model.getValueAt(selectedRow, 1);
                    String password = (String) model.getValueAt(selectedRow, 2);
                    String name = (String) model.getValueAt(selectedRow, 3);
                    String email = (String) model.getValueAt(selectedRow, 4);
                    String status = (String) model.getValueAt(selectedRow, 5);


                    // Hiển thị màn hình chỉnh sửa
                    showEditDialog(selectedRow, id, username, password, name, email, status);
                }
            }
        });
        viewHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginHistoryDialog();
            }
        });

        viewFriendsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFriendsListDialog();
            }
        });

        add(contentPanel, BorderLayout.CENTER);
    }

    private void showEditDialog(int rowIndex, String id, String username, String password, String name, String email, String status) {
        JDialog editDialog = new JDialog();
        editDialog.setLayout(new GridLayout(0, 2));

        JTextField idField = new JTextField(id);
        idField.setEditable(false);
        JTextField usernameField = new JTextField(username);
        JTextField passwordField = new JTextField(password);
        JTextField nameField = new JTextField(name);
        JTextField emailField = new JTextField(email);
        JTextField statusField = new JTextField(status);


        editDialog.add(new JLabel("ID:"));
        editDialog.add(idField);

        editDialog.add(new JLabel("Username:"));
        editDialog.add(usernameField);
        editDialog.add(new JLabel("Password:"));
        editDialog.add(passwordField);
        editDialog.add(new JLabel("Name:"));
        editDialog.add(nameField);
        editDialog.add(new JLabel("Email:"));
        editDialog.add(emailField);
        editDialog.add(new JLabel("Status:"));
        editDialog.add(statusField);


        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        editDialog.add(saveButton);
        editDialog.add(cancelButton);

        saveButton.addActionListener(e -> {
            // Cập nhật dữ liệu trong bảng
            accountList.setValueAt(usernameField.getText(), rowIndex, 1);
            accountList.setValueAt(passwordField.getText(), rowIndex, 2);
            accountList.setValueAt(nameField.getText(), rowIndex, 3);
            accountList.setValueAt(emailField.getText(), rowIndex, 4);
            accountList.setValueAt(statusField.getText(), rowIndex, 5);

            editDialog.dispose();
        });

        cancelButton.addActionListener(e -> editDialog.dispose());

        editDialog.setSize(400, 300);
        editDialog.setLocationRelativeTo(this);
        editDialog.setVisible(true);
    }

    private void showAddDialog(DefaultTableModel model) {
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
        addDialog.add(new JLabel("Status:"));
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

            String newId = String.valueOf(model.getRowCount() + 1);
            model.addRow(new Object[]{newId, usernameField.getText(), passwordField.getText(), nameField.getText(), emailField.getText(), statusField.getText(), "0"});

            addDialog.dispose();
        });

        cancelButton.addActionListener(e -> addDialog.dispose());

        addDialog.setSize(400, 300);
        addDialog.setLocationRelativeTo(this);
        addDialog.setVisible(true);
    }
    private void showLoginHistoryDialog() {
        JDialog historyDialog = new JDialog();
        historyDialog.setTitle("Login History");
        historyDialog.setLayout(new BorderLayout());

        // Dữ liệu mẫu rỗng cho bảng lịch sử đăng nhập
        String[] columnNames = {"ID", "Username", "Name", "Login Time"};
        Object[][] data = {{"1", "xanh1", "Nguyen Van Xanh", "2024-11-11"}};

        JTable historyTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(historyTable);
        historyDialog.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> historyDialog.dispose());
        historyDialog.add(closeButton, BorderLayout.SOUTH);

        historyDialog.setSize(500, 300);
        historyDialog.setLocationRelativeTo(this);
        historyDialog.setVisible(true);
    }

    private void showFriendsListDialog() {
        JDialog friendsDialog = new JDialog();
        friendsDialog.setTitle("Friends List");
        friendsDialog.setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Username", "Name"};
        Object[][] data = {{"2", "Tín", "Vo Trung Tin"}};  // Dữ liệu mẫu hiện tại rỗng

        JTable friendsTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(friendsTable);
        friendsDialog.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> friendsDialog.dispose());
        friendsDialog.add(closeButton, BorderLayout.SOUTH);

        friendsDialog.setSize(500, 300);
        friendsDialog.setLocationRelativeTo(this);
        friendsDialog.setVisible(true);
    }
}
