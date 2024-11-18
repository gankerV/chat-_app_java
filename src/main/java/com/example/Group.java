package com.example;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Group extends JPanel {
    private JComboBox<String> comboBox1;
    private JTable accountList;


    public Group() {

        setLayout(new BorderLayout());


        // Content Panel with Titled Border
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Management Group",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16),
                Color.BLUE
        ));

        // Tạo topPanel chứa Add button và Filter
        JPanel topPanel = new JPanel(new BorderLayout());
        JTextField searchField = new JTextField(20);
        JLabel searchLabel = new JLabel("Search by name:");
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(searchLabel);
        leftPanel.add(searchField);
        topPanel.add(leftPanel, BorderLayout.WEST);

        JLabel orderLabel = new JLabel("Order by:");
        comboBox1 = new JComboBox<>(new String[]{"Tên nhóm", "Ngày tạo"});

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(orderLabel);
        rightPanel.add(comboBox1);
        topPanel.add(rightPanel, BorderLayout.EAST);

        contentPanel.add(topPanel, BorderLayout.NORTH);

        // Khởi tạo JTable
        String[] columnNames = {"ID","Tên Nhóm", "Ngày tạo"};
        Object[][] data = {{"1", "Xanh_Group", "2024-11-15"}};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        accountList = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(accountList);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Thêm nút cho bảng
        //addButton.addActionListener(e -> showAddDialog(model));

        // Tạo buttonPanel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton memberButton = new JButton("List member");
        JButton adminButton = new JButton("List admin");
        buttonPanel.add(memberButton);
        buttonPanel.add(adminButton);
        buttonPanel.setVisible(false);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Hiển thị buttonPanel khi chọn hàng
        accountList.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && accountList.getSelectedRow() != -1) {
                buttonPanel.setVisible(true);
            }
        });

        memberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = accountList.getSelectedRow();
                if (selectedRow != -1) {
                    String GroupName = (String) model.getValueAt(selectedRow, 1);
                    // Hiển thị màn hình chỉnh sửa
                    showList(GroupName);
                }
            }
        });

        add(contentPanel, BorderLayout.CENTER);
    }

    private void showList(String groupName) {
        // Create a dialog for showing group members
        JDialog groupMembersDialog = new JDialog();
        groupMembersDialog.setLayout(new BorderLayout());

        // Set dialog title
        groupMembersDialog.setTitle("Thành viên trong " + groupName);

        // Create JTable with member data for the group
        String[] columnNames = {"Tên nhóm", "Tên thành viên", "Chức vụ"};
        Object[][] memberData = {
                {groupName, "Nguyen Van Xanh", "Member"},
                {groupName, "John Doe", "Member"},
                {groupName, "Anna Smith", "Member"}
        };  // You can dynamically populate this based on the group data

        DefaultTableModel groupModel = new DefaultTableModel(memberData, columnNames);
        JTable groupTable = new JTable(groupModel);

        // Add table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(groupTable);
        groupMembersDialog.add(scrollPane, BorderLayout.CENTER);

        // Create close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> groupMembersDialog.dispose());
        groupMembersDialog.add(closeButton, BorderLayout.SOUTH);

        // Set dialog properties
        groupMembersDialog.setSize(400, 300);
        groupMembersDialog.setLocationRelativeTo(this);
        groupMembersDialog.setVisible(true);
    }
}
