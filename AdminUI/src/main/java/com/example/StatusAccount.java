package com.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class StatusAccount extends JPanel {
    private JComboBox<String> comboBox1;
    private JTable accountList;


    public StatusAccount() {

        setLayout(new BorderLayout());


        // Content Panel with Titled Border
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Management Status Account",
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
        JLabel searchDateLabel = new JLabel("Search by status:");

        // Panel trái chứa các trường tìm kiếm
        JPanel leftPanel = new JPanel(new GridLayout(2, 2)); // 2 hàng, 2 cột
        leftPanel.add(searchNameLabel);
        leftPanel.add(searchNameField);
        leftPanel.add(searchDateLabel);
        leftPanel.add(searchDateField);
        topPanel.add(leftPanel, BorderLayout.WEST);

        // Panel phải chứa "Order by"
        JLabel orderLabel = new JLabel("Order by:");
        comboBox1 = new JComboBox<>(new String[]{"Họ tên", "Ngày tạo"});

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(orderLabel);
        rightPanel.add(comboBox1);
        topPanel.add(rightPanel, BorderLayout.EAST);

        contentPanel.add(topPanel, BorderLayout.NORTH); // Thêm topPanel vào contentPanel

        // Panel cho chọn khoảng thời gian và chart
        JPanel timePanel = new JPanel(new BorderLayout());

        // Panel chọn ngày
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel startDateLabel = new JLabel("Start Date:");
        JTextField startDateField = new JTextField(10);
        JLabel endDateLabel = new JLabel("End Date:");
        JTextField endDateField = new JTextField(10);
        JButton filterButton = new JButton("Filter");

        datePanel.add(startDateLabel);
        datePanel.add(startDateField);
        datePanel.add(endDateLabel);
        datePanel.add(endDateField);
        datePanel.add(filterButton);

        timePanel.add(datePanel, BorderLayout.WEST);

        // Panel chứa nút Chart
        JPanel chartPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton chartButton = new JButton("Chart");
        chartPanel.add(chartButton);
        timePanel.add(chartPanel, BorderLayout.EAST);

        contentPanel.add(timePanel, BorderLayout.CENTER); // Thêm timePanel vào contentPanel

        chartButton.addActionListener(e -> {
            showChartPanel(); // Hiển thị biểu đồ khi nhấn nút Chart
        });

        // Khởi tạo JTable
        String[] columnNames = {"ID", "Tên đăng nhập", "Họ tên", "Ngày tạo", "SL Mở ứng dụng", "SL người chat", "SL nhóm chat"};
        Object[][] data = {{"1", "Xanh1", "Nguyen Van Xanh", "2024-11-15", "10", "2", "1"}};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        accountList = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(accountList);

        contentPanel.add(scrollPane, BorderLayout.SOUTH); // Thêm scrollPane vào contentPanel

        add(contentPanel, BorderLayout.CENTER); // Thêm contentPanel vào JFrame
    }

    private void showChartPanel() {
        // Tạo panel chứa JTextField để nhập năm
        JPanel inputPanel = new JPanel();
        JLabel yearLabel = new JLabel("Enter Year:");
        JTextField yearField = new JTextField(10);

        inputPanel.add(yearLabel);
        inputPanel.add(yearField);

        // Tạo JFrame để chứa inputPanel
        JFrame chartFrame = new JFrame("NumPeople Chart");
        chartFrame.setLayout(new BorderLayout());
        chartFrame.setSize(800, 600);
        chartFrame.setLocationRelativeTo(null);

        // Thêm inputPanel vào phía trên (North) của chartFrame
        chartFrame.add(inputPanel, BorderLayout.NORTH);

        // Tạo ChartPanel trống để chứa biểu đồ (sẽ được cập nhật sau khi người dùng nhập năm)
        JPanel chartContainer = new JPanel();
        chartFrame.add(chartContainer, BorderLayout.CENTER);

        // Thêm listener khi người dùng nhập vào trường năm (yearField)
        yearField.addActionListener(e -> {
            String yearInput = yearField.getText();
            if (!yearInput.isEmpty()) {
                try {
                    int year = Integer.parseInt(yearInput); // Chuyển đổi năm từ chuỗi sang số nguyên
                    // Hiển thị biểu đồ với năm người dùng nhập
                    showBarChart(chartContainer, year);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(chartFrame, "Please enter a valid year.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(chartFrame, "Please enter a year.", "Empty Input", JOptionPane.WARNING_MESSAGE);
            }
        });

        chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chartFrame.setVisible(true);
    }

    private void showBarChart(JPanel chartContainer, int year) {
        // Dữ liệu giả lập cho biểu đồ
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Random random = new Random();

        for (int month = 1; month <= 12; month++) {
            int value = random.nextInt(50); // Số lượng người đăng ký ngẫu nhiên
            dataset.addValue(value, "NumPeople", "Month " + month);
        }

        // Tạo biểu đồ
        JFreeChart barChart = ChartFactory.createBarChart(
                "Number of people open app in " + year,
                "Month",
                "Number of people open chat app",
                dataset
        );

        // Tạo ChartPanel từ biểu đồ
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

        // Xóa biểu đồ hiện tại trong chartContainer nếu có
        chartContainer.removeAll();
        chartContainer.add(chartPanel, BorderLayout.CENTER);
        chartContainer.revalidate();
        chartContainer.repaint();
    }
}
