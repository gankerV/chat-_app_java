package com.example;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class LoginHistory extends JPanel {
    public LoginHistory() {
        setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Login History");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create column names for the table
        String[] columnNames = {"Thời gian", "Tên đăng nhập", "Họ tên"};

        // Create sample data for the table (You can later replace this with real data)
        Object[][] data = {
                {"2024-11-16 10:00", "xanh1", "Nguyen Van Xanh"},
                {"2024-11-16 11:30", "john_doe", "John Doe"},
                {"2024-11-16 14:15", "anna_smith", "Anna Smith"}
        };

        // Create the table model and JTable
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable loginHistoryTable = new JTable(model);

        // Make the table uneditable
        loginHistoryTable.setDefaultEditor(Object.class, null);

        // Wrap the table in a JScrollPane for scrollability
        JScrollPane scrollPane = new JScrollPane(loginHistoryTable);

        // Add components to the panel
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
